/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.main;

import com.kaylerrenslow.armaDialogCreator.data.ApplicationData;
import com.kaylerrenslow.armaDialogCreator.data.ApplicationDataManager;
import com.kaylerrenslow.armaDialogCreator.data.ApplicationProperty;
import com.kaylerrenslow.armaDialogCreator.data.ResourceRegistry;
import com.kaylerrenslow.armaDialogCreator.data.io.xml.ResourceRegistryXmlLoader;
import com.kaylerrenslow.armaDialogCreator.gui.fx.main.ADCWindow;
import com.kaylerrenslow.armaDialogCreator.gui.fx.main.CanvasView;
import com.kaylerrenslow.armaDialogCreator.gui.fx.main.CanvasViewColors;
import com.kaylerrenslow.armaDialogCreator.gui.fx.popup.StagePopup;
import com.kaylerrenslow.armaDialogCreator.gui.img.Images;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Locale;
import java.util.jar.Manifest;

/**
 @author Kayler
 Contains main method for running Arma Dialog Creator
 Created on 05/11/2016. */
public final class ArmaDialogCreator extends Application {

	private static ArmaDialogCreator INSTANCE;
	private static Locale locale = ApplicationProperty.LOCALE.getDefaultValue();
	private static Manifest adcManifest;

	/**
	 Launches the Arma Dialog Creator. Only one instance is allowed to be opened at a time per Java process.
	 */
	public static void main(String[] args) {
		if (INSTANCE != null) {
			getPrimaryStage().requestFocus();
			return;
		}
		ExceptionHandler.init();
		launch(args);
	}

	private Stage primaryStage;
	private ADCWindow mainWindow;
	private ApplicationDataManager applicationDataManager;

	private final LinkedList<Runnable> showLater = new LinkedList<>();

