package com.kaylerrenslow.armaDialogCreator.gui.main.controlPropertiesEditor;

import com.kaylerrenslow.armaDialogCreator.control.sv.Expression;
import com.kaylerrenslow.armaDialogCreator.control.sv.SVString;
import com.kaylerrenslow.armaDialogCreator.control.sv.SerializableValue;
import com.kaylerrenslow.armaDialogCreator.expression.Env;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.ExpressionChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputFieldDataChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.StringChecker;
import com.kaylerrenslow.armaDialogCreator.util.ReadOnlyValueObserver;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

/**
 Created by Kayler on 07/13/2016.
 */
public abstract class InputFieldValueEditor<V extends SerializableValue> implements ValueEditor<V> {
	protected final InputField<? extends InputFieldDataChecker, V> inputField;
	private final StackPane masterPane;
	private final InputField<StringChecker, String> overrideField = new InputField<>(new StringChecker());

	public InputFieldValueEditor(@NotNull InputFieldDataChecker<V> dataChecker) {
		this.inputField = new InputField<>(dataChecker);
		this.masterPane = new StackPane(inputField);
	}

	@Override
	public void submitCurrentData() {
		inputField.submitValue();
	}

	@Override
	public V getValue() {
		return inputField.getValue();
	}

	@Override
	public void setValue(V val) {
		inputField.setValue(val);
	}

	@Override
	public @NotNull Node getRootNode() {
		return masterPane;
	}

	@Override
	public void setToCustomData(boolean override) {
		masterPane.getChildren().clear();
		if (override) {
			masterPane.getChildren().add(overrideField);
		} else {
			masterPane.getChildren().add(inputField);
		}
	}

	@Override
	public InputField<StringChecker, String> getCustomDataTextField() {
		return overrideField;
	}
	
	@Override
	public void focusToEditor() {
		inputField.requestFocus();
	}
		
	@Override
	public boolean displayFullWidth() {
		return true;
	}

	@Override
	public ReadOnlyValueObserver<V> getReadOnlyObserver() {
		return inputField.getValueObserver().getReadOnlyValueObserver();
	}

	public static class IntegerEditor extends InputFieldValueEditor<Expression> {
		public IntegerEditor(@NotNull Env env) {
			super(new ExpressionChecker(env, ExpressionChecker.TYPE_INT));
		}
	}

	public static class DoubleEditor extends InputFieldValueEditor<Expression> {
		public DoubleEditor(@NotNull Env env) {
			super(new ExpressionChecker(env, ExpressionChecker.TYPE_FLOAT));
		}
	}

	public static class ArmaStringEditor extends InputFieldValueEditor<SVString>{
		public ArmaStringEditor() {
			super(new SVArmaStringChecker());
		}
	}

}
