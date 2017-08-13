package com.j2assembly.example;

import com.j2assembly.compiler.JCompiler;
import com.j2assembly.resources.Clock;
import com.j2assembly.resources.Pin;
import com.j2assembly.resources.Port;

/**
 * Created by Jordan Laptop on 10/08/2017.
 */
public class ARDUINOJ_BLINK {


	public static void main() {
		JCompiler.include(
				"#include <stdlib.h>" +
				"#include <avr/io.h>" +
				"#include <util/delay.h>" +
				"#include <stdio.h>" +
				"#include <avr/interrupt.h>"
		);
		Clock.define(16000000);
		Port.define('D', new int[]{2,3,4,5,6,11,12,13}, new boolean[]{Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE});

	}

	public static void loop() {
		Clock.delay(1000);
		Pin.setPin(13, Pin.HIGH);
		Clock.delay(1000);
		Pin.setPin(13, Pin.LOW);

		if (true) {
			Pin.setPin(13, Pin.LOW);
		}

		while (true) {
			Pin.setPin(13, Pin.LOW);
		}
	}

}
