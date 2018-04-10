package com.tiem625.modemarker.controller

import com.tiem625.modemarker.data.SpriteSheetMetaInfo
import javafx.collections.ObservableList
import javafx.scene.image.Image
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.ConstraintsBase
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import tornadofx.*
import java.io.File

class MainViewController: Controller() {

    val loadedSheetInfo: SpriteSheetMetaInfo = SpriteSheetMetaInfo()
    var loadedImage: Image? by property()
    var selectedCell: Pair<Int, Int>? by property()

    val editSpriteViewController: EditSpriteViewController by inject()

    fun loadedImageProperty() = getProperty(MainViewController::loadedImage)
    fun selectedCellProperty() = getProperty(MainViewController::selectedCell)

    fun convertSpriteDimensions(sheetInfo: SpriteSheetMetaInfo): String {

        val dimensions = sheetInfo.getSpriteDimensions()
        return "(${dimensions.width}x${dimensions.height})"
    }

    fun loadImage(file: File) {

        loadedImage = Image(
                file.inputStream(),
                0.0,
                0.0,
                false,
                true
        )

        loadedImage?.let {

            loadedSheetInfo.apply {
                loadedSheetInfo.spriteSheetName = file.nameWithoutExtension
                sheetWidth = it.width
                sheetHeight = it.height
                sheetRows = 1
                sheetCols = 1
                sheetSpriteSpacingX = 0
                sheetSpriteSpacingY = 0
            }

        }
    }


    fun callEditSelection() {

        if (loadedImage != null && selectedCell != null) {

            editSpriteViewController.prepareEdit(loadedImage!!, loadedSheetInfo, selectedCell!!)

        }
    }

}