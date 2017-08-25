package com.j2assembly.resources.chip;

import com.j2assembly.resources.helpers.Port;

/**
 * Created by Jordan Laptop on 23/08/2017.
 */
public class Chip {

	private String chipName;
	private long clockSpeed;
	private Port[] ports;


	public Chip(String chipName, long clockSpeed, Port[] ports) {
		this.chipName = chipName;
		this.clockSpeed = clockSpeed;
		this.ports = ports;
	}

	/**
	 * Getter for property 'chipName'.
	 *
	 * @return Value for property 'chipName'.
	 */
	public String getChipName() {
		return chipName;
	}

	/**
	 * Getter for property 'clockSpeed'.
	 *
	 * @return Value for property 'clockSpeed'.
	 */
	public long getClockSpeed() {
		return clockSpeed;
	}

	/**
	 * Getter for property 'ports'.
	 *
	 * @return Value for property 'ports'.
	 */
	public Port[] getPorts() {
		return ports;
	}
}
