package com.j2assembly.resources.chip;

import com.j2assembly.resources.helpers.Port;

/**
 * Created by Jordan Laptop on 23/08/2017.
 */
public enum CommonChips {
	ATMEGA328P(new Chip("atmega328p", 16000000l, new Port[]{
			Port.define('B', new int[]{14,15,16,17,18,19,9,10}, new boolean[8]),
			Port.define('C', new int[]{23,24,25,26,27,28,1,21}, new boolean[8]),
			Port.define('D', new int[]{2,3,4,5,6,11,12,13}, new boolean[8]),

	}));


	private Chip chip;

	CommonChips(Chip chip) {
		this.chip = chip;
	}

	/**
	 * Getter for property 'chip'.
	 *
	 * @return Value for property 'chip'.
	 */
	public Chip getChip() {
		return chip;
	}
}
