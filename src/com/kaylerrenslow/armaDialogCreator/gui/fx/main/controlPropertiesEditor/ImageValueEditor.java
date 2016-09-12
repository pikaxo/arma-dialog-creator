/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.gui.fx.main.controlPropertiesEditor;

import com.kaylerrenslow.armaDialogCreator.arma.util.ArmaTools;
import com.kaylerrenslow.armaDialogCreator.control.sv.SVImage;
import com.kaylerrenslow.armaDialogCreator.control.sv.SerializableValue;
import com.kaylerrenslow.armaDialogCreator.data.ApplicationDataManager;
import com.kaylerrenslow.armaDialogCreator.data.ExternalResource;
import com.kaylerrenslow.armaDialogCreator.data.PaaImageExternalResource;
import com.kaylerrenslow.armaDialogCreator.data.ResourceRegistry;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.inputfield.ArmaStringChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.fx.main.popup.SelectSaveLocationPopup;
import com.kaylerrenslow.armaDialogCreator.gui.fx.popup.StagePopup;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.lang.Lang;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 @author Kayler
 A ValueEditor implementation for selecting image files. This editor also has an implementation for discovering if the image file is a .paa or .tga file and will automatically
 convert it to a read-able format and store it.
 Created on 07/16/2016. */
public class ImageValueEditor implements ValueEditor {
	private final InputField<ArmaStringChecker, String> overrideField = new InputField<>(new ArmaStringChecker());
	
	private final Button btnChooseImage = new Button(Lang.ValueEditors.ImageValueEditor.LOCATE_IMAGE);
	protected final TextField tfFilePath = new TextField("");
	
	private final HBox hBox = new HBox(5, btnChooseImage, tfFilePath);
	
	private final StackPane masterPane = new StackPane(hBox);
	
	protected @Nullable SVImage imageValue = null;
	
