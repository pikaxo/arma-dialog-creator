package com.kaylerrenslow.armaDialogCreator.gui.main.actions.mainMenu.file;

import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 Created by Kayler on 05/20/2016.
 */
public class FileCloseProjectAction implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent event) {
		ArmaDialogCreator.loadNewProject();
	}
}
