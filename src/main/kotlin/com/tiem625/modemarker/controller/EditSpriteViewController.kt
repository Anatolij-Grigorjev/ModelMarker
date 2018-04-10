package com.tiem625.modemarker.controller

import com.tiem625.modemarker.data.SpriteSheetMetaInfo
import com.tiem625.modemarker.data.sprite.RectModel
import com.tiem625.modemarker.data.sprite.SpriteModel
import javafx.scene.image.Image
import tornadofx.*

class EditSpriteViewController: Controller() {

    val spriteModel: SpriteModel = SpriteModel()
    val rectModel: RectModel = RectModel()


    var loadedSheetInfo: SpriteSheetMetaInfo by property(SpriteSheetMetaInfo())
    fun loadedSheetInfoProperty() = getProperty(EditSpriteViewController::loadedSheetInfo)

    var loadedImage: Image? by property()
    fun loadedImageProperty() = getProperty(EditSpriteViewController::loadedImage)

    var selectedCell: Pair<Int, Int>? by property()
    fun selectedCellProperty() = getProperty(MainViewController::selectedCell)


    fun prepareEdit(loadedImage: Image, loadedSheetInfo: SpriteSheetMetaInfo, selectedCell: Pair<Int, Int>) {

        this.loadedImage = loadedImage
        this.selectedCell = selectedCell
        this.loadedSheetInfo = loadedSheetInfo

        spriteModel.apply {
            this.spriteName = this.spriteName ?: imgNameToSprite(loadedSheetInfo, selectedCell)
            this.spriteRowIdx = selectedCell.first
            this.spriteColIdx = selectedCell.second
        }
    }

    private fun imgNameToSprite(loadedSheetInfo: SpriteSheetMetaInfo, selectedCell: Pair<Int, Int>): String =
            selectedCell.let {
                "${loadedSheetInfo.spriteSheetName}_${it.first}_${it.second}"
            }

}