/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.gui.fx.main.popup.export;

import com.kaylerrenslow.armaDialogCreator.data.Project;
import com.kaylerrenslow.armaDialogCreator.data.io.export.HeaderFileType;
import com.kaylerrenslow.armaDialogCreator.data.io.export.ProjectExportConfiguration;
import com.kaylerrenslow.armaDialogCreator.data.io.export.ProjectExporter;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.FileChooserPane;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.inputfield.IdentifierChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.fx.main.displayPropertiesEditor.DisplayPropertiesEditorPane;
import com.kaylerrenslow.armaDialogCreator.gui.fx.popup.StageDialog;
import com.kaylerrenslow.armaDialogCreator.gui.img.ImagePaths;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.ExceptionHandler;
import com.kaylerrenslow.armaDialogCreator.main.HelpUrls;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import com.kaylerrenslow.armaDialogCreator.util.BrowserUtil;
import com.kaylerrenslow.armaDialogCreator.util.ValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 Created by Kayler on 10/02/2016.
 */
public class EditExportConfigurationDialog extends StageDialog<VBox> {

	private enum DisplayType {
		DIALOG(Lang.ApplicationBundle().getString("Popups.ExportProject.DisplayProperties.dialog")), TITLE(Lang.ApplicationBundle().getString("Popups.ExportProject.DisplayProperties.title"));

		private final String displayName;

		DisplayType(String displayName) {
			this.displayName = displayName;
		}

		public static final DisplayType DEFAULT = DIALOG;

	}

	private boolean cancel = false;
	private DisplayType selectedDisplayType = DisplayType.DEFAULT;

	private final Insets padding10t = new Insets(10, 0, 10, 0);
	private final ProjectExportConfiguration configuration;

	/*export parameter things*/
	/** observer to detect when the macros are being exported to their own file, or being placed in the display's header file */
	private final ValueObserver<Boolean> exportMacrosToFileObserver = new ValueObserver<>(false);

	public EditExportConfigurationDialog(@NotNull Project project) {
		super(ArmaDialogCreator.getPrimaryStage(), new VBox(10), Lang.ApplicationBundle().getString("Popups.ExportProject.dialog_title"), true, true, true);
		btnOk.setText(Lang.ApplicationBundle().getString("Popups.ExportProject.ok_button_export"));
		configuration = project.getExportConfiguration().copy();

		setStageSize(720, 480);
		myRootElement.setPadding(new Insets(10d));

		final Label lblTitle = new Label(Lang.ApplicationBundle().getString("Popups.ExportProject.title_label"));
		lblTitle.setFont(Font.font(17));
		myRootElement.getChildren().add(lblTitle);
		myRootElement.getChildren().add(new Separator(Orientation.HORIZONTAL));
		final Tab tabDisplayProperties = new Tab(Lang.ApplicationBundle().getString("Popups.ExportProject.display_properties"));
		tabDisplayProperties.setClosable(false);
		final Tab tabExportParameters = new Tab(Lang.ApplicationBundle().getString("Popups.ExportProject.export_parameters"));
		tabExportParameters.setClosable(false);
		final Tab tabExportPreview = new Tab(Lang.ApplicationBundle().getString("Popups.ExportProject.export_preview"));
		tabExportPreview.setClosable(false);

		final TabPane tabPane = new TabPane(tabDisplayProperties, tabExportParameters, tabExportPreview);
		VBox.setVgrow(tabPane, Priority.ALWAYS);

		myRootElement.getChildren().add(tabPane);

		initTabDisplayProperties(tabDisplayProperties);
		initTabExportParameters(tabExportParameters);
		initTabExportPreview(tabExportPreview);
	}

