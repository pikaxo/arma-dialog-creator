package com.kaylerrenslow.armaDialogCreator.util;

import org.jetbrains.annotations.NotNull;

/**
 @author Kayler
 Used for converting a String[] into a given type.
 Created on 07/29/2016. */
public interface ValueConverter<T> {
	/** Convert a String array into type T */
	T convert(@NotNull DataContext context, @NotNull String... values) throws Exception;
}