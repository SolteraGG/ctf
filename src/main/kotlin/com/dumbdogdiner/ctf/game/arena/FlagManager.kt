package com.dumbdogdiner.ctf.game.arena

import com.dumbdogdiner.ctf.game.team.Team
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

/**
 * Manages flags within an arena.
 */
class FlagManager(private val: Arena) : Listener {
	private val flags = hashMapOf<Team, Flag>()

	@EventHandler
	fun onInteractWithEntity(e: PlayerInteractEntityEvent) {

	}
}