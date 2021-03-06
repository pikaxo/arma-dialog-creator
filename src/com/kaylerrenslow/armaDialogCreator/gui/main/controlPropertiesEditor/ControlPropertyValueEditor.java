package com.kaylerrenslow.armaDialogCreator.gui.main.controlPropertiesEditor;

import com.kaylerrenslow.armaDialogCreator.control.ControlProperty;
import com.kaylerrenslow.armaDialogCreator.control.Macro;
import com.kaylerrenslow.armaDialogCreator.control.sv.SerializableValue;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.StringChecker;
import com.kaylerrenslow.armaDialogCreator.util.ValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

/**
 @author Kayler
 @since 11/20/2016 */
interface ControlPropertyValueEditor extends ControlPropertyEditor {

	enum EditMode {
		/** Using default editor */
		DEFAULT,
		/** Uses a raw input field in which the user can enter anything they want. */
		CUSTOM_DATA,
		/** The control property's value is set to a macro */
		MACRO
	}

	/**
	 Updating the edit mode. When this is invoked, the proper new editor should be used.
	 <ul>
	 <li>{@link EditMode#DEFAULT} = default editor</li>
	 <li>{@link EditMode#CUSTOM_DATA} = use an editor that allows for literally any input (use a {@link TextField} for input)</li>
	 <li>{@link EditMode#MACRO} = setting the ControlProperty's value equal to a {@link Macro}</li>
	 </ul>
	 */
	void setToMode(EditMode mode);

	@NotNull Node getRootNode();

	/** Get the Class type that */
	@NotNull Class<? extends SerializableValue> getMacroClass();

	/**
	 Return true if the {@link #getRootNode()}'s width should fill the parent's width.
	 False if the width should be whatever it is initially. By default, will return false.
	 */
	default boolean displayFullWidth() {
		return false;
	}

	static InputField<StringChecker, String> modifyRawInput(InputField<StringChecker, String> rawInput, ControlProperty controlProperty) {
		rawInput.getValueObserver().addListener(new ValueListener<String>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<String> observer, String oldValue, String newValue) {
				controlProperty.setCustomDataValue(newValue);
			}
		});
		return rawInput;
	}

	/** Clear all listeners */
	void clearListeners();

	/** Initialize all listeners */
	void initListeners();

	/** Set the editor to the {@link #getControlProperty()} value. */
	void refresh();
}
