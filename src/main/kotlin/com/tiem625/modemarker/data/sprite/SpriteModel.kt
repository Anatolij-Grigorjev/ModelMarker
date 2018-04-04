package com.tiem625.modemarker.data.sprite

import tornadofx.*

class SpriteModel {

    var spriteRowIdx: Int by property(0)
    fun spriteRowIdxProperty() = getProperty(SpriteModel::spriteRowIdx)

    var spriteColIdx: Int by property(0)
    fun spriteColIdxProperty() = getProperty(SpriteModel::spriteColIdx)

    var spriteName: String by property("")
    fun spriteNameProperty() = getProperty(SpriteModel::spriteName)

}