	public ImageValueEditor() {
		HBox.setHgrow(tfFilePath, Priority.ALWAYS);
		tfFilePath.setEditable(false);
		btnChooseImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fc = new FileChooser();
				fc.setTitle(Lang.ValueEditors.ImageValueEditor.LOCATE_IMAGE);
				fc.getExtensionFilters().addAll(Lang.ValueEditors.ImageValueEditor.IMAGE_FILE_EXTENSIONS);
				fc.setInitialDirectory(ArmaDialogCreator.getApplicationDataManager().getAppSaveDataDirectory());
				File chosenFile = fc.showOpenDialog(ArmaDialogCreator.getPrimaryStage());
				if (chosenFile == null) {
					return;
				}
				chooseImage(chosenFile);
			}
		});
	}
	
	private void chooseImage(File chosenFile) {
		if (chosenFile.getName().endsWith(".paa")) {
			convertImage(chosenFile);
		} else {
			setValue(new SVImage(chosenFile));
		}
	}
	
	private void convertImage(File chosenFile) {
		File a3Tools = ArmaDialogCreator.getApplicationDataManager().getArma3ToolsDirectory();
		if (a3Tools == null) {
			Arma3ToolsDirNotSetPopup setPopup = new Arma3ToolsDirNotSetPopup();
			setPopup.showAndWait();
			a3Tools = setPopup.getA3ToolsDir();
			if (a3Tools == null) {
				return;
			}
		}
		ConvertingPaaPopup convertingPaaPopup = new ConvertingPaaPopup(this, chosenFile, a3Tools);
		convertingPaaPopup.show();
		
	}

	@Override
	public void submitCurrentData() {

	}

	@Override
	public @Nullable SerializableValue getValue() {
		return imageValue;
	}
	
	@Override
	public void setValue(SerializableValue val) {
		imageValue = (SVImage) val;
		if (val == null) {
			tfFilePath.setText("");
		} else {
			tfFilePath.setText(val.toString());
		}
	}
	
	@Override
	public @NotNull Node getRootNode() {
		return masterPane;
	}
	
	@Override
	public void setToOverride(boolean override) {
		masterPane.getChildren().clear();
		if (override) {
			masterPane.getChildren().add(overrideField);
		} else {
			masterPane.getChildren().add(hBox);
		}
	}
	
	@Override
	public InputField<ArmaStringChecker, String> getOverrideTextField() {
		return overrideField;
	}
	
	@Override
	public void focusToEditor() {
		btnChooseImage.requestFocus();
	}
	
	private static class ConvertingPaaPopup extends StagePopup<VBox> {
		
		private final ProgressBar progressBar = new ProgressBar(0);
		private ConvertPaaTask convertPaaTask;
		private boolean errorShown = false;
		
		public ConvertingPaaPopup(ImageValueEditor imageValueEditor, File convertingFile, File a3tools) {
			super(ArmaDialogCreator.getPrimaryStage(), new VBox(10), Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.POPUP_TITLE);
			myStage.initModality(Modality.APPLICATION_MODAL);
			myRootElement.setPadding(new Insets(10));
			myStage.setResizable(false);
			
			myRootElement.getChildren().add(new Label(String.format(Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.MESSAGE_F, convertingFile.getName())));
			myRootElement.getChildren().add(progressBar);
			
			initTask(imageValueEditor, convertingFile, a3tools);
		}
		
		private void initTask(ImageValueEditor imageValueEditor, File chosenFile, File a3Tools) {
			convertPaaTask = new ConvertPaaTask(chosenFile, a3Tools);
			progressBar.progressProperty().bind(convertPaaTask.progressProperty());
			convertPaaTask.exceptionProperty().addListener(new ChangeListener<Throwable>() {
				@Override
				public void changed(ObservableValue<? extends Throwable> observable, Throwable oldValue, Throwable newValue) {
					newValue.printStackTrace();
					conversionError(newValue.getMessage() != null ? newValue.getMessage() : Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.UNKNOWN_IMAGE_CONVERSION_ERROR);
				}
			});
			convertPaaTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					imageValueEditor.setValue(convertPaaTask.getValue());
					close();
				}
			});
			convertPaaTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					conversionError(Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.UNKNOWN_IMAGE_CONVERSION_ERROR);
				}
			});
			convertPaaTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					conversionError(Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.UNKNOWN_IMAGE_CONVERSION_ERROR);
				}
			});
			
			myStage.setOnShowing(new EventHandler<WindowEvent>() {//prevent the conversion thread being finished before the popup is shown
				@Override
				public void handle(WindowEvent event) {
					Thread thread = new Thread(convertPaaTask);
					thread.setDaemon(true);
					thread.start();
				}
			});
		}
		
		private void conversionError(String msg) {
			if (errorShown) { //prevent the popup showing twice
				return;
			}
			errorShown = true;
			new ConversionFailPopup(msg).showAndWait();
			close();
		}
		
		
		@Override
		protected void onCloseRequest(WindowEvent event) {
			event.consume();
		}
	}
	
	private static class ConvertPaaTask extends Task<SVImage> {
		
		private final File toConvert;
		private final File a3Tools;
		
		private static final long TIMEOUT = 1000 * 10; //ten seconds
		
		public ConvertPaaTask(@NotNull File toConvert, @NotNull File a3Tools) {
			this.toConvert = toConvert;
			this.a3Tools = a3Tools;
		}
		
		@Override
		protected SVImage call() throws Exception {
			updateProgress(-1, 1);
			File f = ResourceRegistry.getResourcesFilePathForName(toConvert.getName() + ".png");
			boolean good = ArmaTools.imageToPAA(a3Tools, toConvert, f, TIMEOUT);
			if (!good) {
				cancel();
				return null;
			}
			updateProgress(1, 1);
			Thread.sleep(500); //show that there was success for a brief moment to not to confuse user

			ExternalResource resource = new PaaImageExternalResource(toConvert, f);
			ResourceRegistry.getGlobalRegistry().getExternalResourceList().add(resource);
			ApplicationDataManager.getInstance().getApplicationData().getCurrentProject().getResourceRegistry().getExternalResourceList().add(resource);

			return new SVImage(f);
		}
	}
	
	private static class Arma3ToolsDirNotSetPopup extends StagePopup<VBox> {
		
		public Arma3ToolsDirNotSetPopup() {
			super(ArmaDialogCreator.getPrimaryStage(), new VBox(5), Lang.Popups.GENERIC_POPUP_TITLE);
			myStage.initModality(Modality.APPLICATION_MODAL);
			myStage.setResizable(false);
			
			Button btnLocate = new Button(Lang.ValueEditors.ImageValueEditor.SET_A3_TOOLS_BTN);
			btnLocate.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					new SelectSaveLocationPopup(ArmaDialogCreator.getApplicationDataManager().getAppSaveDataDirectory(), ArmaDialogCreator.getApplicationDataManager().getArma3ToolsDirectory()).showAndWait();
					close();
				}
			});
			
			myRootElement.setPadding(new Insets(10));
			myRootElement.getChildren().add(new Label(Lang.ValueEditors.ImageValueEditor.A3_TOOLS_DIR_NOT_SET));
			myRootElement.getChildren().add(btnLocate);
			
			myRootElement.getChildren().addAll(new Separator(Orientation.HORIZONTAL), getResponseFooter(true, true, false));
		}
		
		@Nullable
		public File getA3ToolsDir() {
			return ArmaDialogCreator.getApplicationDataManager().getArma3ToolsDirectory();
		}
	}
	
	private static class ConversionFailPopup extends StagePopup<VBox> {
		
		public ConversionFailPopup(@NotNull String message) {
			super(ArmaDialogCreator.getPrimaryStage(), new VBox(5), Lang.ValueEditors.ImageValueEditor.ConvertingPaaPopup.CONVERT_ERROR_POPUP_TITLE);
			
			myStage.initModality(Modality.APPLICATION_MODAL);
			myRootElement.setPadding(new Insets(10));
			myRootElement.getChildren().addAll(new HBox(10, new ImageView("/com/kaylerrenslow/armaDialogCreator/gui/img/icons/error64.png"), new Label(message)));
			myStage.setMinWidth(340d);
			
			myRootElement.getChildren().addAll(new Separator(Orientation.HORIZONTAL), getResponseFooter(false, true, false));
		}
	}
}
