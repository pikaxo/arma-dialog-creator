package com.kaylerrenslow.armaDialogCreator.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 Created by Kayler on 07/05/2016.
 */
public interface UpdateGroupListener<T> {
	/**
	 Invoked when {@link UpdateListenerGroup#update(Object)} was called.

	 @param group group where the update occurred
	 @param data data that may have been passted
	 */
	void update(@NotNull UpdateListenerGroup<T> group, @Nullable T data);

}
