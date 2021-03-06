package com.kaylerrenslow.armaDialogCreator.data.export;

import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaControl;
import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaControlGroup;
import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaDisplay;
import com.kaylerrenslow.armaDialogCreator.arma.stringtable.StringTableKey;
import com.kaylerrenslow.armaDialogCreator.control.ControlClass;
import com.kaylerrenslow.armaDialogCreator.control.ControlProperty;
import com.kaylerrenslow.armaDialogCreator.control.Macro;
import com.kaylerrenslow.armaDialogCreator.control.PropertyType;
import com.kaylerrenslow.armaDialogCreator.control.sv.SerializableValue;
import com.kaylerrenslow.armaDialogCreator.data.Project;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import com.kaylerrenslow.armaDialogCreator.util.IndentedStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 Created by Kayler on 09/13/2016.
 */
public class ProjectExporter {
	private static final String CONTROLS_BACKGROUND = "ControlsBackground";
	private static final String OBJECTS = "Objects";
	private static final String CONTROLS = "Controls";

	public static void export(@NotNull ProjectExportConfiguration configuration) throws IOException {
		new ProjectExporter(configuration).export();
	}

	public static void export(@NotNull ProjectExportConfiguration configuration, @NotNull OutputStream displayOutputStream, @Nullable OutputStream macrosOutputStream) throws IOException {
		new ProjectExporter(configuration).export(displayOutputStream, macrosOutputStream);
	}


	@NotNull
	public static String getMacrosFileName(@NotNull ProjectExportConfiguration configuration) {
		return configuration.getExportClassName() + "_Macros" + configuration.getHeaderFileType().getExtension();
	}

	@NotNull
	public static String getDisplayFileName(@NotNull ProjectExportConfiguration configuration) {
		return configuration.getExportClassName() + configuration.getHeaderFileType().getExtension();
	}

	@NotNull
	public static String getExportValueString(@NotNull SerializableValue value, @NotNull PropertyType type) {
		String[] arr = value.getAsStringArray();
		StringBuilder ret = new StringBuilder();
		String v;
		for (int i = 0; i < arr.length; i++) {
			v = arr[i];
			for (int quoteIndex : type.getIndexesWithQuotes()) {
				if (quoteIndex == i) {
					v = "\"" + v + "\"";
					break;
				}
			}
			ret.append(v).append(i != arr.length - 1 ? "," : "");
		}
		return arr.length > 1 ? "{" + ret + "}" : ret.toString();
	}

	public static void exportControlClass(@NotNull ProjectExportConfiguration configuration, @NotNull ControlClass controlClass, @NotNull OutputStream stream) throws IOException {
		ProjectExporter exporter = new ProjectExporter(configuration);
		CachedIndentedStringBuilder builder = getBuilder(stream);
		exporter.writeControlClass(builder, controlClass);
		stream.flush();
	}

