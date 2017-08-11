package com.j2assembly.resources;

/**
 * Created by Jordan Laptop on 9/08/2017.
 */
public class Clock {

	private int speed;
	public enum ClockScalar {
		Hz (1),
		kHz (1000),
		MHz (1000000),
		GHz (1000000000);

		private int scalar;

		ClockScalar(int scalar) {
			this.scalar = scalar;
		}

		/**
		 * Getter for property 'scalar'.
		 *
		 * @return Value for property 'scalar'.
		 */
		public int getScalar() {
			return scalar;
		}
	}

	public Clock(int speed, ClockScalar clockScalar) {
		this.speed = speed * clockScalar.getScalar();
	}


}
