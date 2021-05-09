/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf

interface WithPlugin {
    val instance
        get() = FoxCapturePlugin.instance
}
