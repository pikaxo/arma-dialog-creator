package com.kaylerrenslow.armaDialogCreator.control.sv;

import com.kaylerrenslow.armaDialogCreator.control.PropertyType;
import com.kaylerrenslow.armaDialogCreator.util.DataContext;
import com.kaylerrenslow.armaDialogCreator.util.ValueConverter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 Created by Kayler on 07/13/2016.
 */
public final class SVStringArray extends SerializableValue {
	
	public static final ValueConverter<SVStringArray> CONVERTER = new ValueConverter<SVStringArray>() {
		@Override
		public SVStringArray convert(DataContext context, @NotNull String... values) throws Exception {
			return new SVStringArray(values);
		}
	};
	
	public SVStringArray(String... strings) {
		super(strings);
	}
	
	/** Set the string at index equal to s */
	public void setString(String s, int index) {
		this.valuesAsArray[index] = s;
	}
	
	public void setStrings(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			valuesAsArray[i] = strings[i];
		}
	}
	
	@Override
	public String toString() {
		String ret = "{";
		for (int i = 0; i < valuesAsArray.length; i++) {
			ret += valuesAsArray[i] + (i != valuesAsArray.length - 1 ? ", " : "}");
		}
		return ret;
	}
	
	@Override
	public SerializableValue deepCopy() {
		String[] copy = new String[valuesAsArray.length];
		System.arraycopy(valuesAsArray, 0, copy, 0, copy.length);
		return new SVStringArray(copy);
	}

	@NotNull
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.Array;
	}

	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		if(o instanceof SVStringArray){
			SVStringArray other = (SVStringArray) o;
			return Arrays.equals(this.valuesAsArray, other.valuesAsArray);
		}
		return false;
	}
	
	
}
