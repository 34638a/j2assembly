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
				"#include <avr/interrupt.h>" +
				"#define CPU_PRESCALE(n) (CLKPR = 0x80, CLKPR = (n))" +
				"#define CPU_16MHz       0x00" +
				"#define CPU_8MHz        0x01" +
				"#define CPU_4MHz        0x02" +
				"#define CPU_2MHz        0x03" +
				"#define CPU_1MHz        0x04" +
				"#define CPU_500kHz      0x05" +
				"#define CPU_250kHz      0x06" +
				"#define CPU_125kHz      0x07" +
				"#define CPU_62kHz       0x08" +
				"#define set_clock_speed CPU_PRESCALE"
		);
		Clock.define(16, Clock.ClockScalar.MHz);
		Port.define('D', new int[]{2,3,4,5,6,11,12,13}, new boolean[]{Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE, Port.WRITE});

		while (true) {
			loop();
		}
	}

	public static void loop() {
		Clock.delay(1000);
		Pin.setPin(13, Pin.HIGH);
		Clock.delay(1000);
		Pin.setPin(13, Pin.LOW);

		//if (true) {
		//	Pin.setPin(13, Pin.LOW);
		//}
	}

}