	private static CachedIndentedStringBuilder getBuilder(@NotNull OutputStream outputStream) {
		return new CachedIndentedStringBuilder(4, true, 120, s -> {
			try {
				outputStream.write(s.getBytes());
				outputStream.flush();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
			return null;
		});
	}

	private final Project project;
	private final ProjectExportConfiguration configuration;
	private final ResourceBundle bundle = Lang.ApplicationBundle();

	private CachedIndentedStringBuilder displayStringBuilder;

	private CachedIndentedStringBuilder macrosStringBuilder;

	public ProjectExporter(@NotNull ProjectExportConfiguration configuration) {
		this.configuration = configuration;
		this.project = configuration.getProject();

	}

	public void export() throws IOException {
		if (!configuration.getExportLocation().exists()) {
			configuration.getExportLocation().mkdirs();
		}
		if (!configuration.getExportLocation().isDirectory()) {
			throw new IllegalArgumentException("exportLocation ('" + configuration.getExportLocation().getPath() + "') is not a directory");
		}
		final String exportDirectoryPath = configuration.getExportLocation().getPath() + "/";
		final File exportDirectory = configuration.getExportLocation();

		exportDirectory.mkdir();

		final File exportDisplayFile = new File(exportDirectoryPath + getDisplayFileName(configuration));
		exportDisplayFile.createNewFile();
		final FileOutputStream fosDisplay = new FileOutputStream(exportDisplayFile);

		FileOutputStream fosMacros = null;
		if (configuration.shouldExportMacrosToFile()) {
			final File macrosExportFile = new File(exportDirectoryPath + getMacrosFileName(configuration));
			macrosExportFile.createNewFile();
			fosMacros = new FileOutputStream(macrosExportFile);
		}

		export(fosDisplay, fosMacros);

		fosDisplay.close();
		if (fosMacros != null) {
			fosMacros.close();
		}
	}

	public void export(@NotNull OutputStream displayOutputStream, @Nullable OutputStream macrosOutputStream) throws IOException {
		if (macrosOutputStream == null || !configuration.shouldExportMacrosToFile()) {
			macrosOutputStream = displayOutputStream; //save the macros inside the display header file
		}

		displayStringBuilder = getBuilder(displayOutputStream);
		macrosStringBuilder = getBuilder(macrosOutputStream);

		exportMacros(macrosStringBuilder);
		//write remainder stuff
		macrosOutputStream.write(macrosStringBuilder.toString().getBytes());

		if (configuration.shouldPlaceAdcNotice()) {
			writelnComment(displayStringBuilder, bundle.getString("Misc.adc_export_notice"));
			writeln(displayStringBuilder, "");
		}

		exportDisplay(displayStringBuilder);

		//write the remainder of the string builders
		displayOutputStream.write(displayStringBuilder.toString().getBytes());

		//one last flush
		displayOutputStream.flush();
		macrosOutputStream.flush();
	}

	private void writeln(@NotNull IndentedStringBuilder stringBuilder, @NotNull String s) {
		stringBuilder.append(s);
		stringBuilder.append('\n');
	}


	private void exportMacros(@NotNull IndentedStringBuilder stringBuilder) throws IOException {
		if (configuration.shouldPlaceAdcNotice()) {
			writelnComment(stringBuilder, bundle.getString("Misc.adc_export_notice"));
			writeln(stringBuilder, "");
		}

		for (Macro macro : project.getMacroRegistry().getMacros()) {
			if (macro.getComment() != null && macro.getComment().length() != 0) {
				writelnComment(stringBuilder, macro.getComment());
			}
			writeln(stringBuilder, "#define " + macro.getKey() + " " + getExportValueString(macro.getValue(), macro.getPropertyType()));
		}

		stringBuilder.append('\n');
	}

	private void exportDisplay(@NotNull IndentedStringBuilder stringBuilder) throws IOException {
		if (configuration.shouldExportMacrosToFile()) {
			writeln(stringBuilder, "#include \"" + getMacrosFileName(configuration) + "\"");
			writeln(stringBuilder, "");
		}
		if (project.getProjectDescription() != null && project.getProjectDescription().length() > 0) {
			writelnComment(stringBuilder, project.getProjectDescription());
		}
		ArmaDisplay display = project.getEditingDisplay();

		writeClass(stringBuilder, configuration.getExportClassName(), null, stringBuilderCopy -> {
			//write display properties

			writeControlProperties(displayStringBuilder, display.getDisplayProperties());
			displayStringBuilder.append('\n');


			//write background controls
			writeClass(stringBuilder, CONTROLS_BACKGROUND, null, sb -> {
				for (ArmaControl control : display.getBackgroundControls()) {
					writeControl(stringBuilder, control);
				}
				return null;
			});

			//write controls
			writeClass(stringBuilder, CONTROLS, null, sb -> {
				for (ArmaControl control : display.getControls()) {
					writeControl(stringBuilder, control);
				}
				return null;
			});


			return null;
		});

	}

	private void writeControl(@NotNull IndentedStringBuilder stringBuilder, @NotNull ArmaControl control) {
		if (control instanceof ArmaControlGroup) {
			//write group's "Controls" class
			writeClass(stringBuilder, CONTROLS, null, sb -> {
				for (ArmaControl subControl : ((ArmaControlGroup) control).getControls()) {
					writeControl(sb, subControl);
				}
				return null;
			});

		}
		//write control body
		writeControlClass(stringBuilder, control);
	}

	private void writeControlClass(@NotNull IndentedStringBuilder stringBuilder, @NotNull ControlClass controlClass) {
		writeClass(stringBuilder, controlClass.getClassName(), controlClass.getExtendClass() == null ? null : controlClass.getExtendClass().getClassName(), sb -> {
			writeControlProperties(sb, controlClass.getAllChildProperties());
			for (ControlClass nested : controlClass.getAllNestedClasses()) {
				writeControlClass(stringBuilder, nested);
			}
			return null;
		});
	}

	private void writeClass(@NotNull IndentedStringBuilder stringBuilder, @NotNull String className, @Nullable String extendClassName, @NotNull Function<IndentedStringBuilder, Void> writeBodyFunc) {
		//class example : thing
		//{
		//	writeBodyFunc.apply(stringBuilder)
		//};

		stringBuilder.append("class ");
		stringBuilder.append(className);
		if (extendClassName != null) {
			stringBuilder.append(" : ");
			stringBuilder.append(extendClassName);
			stringBuilder.append(" ");
		}

		stringBuilder.append('\n');
		stringBuilder.incrementTabCount();
		stringBuilder.append('{');

		stringBuilder.append('\n');

		writeBodyFunc.apply(stringBuilder);

		stringBuilder.decrementTabCount();
		stringBuilder.append('\n');
		stringBuilder.append("};");
		stringBuilder.append('\n');
	}

	private void writeControlProperties(@NotNull IndentedStringBuilder stringBuilder, @NotNull Iterable<? extends ControlProperty> controlProperties) {
		String itemFormatString = "%s = %s;";
		String itemArrayFormatString = "%s[] = %s;";
		for (ControlProperty property : controlProperties) {
			if (property.getValue() == null/* && editor.isOptional()*/) { //can allow for partial implementation, so we don't need to check if it is optional
				continue;
			}
			if (property.isInherited()) {
				continue;
			}
			if (property.getMacro() != null) {
				StringTableKey stringKey = null;
				if (property.getMacro() instanceof StringTableKey) {
					stringKey = (StringTableKey) property.getMacro();
				}
				stringBuilder.append(String.format(itemFormatString, property.getName(), stringKey != null ? stringKey.getHeaderMacroId() : property.getMacro().getKey()));
			} else {
				if (property.getValue().getAsStringArray().length == 1) {
					stringBuilder.append(String.format(itemFormatString, property.getName(), getExportValueString(property.getValue(), property.getPropertyType())));
				} else {
					stringBuilder.append(String.format(itemArrayFormatString, property.getName(), getExportValueString(property.getValue(), property.getPropertyType())));
				}
			}
			stringBuilder.append('\n');
		}
	}

	private void writelnComment(@NotNull IndentedStringBuilder stringBuilder, String s) throws IOException {
		if (s.contains("\n")) {
			writeln(stringBuilder, "/*");
			writeln(stringBuilder, s);
			writeln(stringBuilder, "*/");
		} else {
			writeln(stringBuilder, "//" + s);
		}
	}

	private static class CachedIndentedStringBuilder extends IndentedStringBuilder {

		private final int cacheSize;
		private int appendCount;
		private Function<String, Object> onFull;

		public CachedIndentedStringBuilder(int tabSizeInSpaces, boolean useTabCharacter, int cacheSize, @NotNull Function<String, Object> onFull) {
			super(tabSizeInSpaces, useTabCharacter);
			this.cacheSize = cacheSize;
			this.onFull = onFull;
		}

		@Override
		public void append(char c) {
			super.append(c);
			appendCount++;
			if (appendCount >= cacheSize) {
				onFull.apply(this.toString());
				this.getBuilder().delete(0, length());
				appendCount = 0;
			}
		}
	}
}
