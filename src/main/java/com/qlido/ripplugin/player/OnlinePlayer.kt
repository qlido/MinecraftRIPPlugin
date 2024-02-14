package com.qlido.ripplugin.player

import java.util.UUID

data class OnlinePlayer(
    val uuid : UUID,
    var name:String,
    var prefix:String,
)