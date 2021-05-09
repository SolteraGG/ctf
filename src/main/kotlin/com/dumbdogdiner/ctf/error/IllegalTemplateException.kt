package com.dumbdogdiner.ctf.error

import java.lang.RuntimeException

/**
 * Represents an exception thrown when a template of the target name could not be found.
 */
class IllegalTemplateException(name: String) : RuntimeException() {
	override val message = "A template world with name '$name' does not exist"
}
