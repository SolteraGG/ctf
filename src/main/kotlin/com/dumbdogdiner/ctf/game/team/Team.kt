package com.dumbdogdiner.ctf.game.team

import org.bukkit.entity.Player

/**
 * Represents a team of players.
 */
class Team(
	/**
	 * The color of this team.
	 */
	private val color: Color
) {
	/**
	 * Represents possible team types.
	 */
	enum class Color {
		/**
		 * Represents the red team.
		 */
		RED,

		/**
		 * Represents the blue team.
		 */
		BLUE,

		/**
		 * Represents the green team.
		 */
		GREEN,

		/**
		 * Represents the yellow team.
		 */
		YELLOW
	}

	/**
	 * A set of players on this team.
	 */
	private val players = mutableSetOf<Player>()

	/**
	 * The number of players on this team.
	 */
	val playerCount: Int
		get() = this.players.size

	/**
	 * Add a player to this team.
	 */
	fun addPlayer(player: Player) {
		this.players.add(player)
	}
}
