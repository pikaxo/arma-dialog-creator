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

import com.kaylerrenslow.armaDialogCreator.control.ControlClass;
import com.kaylerrenslow.armaDialogCreator.gui.fx.control.ComboBoxMenuButton;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 Created by Kayler on 10/19/2016.
 */
public class ControlClassMenuButton extends ComboBoxMenuButton<ControlClass> {
	@SafeVarargs
	public ControlClassMenuButton(String placeholderText, Node placeholderGraphic, CBMBMenuItem<ControlClass>[]... classGroups) {
		super(placeholderText, placeholderGraphic, classGroups);
	}

	public static CBMBMenuItem<ControlClass> newItem(@NotNull ControlClass value, @Nullable ImageView graphic) {
		return new ControlClassMenuItem(value, graphic);
	}

	public static CBMBMenuItem<ControlClass> newItem(@NotNull ControlClass value) {
		return newItem(value, null);
	}

	public static class ControlClassMenuItem extends CBMBMenuItem<ControlClass> {

		public ControlClassMenuItem(ControlClass value, @Nullable ImageView graphic) {
			super(value, graphic);
		}

		public ControlClassMenuItem(ControlClass value) {
			super(value);
		}
	}
}
