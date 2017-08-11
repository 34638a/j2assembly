package com.j2assembly.example;

import com.j2assembly.resources.Clock;
import com.j2assembly.resources.Pin;
import com.j2assembly.resources.Port;

import java.io.File;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public class ARDUINOJ_BLINK {


	public static void main() {

		Port.define('D', new int[]{2,3,4,5,6,11,12,13});
		Pin.setPinType(Pin.PinType.Write);

	}

	public static void loop() {
		Clock.delay(1000);
		Pin.setPin(13, Pin.HIGH);
		Clock.delay(1000);
		Pin.setPin(13, Pin.LOW);
	}

}
