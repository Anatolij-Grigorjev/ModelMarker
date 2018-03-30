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

    fun loadedImageProperty() = getProperty(MainViewController::loadedImage)

    fun convertSpriteDimensions(sheetInfo: SpriteSheetMetaInfo): String {

        val dimensions = sheetInfo.getSpriteDimensions()
        return "(${dimensions.width}x${dimensions.height})"
    }

    fun loadImage(file: File) {

        loadedImage = Image(file.inputStream(), 0.0, 0.0, true, true)

        loadedImage?.let {

            loadedSheetInfo.apply {
                sheetWidthProperty().set(it.width)
                sheetHeightProperty().set(it.height)
                sheetRowsProperty().set(1)
                sheetColsProperty().set(1)
                sheetSpriteSpacingXProperty().set(0)
                sheetSpriteSpacingYProperty().set(0)
            }

        }
    }



}