package com.tiem625.modemarker.controller

import com.tiem625.modemarker.data.sprite.RectModel
import com.tiem625.modemarker.data.sprite.SpriteModel
import javafx.scene.image.Image
import tornadofx.*

class EditSpriteViewController: Controller() {

    val spriteModel: SpriteModel = SpriteModel()
    val rectModel: RectModel = RectModel()

    var loadedImage: Image? by property()
    fun loadedImageProperty() = getProperty(EditSpriteViewController::loadedImage)

    var selectedCell: Pair<Int, Int>? by property()
    fun selectedCellProperty() = getProperty(MainViewController::selectedCell)


}