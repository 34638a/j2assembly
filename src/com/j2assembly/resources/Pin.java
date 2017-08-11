package com.j2assembly.resources;

/**
 * Created by Jordan Laptop on 11/08/2017.
 */
public class Pin {

	public static boolean HIGH = true;
	public static boolean LOW = false;

	public static void setPin(int pin, boolean value){}
	public static boolean readPin(int pin){return true;}

	public static void setPinType(PinType pinType){}

	public enum PinType {
		Read,
		Write
	}
}
