package com.kaylerrenslow.armaDialogCreator.arma.util;

import org.jetbrains.annotations.NotNull;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 @author Kayler
 @since 05/06/2017 */
public class ArmaPrecision {
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.########");
	public static final double EPSILON = 0.00000001;

	/** Return true if d1 == d2 or Math.abs(d1 - d2) < {@link #EPSILON}. */
	public static boolean isEqualTo(double d1, double d2) {
		return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
	}

	static {
		DECIMAL_FORMAT.setRoundingMode(RoundingMode.CEILING);
	}

	/** Get a String representation of the given double that is truncated and not rounded */
	@NotNull
	public static String format(double d) {
		return DECIMAL_FORMAT.format(d);
	}
}
