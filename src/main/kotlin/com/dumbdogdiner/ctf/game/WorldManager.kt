package com.dumbdogdiner.ctf.game

import com.dumbdogdiner.ctf.WithPlugin
import com.dumbdogdiner.ctf.error.IllegalTemplateException
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.File
import java.lang.RuntimeException
import java.util.UUID

/**
 * Manages game worlds.
 */
class WorldManager : WithPlugin {
	private val serverFolder = this.instance.dataFolder.parentFile.parentFile

	// world stores
	val worlds = mutableSetOf<World>()
	val templateWorlds = mutableSetOf<World>()

	/**
	 * Add a template world.
	 */
	fun addTemplateWorld(world: World) {
		this.templateWorlds.add(world)
	}

	/**
	 * Attempt to find a template world with the target name.
	 */
	fun getTemplateWorld(name: String): World? {
		return this.templateWorlds.find { it.name == name }
	}

	/**
	 * Create a new world from the template.
	 */
	fun createWorldFromTemplate(name: String): World {
		val template = this.getTemplateWorld(name) ?: throw IllegalTemplateException(name)
		// create the new world folder
		val worldName = UUID.randomUUID().toString()
		val worldFolder = File(serverFolder, worldName)
		if (!worldFolder.mkdir()) {
			throw RuntimeException("Failed to create world folder for world '$worldName' using template '$name'")
		}
		// copy the template to the new world folder
		val success = serverFolder.resolveSibling(template.name).copyRecursively(worldFolder, true)
		if (!success) {
			throw RuntimeException("Failed to copy world data for world '$worldName' using template '$name'")
		}
		// load the world
		val world = Bukkit.createWorld(WorldCreator(worldName))
			?: throw RuntimeException("Failed to load world '$worldName' using template '$name'")
		// store a reference to world in memory
		this.worlds.add(world)
		return world
	}

	/**
	 * Discard the target world. This method should be called when the world is done with.
	 */
	fun discardWorld(world: World) {
		if (!this.worlds.contains(world)) {
			return this.instance.logger.warning("Attempted to delete world '${world.name}' that is not managed by CTF")
		}
		// unload the target world
		Bukkit.unloadWorld(world, false)
		// look up the world folder
		val worldDir = this.serverFolder.resolveSibling(world.name)
		// check if it doesn't exist
		if (!worldDir.exists() || !worldDir.deleteRecursively()) {
			return this.instance.logger.warning("Failed to delete world '${world.name}'")
		}
	}
}
