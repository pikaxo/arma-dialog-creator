package com.kaylerrenslow.armaDialogCreator.control;

import com.kaylerrenslow.armaDialogCreator.util.DataContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 A custom control class has two things: a specification and a implementation. The specification, provided by a {@link ControlClassSpecification} instance, is a way to construct the implementation,
 which is a {@link ControlClass} instance. There will only be one {@link ControlClass} instance per {@link CustomControlClass} instance and the {@link ControlClass} changes will be tracked
 (when an update happens via {@link ControlClass#getPropertyUpdateGroup()}, the {@link ControlClassSpecification} will update as well).
 This update tracking will only happen between this class instance and {@link #getControlClass()}.
 Editing the {@link ControlClassSpecification} will not change the {@link ControlClass} implementation.

 @author Kayler
 @since 11/13/2016 */
public class CustomControlClass {
	private final ControlClass controlClass;
	private final DataContext programData = new DataContext();
	private String comment;


	/**
	 Construct a new custom control class with the given {@link ControlClass} instance. The given instance will be the underlying instance for {@link #getControlClass()} and a new
	 {@link ControlClassSpecification} will be created with the instance via {@link ControlClassSpecification(ControlClass)}.
	 * @param controlClass instance to use

	 */
	public CustomControlClass(@NotNull ControlClass controlClass) {
		this.controlClass = controlClass;
	}

	/**
	 Construct a new custom control class with the given specification

	 @param specification specification to use
	 */
	public CustomControlClass(@NotNull ControlClassSpecification specification, @NotNull SpecificationRegistry registry) {
		controlClass = specification.constructNewControlClass(registry);
	}

	/** Get the {@link ControlClass} instance. This instance will remain constant. */
	@NotNull
	public ControlClass getControlClass() {
		return controlClass;
	}

	/** Some information provided by the user about the custom control */
	@Nullable
	public String getComment() {
		return comment;
	}

	/**
	 @param comment information about the custom control
	 */
	public void setComment(@Nullable String comment) {
		this.comment = comment;
	}

	/** @return a new instance of {@link ControlClassSpecification} equal to {@link #getControlClass()} */
	@NotNull
	public ControlClassSpecification newSpecification() {
		return new ControlClassSpecification(this.controlClass);
	}

	@Override
	public int hashCode() {
		return controlClass.getClassName().hashCode();
	}

	@Override
	public String toString() {
		return controlClass.getClassName();
	}

	/** Get a {@link DataContext} instance that stores random things. */
	@NotNull
	public DataContext getUserData() {
		return programData;
	}
}
