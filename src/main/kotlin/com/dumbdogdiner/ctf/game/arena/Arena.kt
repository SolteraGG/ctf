/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf.game.arena

import com.dumbdogdiner.ctf.game.arena.Flag
import org.bukkit.entity.Player

class Arena {
    val players = mutableListOf<Player>()
    val flags = mutableListOf<Flag>()
}
