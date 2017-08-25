package com.j2assembly.example;

import com.j2assembly.compiler.JCompiler;
import com.j2assembly.resources.helpers.Clock;
import com.j2assembly.resources.helpers.Pin;
import com.j2assembly.resources.helpers.Port;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public class ARDUINOJ_BLINK {

	public static void main() {
		JCompiler.include(
						"#include <avr/io.h>" +
						"#include <util/delay.h>"
		);

		//Clock.define(16, Clock.ClockScalar.MHz);
		Port.define('B', new int[]{14,15,16,17,18,19,9,10}, new boolean[]{Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE});

		while (true) {
			CLOCK_LOOP();
		}
	}

	public static void CLOCK_LOOP() {
		Clock.delay(2000);
		Pin.setPin(19, Pin.HIGH);
		Clock.delay(100);
		Pin.setPin(19, Pin.LOW);

		//if (true) {
		//	Pin.setPin(13, Pin.LOW);
		//}
	}

}
