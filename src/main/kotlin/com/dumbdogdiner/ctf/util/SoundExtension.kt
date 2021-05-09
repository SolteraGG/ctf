package com.dumbdogdiner.ctf.util

import org.bukkit.Sound
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.Player

private fun CommandExecutor.sendSuccessSound() {
	// cannot send sound to non-console players.
	if (this !is Player) {
		return
	}
	this.playSound(this.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f)
	this.playSound(this.location, Sound.ENTITY_FOX_AMBIENT, 1f, 1f)
}
