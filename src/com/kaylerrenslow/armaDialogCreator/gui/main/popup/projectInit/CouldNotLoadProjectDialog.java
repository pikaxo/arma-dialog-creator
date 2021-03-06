package com.kaylerrenslow.armaDialogCreator.gui.main.popup.projectInit;

import com.kaylerrenslow.armaDialogCreator.gui.popup.StageDialog;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.ExceptionHandler;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

/**
 @author Kayler
 @since 11/23/2016 */
public class CouldNotLoadProjectDialog extends StageDialog<VBox> {
	public CouldNotLoadProjectDialog(@NotNull Exception e) {
		super(ArmaDialogCreator.getPrimaryStage(), new VBox(5), null, false, true, false);
		ResourceBundle bundle = Lang.ApplicationBundle();
		setTitle(bundle.getString("ProjectLoadError.could_not_load_project"));

		TextArea taError = ExceptionHandler.getExceptionTextArea(e);
		taError.setEditable(false);
		myStage.setResizable(false);
		myRootElement.getChildren().addAll(
				new Label(bundle.getString("ProjectLoadError.could_not_load_project")),
				taError
		);
	}
}

