package com.tiem625.modemarker.controller

import com.tiem625.modemarker.data.SpriteSheetMetaInfo
import javafx.scene.image.Image
import javafx.util.StringConverter
import tornadofx.*
import java.io.File

class MainViewController: Controller() {

    val loadedSheetInfo: SpriteSheetMetaInfo = SpriteSheetMetaInfo()
    var loadedImage: Image? = null

    fun convertSpriteDimensions(sheetInfo: SpriteSheetMetaInfo): String {

        val dimensions = sheetInfo.getSpriteDimensions()
        return "(${dimensions.width}x${dimensions.height})"
    }






    fun loadImage(file: File) {

        loadedImage = Image(file.inputStream(), 0.0, 0.0, true, true)

        loadedImage?.let {

            loadedSheetInfo.apply {
                sheetWidth = it.width
                sheetHeight = it.height
                sheetRows = 1
                sheetCols = 1
                sheetSpriteSpacingX = 0
                sheetSpriteSpacingY = 0
            }

        }
    }

}