	public ArmaDialogCreator() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Should not create a new ArmaDialogCreator instance when one already exists");
		}
		INSTANCE = this;
	}

	@Override
	public void init() throws Exception {
		int progress = 0;

		applicationDataManager = new ApplicationDataManager();
		initializeCurrentLocale();

		new ResourceRegistryXmlLoader(ResourceRegistry.getGlobalRegistry().getGlobalResourcesXmlFile(), null).load(ResourceRegistry.getGlobalRegistry());

		//todo have actual progress be displayed (sum of file sizes and when file is loaded, subtract file size)

		for (; progress < 100; progress++) {
			Thread.sleep(40);
			notifyPreloaderLog(new Preloader.ProgressNotification(progress / 100.0));
		}

	}

	private void notifyPreloaderLog(Preloader.PreloaderNotification notification) {
		if (containsUnamedLaunchParameter(ProgramArgument.LOG_INIT_PROGRESS)) {
			if (notification instanceof Preloader.ProgressNotification) {
				System.out.println("Preloader Log Progress: " + ((Preloader.ProgressNotification) notification).getProgress());
			} else if (notification instanceof Preloader.StateChangeNotification) {
				System.out.println("Preloader Stage Change: " + ((Preloader.StateChangeNotification) notification).getType());
			} else if (notification instanceof Preloader.ErrorNotification) {
				System.out.println("Preloader Error: " + notification);
			}
		}
		notifyPreloader(notification);
	}

	@Override
	public void stop() throws Exception {
		ApplicationDataManager.getInstance().askSaveAll();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//load this stuff first
		this.primaryStage = primaryStage;
		Thread.currentThread().setName("Arma Dialog Creator JavaFX Thread");
		primaryStage.setOnCloseRequest(new ArmaDialogCreatorWindowCloseEvent());
		primaryStage.getIcons().add(Images.IMAGE_ADC_ICON);
		primaryStage.setTitle(Lang.Application.APPLICATION_TITLE);

		//load main window
		mainWindow = new ADCWindow(primaryStage);

		setToDarkTheme(ApplicationProperty.DARK_THEME.get(ArmaDialogCreator.getApplicationDataManager().getApplicationProperties()));

		loadNewProject(false);
	}

	@NotNull
	public static Manifest getManifest() {
		if (adcManifest == null) {
			try {
				Enumeration<URL> resources = ArmaDialogCreator.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
				while (resources.hasMoreElements()) {
					Manifest manifest = new Manifest(resources.nextElement().openStream());
					String specTitle = manifest.getMainAttributes().getValue("Specification-Title");
					if (specTitle != null && specTitle.equals("Arma Dialog Creator")) {
						adcManifest = manifest;
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return adcManifest;
	}

	@NotNull
	public static Locale getCurrentLocale() {
		return locale;
	}

	/** Closes the application after asking if user wants to save. */
	public static void closeApplication() {
		//do not execute window closing event
		Platform.exit();
	}

	public static void restartApplication(boolean askToSave) {
		try {
			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			final File currentJar = new File(ArmaDialogCreator.class.getProtectionDomain().getCodeSource().getLocation().toURI());

  		/* is it a jar file? */
			if (!currentJar.getName().endsWith(".jar")) {
				return;
			}

  		/* Build command: java -jar application.jar */
			final ArrayList<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-jar");
			command.add(currentJar.getPath());

			final ProcessBuilder builder = new ProcessBuilder(command);
			if (askToSave) {
				ApplicationDataManager.getInstance().askSaveAll();
			}
			builder.start();
			System.exit(0);
		} catch (Exception e) {
			ExceptionHandler.error(e);
		}
	}

	public static void loadNewProject() {
		loadNewProject(true);
	}

	private static void loadNewProject(boolean askToSave) {
		if (askToSave) {
			ApplicationDataManager.getInstance().askSaveAll();
		}

		getPrimaryStage().close();
		ApplicationLoader.ApplicationLoadConfig config = ApplicationLoader.getInstance().getLoadConfig();
		getApplicationDataManager().setApplicationData(config.getApplicationData());
		getMainWindow().initialize();
		getMainWindow().show();
		getMainWindow().getCanvasView().setTreeStructure(false, config.getNewTreeStructureMain());
		getMainWindow().getCanvasView().setTreeStructure(true, config.getNewTreeStructureBg());

		for (Runnable run: INSTANCE.showLater) {
			run.run();
		}
		INSTANCE.showLater.clear();
	}


	public static CanvasView getCanvasView() {
		return INSTANCE.mainWindow.getCanvasView();
	}

	public static Stage getPrimaryStage() {
		return INSTANCE.primaryStage;
	}

	public static ADCWindow getMainWindow() {
		return INSTANCE.mainWindow;
	}

	public static void setToDarkTheme(boolean set) {
		final String darkTheme = "/com/kaylerrenslow/armaDialogCreator/gui/fx/dark.css";
		if (set) {
			CanvasViewColors.EDITOR_BG = CanvasViewColors.DARK_THEME_EDITOR_BG;
			CanvasViewColors.GRID = CanvasViewColors.DARK_THEME_GRID;
			INSTANCE.primaryStage.getScene().getStylesheets().add(darkTheme);
		} else {
			CanvasViewColors.EDITOR_BG = CanvasViewColors.DEFAULT_EDITOR_BG;
			CanvasViewColors.GRID = CanvasViewColors.DEFAULT_GRID;
			INSTANCE.primaryStage.getScene().getStylesheets().remove(darkTheme);
		}
		if (getCanvasView() != null) {
			getCanvasView().updateCanvas();
		}
		getApplicationDataManager().getApplicationProperties().put(ApplicationProperty.DARK_THEME, set);
		getApplicationDataManager().saveApplicationProperties();
	}

	public static ApplicationDataManager getApplicationDataManager() {
		return INSTANCE.applicationDataManager;
	}

	public static ApplicationData getApplicationData() {
		return INSTANCE.applicationDataManager.getApplicationData();
	}

	/** Run the given runnable on the JavaFX thread after the application's main window has been initialized */
	public static void showAfterMainWindowLoaded(@NotNull Runnable runnable) {
		INSTANCE.showLater.add(runnable);
	}

	public static Parameters getLaunchParameters() {
		return INSTANCE.getParameters();
	}

	public static boolean containsUnamedLaunchParameter(@NotNull ProgramArgument argument) {
		return getLaunchParameters().getUnnamed().contains(argument.getArgKey());
	}

	private static void initializeCurrentLocale() {
		locale = ApplicationProperty.LOCALE.get(ApplicationDataManager.getApplicationProperties());
	}

	/** Set the Locale of Arma Dialog Creator to a new locale. This will require a restart to fully take into affect. */
	public static void setLocale(@NotNull Locale locale) {
		ArmaDialogCreator.locale = locale;
		ApplicationProperty.LOCALE.put(ApplicationDataManager.getApplicationProperties(), locale);
		ApplicationDataManager.getInstance().saveApplicationProperties();
	}


	private static class ArmaDialogCreatorWindowCloseEvent implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			/*we want to keep the Arma Dialog Creator window still open when asking to save progress before exiting.
			Consuming the event will keep window open and then we call closeApplication to execute the closing procedure and in turn, close the window*/
			event.consume();
			closeApplication();
		}
	}
}
