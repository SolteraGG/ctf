package com.dumbdogdiner.ctf.command

import dev.jorel.commandapi.annotations.Command
import dev.jorel.commandapi.annotations.Default
import org.bukkit.command.CommandSender

@Command("ctf")
object Ctf {
	@Default
	@JvmStatic
	fun warp(sender: CommandSender) {
		sender.sendMessage("--- CTF Help ---")
		sender.sendMessage("/warp - Show this help")
		sender.sendMessage("/warp <warp> - Teleport to <warp>")
		sender.sendMessage("/warp create <warpname> - Creates a warp at your current location")
	}
}