	/*
	*
	* TAB INIT: Display Properties
	*
	*/
	private void initTabDisplayProperties(Tab tabDisplayProperties) {
		final VBox tabRoot = new VBox(10);
		tabRoot.setPadding(padding10t);
		tabDisplayProperties.setContent(tabRoot);

		/*class name*/
		final Label lblClassName = new Label("");
		final InputField<IdentifierChecker, String> inputFieldClassName = new InputField<>(new IdentifierChecker(), configuration.getExportClassName());
		final ValueObserver<String> classNameObserver = inputFieldClassName.getValueObserver();
		classNameObserver.addValueListener(new ValueListener<String>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<String> observer, String oldValue, String newValue) {
				configuration.setExportClassName(newValue);
			}
		});
		HBox.setHgrow(inputFieldClassName, Priority.ALWAYS);
		final HBox hboxClassName = new HBox(5, lblClassName, inputFieldClassName);
		tabRoot.getChildren().add(hboxClassName);

		/*display type*/
		final Label lblDisplayType = new Label(Lang.ApplicationBundle().getString("Popups.ExportProject.DisplayProperties.display_type"));
		final ToggleGroup toggleGroup = new ToggleGroup();
		final FlowPane flowPaneDisplayType = new FlowPane(Orientation.HORIZONTAL, 5, 10);
		final HBox hboxDisplayType = new HBox(5, lblDisplayType, flowPaneDisplayType);
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				selectedDisplayType = (DisplayType) newValue.getUserData();
				lblClassName.setText(String.format(Lang.ApplicationBundle().getString("Popups.ExportProject.DisplayProperties.class_name_f"), selectedDisplayType.displayName));
			}
		});
		for (DisplayType type : DisplayType.values()) {
			final RadioButton radioButton = new RadioButton(type.displayName);
			radioButton.setUserData(type);
			radioButton.setToggleGroup(toggleGroup);
			if (type == selectedDisplayType) {
				radioButton.setSelected(true);
			}
			flowPaneDisplayType.getChildren().add(radioButton);
		}
		tabRoot.getChildren().add(hboxDisplayType);

		/*display properties*/
		DisplayPropertiesEditorPane editorPane = new DisplayPropertiesEditorPane(configuration.getProject().getEditingDisplay());
		tabRoot.getChildren().add(editorPane);
	}

	/*
	*
	* TAB INIT: Export Parameters
	*
	*/
	private void initTabExportParameters(Tab tabExportParameters) {
		final VBox tabRoot = new VBox(20);
		tabRoot.setPadding(padding10t);
		tabExportParameters.setContent(tabRoot);

		/*set export directory*/
		final Label lblExportDirectory = new Label(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.export_directory"));
		final FileChooserPane chooserPane = new FileChooserPane(ArmaDialogCreator.getPrimaryStage(), FileChooserPane.ChooserType.DIRECTORY,
				Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.locate_export_directory"), configuration.getExportLocation());
		Tooltip.install(chooserPane, new Tooltip(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.export_directory_tooltip")));
		chooserPane.setChosenFile(configuration.getExportLocation());
		chooserPane.getChosenFileObserver().addValueListener(new ValueListener<File>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<File> observer, File oldValue, File newValue) {
				configuration.setExportLocation(newValue);
			}
		});
		tabRoot.getChildren().add(new VBox(5, lblExportDirectory, chooserPane));


		/*export macros to own file*/
		final CheckBox checkBoxExportMacrosToFile = new CheckBox(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.export_macros_to_file"));
		checkBoxExportMacrosToFile.setTooltip(new Tooltip(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.export_macros_to_file_tooltip")));
		checkBoxExportMacrosToFile.setSelected(configuration.shouldExportMacrosToFile());
		checkBoxExportMacrosToFile.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean selected) {
				configuration.setExportMacrosToFile(selected);
				exportMacrosToFileObserver.updateValue(selected);
			}
		});
		tabRoot.getChildren().add(checkBoxExportMacrosToFile);

		/*export file extension*/
		final ToggleGroup toggleGroupFileExt = new ToggleGroup();
		final FlowPane flowPaneFileExt = new FlowPane(Orientation.HORIZONTAL, 5, 10);
		for (HeaderFileType headerFileType : HeaderFileType.values()) {
			final RadioButton radioButtonFileExt = new RadioButton(headerFileType.getExtension());
			radioButtonFileExt.setToggleGroup(toggleGroupFileExt);
			if (headerFileType == configuration.getHeaderFileType()) {
				toggleGroupFileExt.selectToggle(radioButtonFileExt);
			}
			radioButtonFileExt.setUserData(headerFileType);
			flowPaneFileExt.getChildren().add(radioButtonFileExt);
		}
		toggleGroupFileExt.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				configuration.setFileType((HeaderFileType) newValue.getUserData());
			}
		});
		tabRoot.getChildren().add(new VBox(5, new Label(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.export_file_extension")), flowPaneFileExt));


		/*place adc notice*/
		final CheckBox checkBoxPlaceAdcNotice = new CheckBox(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.place_adc_notice"));
		checkBoxPlaceAdcNotice.setSelected(configuration.shouldPlaceAdcNotice());
		checkBoxPlaceAdcNotice.setTooltip(new Tooltip(Lang.ApplicationBundle().getString("Popups.ExportProject.ExportParameters.place_adc_notice_tooltip")));
		checkBoxPlaceAdcNotice.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				configuration.setPlaceAdcNotice(newValue);
			}
		});
		tabRoot.getChildren().add(new HBox(2, checkBoxPlaceAdcNotice, new ImageView(ImagePaths.ICON_HEART)));
	}

	/*
	*
	* TAB INIT: Export Preview
	*
	*/
	@SuppressWarnings("unchecked")
	private void initTabExportPreview(Tab tabExportPreview) {
		final StackPane tabRoot = new StackPane();
		tabExportPreview.setContent(tabRoot);

		final SplitPane splitPane = new SplitPane();
		splitPane.setOrientation(Orientation.HORIZONTAL);
		tabRoot.getChildren().add(splitPane);

		final VBox vboxDisplayPreview = new VBox(5);
		final Label lblDisplayFileExportPreview = new Label("");
		final TextArea textAreaDisplay = new TextArea();
		textAreaDisplay.setEditable(false);
		vboxDisplayPreview.getChildren().add(lblDisplayFileExportPreview);
		vboxDisplayPreview.getChildren().add(textAreaDisplay);
		VBox.setVgrow(textAreaDisplay, Priority.ALWAYS);

		final VBox vboxMacrosPreview = new VBox(5);
		final Label lblMacrosFileExportPreview = new Label("");
		final TextArea textAreaMacros = new TextArea();
		textAreaMacros.setEditable(false);

		vboxMacrosPreview.getChildren().add(lblMacrosFileExportPreview);
		vboxMacrosPreview.getChildren().add(textAreaMacros);
		VBox.setVgrow(textAreaMacros, Priority.ALWAYS);


		exportMacrosToFileObserver.addValueListener(new ValueListener<Boolean>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<Boolean> observer, Boolean oldValue, Boolean export) {
				if (export) {
					splitPane.getItems().add(vboxMacrosPreview);
				} else {
					splitPane.getItems().remove(vboxMacrosPreview);
				}
			}
		});
		tabExportPreview.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean selected) {
				if (!selected) {
					return;
				}
				lblDisplayFileExportPreview.setText(ProjectExporter.getDisplayFileName(configuration));
				lblMacrosFileExportPreview.setText(ProjectExporter.getMacrosFileName(configuration));

				final ByteArrayOutputStream outDisplay = new ByteArrayOutputStream();
				final ByteArrayOutputStream outMacros = new ByteArrayOutputStream();
				try {
					ProjectExporter.export(configuration, outDisplay, outMacros);
				} catch (Exception e) {
					textAreaDisplay.setText("Could not export: " + e.getMessage());
					ExceptionHandler.error(e);
				}
				textAreaDisplay.setText(new String(outDisplay.toByteArray()));
				textAreaMacros.setText(new String(outMacros.toByteArray()));
				try {
					outDisplay.close();
					outMacros.close();
				} catch (Exception e) {
					ExceptionHandler.error(e);
				}
			}
		});

		splitPane.getItems().add(vboxDisplayPreview);
		if (configuration.shouldExportMacrosToFile()) {
			splitPane.getItems().add(vboxMacrosPreview);
		}
	}

	@Override
	protected void cancel() {
		this.cancel = true;
		super.cancel();
	}

	@Override
	protected void help() {
		BrowserUtil.browse(HelpUrls.EXPORT);
	}

	@Nullable
	public ProjectExportConfiguration getConfiguration() {
		return cancel ? null : configuration;
	}
}
