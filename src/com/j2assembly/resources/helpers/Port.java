package com.j2assembly.resources.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan Laptop on 11/08/2017.
 */
public class Port {

	public static boolean READ = false, WRITE = true;

	public static List<Port> ports = new ArrayList<>();

	public static Port define(char name, int[] pins, boolean[] map){
		for (Port port : ports) {
			for (int i = 0; i < pins.length; i++) {
				for (int o = 0; o < pins.length; o++) {
					if (port.pins[o] == pins[i]) {
						return null;
					}
				}
			}
		}
		ports.add(new Port(name, pins, map));
		return ports.get(ports.size()-1);
	}


	private char name;
	private int[] pins;
	private boolean[] map;

	private Port (char name, int[] pins, boolean[] map) {
		this.name = name;
		this.pins = pins;
		this.map = map;
	}

	/**
	 * Getter for property 'name'.
	 *
	 * @return Value for property 'name'.
	 */
	public char getName() {
		return name;
	}

	/**
	 * Getter for property 'pins'.
	 *
	 * @return Value for property 'pins'.
	 */
	public int[] getPins() {
		return pins;
	}

	/**
	 * Getter for property 'map'.
	 *
	 * @return Value for property 'map'.
	 */
	public boolean[] getMap() {
		return map;
	}
}
