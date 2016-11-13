/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.control;

import org.jetbrains.annotations.NotNull;

/**
 @author Kayler
 Provides the specification for a control class that says what sub-classes are required/optional and what properties are required/optional.
 Since the specification shouldn't change, it is best to store the returned values for later use
 Created on 07/07/2016. */
public interface ControlClassRequirementSpecification {

	/** Trivial implementation of the interface */
	ControlClassRequirementSpecification TRIVIAL_IMPL = new ControlClassRequirementSpecification() {
	};

	/** Get all required sub-classes for the control class. Default implementation returns {@link ControlClass#EMPTY} */
	@NotNull
	default ControlClassSpecification[] getRequiredSubClasses() {
		return ControlClassSpecification.EMPTY;
	}

	/** Get all <b>optional</b> sub-classes for the control class. Default implementation returns {@link ControlClass#EMPTY} */
	@NotNull
	default ControlClassSpecification[] getOptionalSubClasses() {
		return ControlClassSpecification.EMPTY;
	}

	/** Get all required properties for the control class. Default implementation returns {@link ControlProperty#EMPTY} */
	@NotNull
	default ControlPropertyLookup[] getRequiredProperties() {
		return ControlPropertyLookup.EMPTY;
	}

	/** Get all <b>optional</b> properties for the control class. Default implementation returns {@link ControlProperty#EMPTY} */
	@NotNull
	default ControlPropertyLookup[] getOptionalProperties() {
		return ControlPropertyLookup.EMPTY;
	}
}