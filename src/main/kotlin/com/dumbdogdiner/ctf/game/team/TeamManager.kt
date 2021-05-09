package com.dumbdogdiner.ctf.game.team

import com.dumbdogdiner.ctf.game.Round
import org.bukkit.entity.Player

class TeamManager(private val round: Round) {
	/**
	 * A hashmap of teams by their color.
	 */
	private val teams = hashMapOf<Team.Color, Team>()
	init {
		// initialize all available teams.
		for (color in Team.Color.values()) {
			teams[color] = Team(color)
		}
	}

	/**
	 * @return The next team a player should be added to.
	 */
	fun getNextBalancedTeam(): Team {
		// set up variables
		// default to the red team - this shouldn't affect player distribution
		var smallestTeam = teams[Team.Color.RED]!!
		var smallestPlayerCount = Int.MAX_VALUE
		// iterate over all teams and find the smallest
		for (team in this.teams.values) {
			if (team.playerCount < smallestPlayerCount) {
				smallestTeam = team
				smallestPlayerCount = team.playerCount
			}
		}
		// return the smallest team
		return smallestTeam
	}

	/**
	 * Add a player to the next team.
	 */
	fun joinPlayer(player: Player): Team {
		// find the next team to join
		val team = this.getNextBalancedTeam()
		team.addPlayer(player)
		return team
	}
}
