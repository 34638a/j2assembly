package com.j2assembly.resources.helpers;

import java.lang.annotation.Documented;

/**
 * A Java wrapper for Interrupts on a microprocessor.
 * Please consult the relevant interrupt methods that are supported by your specific micro-controller.
 */
@Documented
public @interface Interrupt {

	/**
	 * The String literal for the name of the interrupt.
	 * @return The name of the interrupt that this function should be called on.
	 */
	String interuptName();

}
