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
                sheetWidth = it.width
                sheetHeight = it.height
                sheetRows = 1
                sheetCols = 1
                sheetSpriteSpacingX = 0
                sheetSpriteSpacingY = 0
            }

        }
    }

    fun addGridListeners(gridPane: GridPane) {

        loadedSheetInfo.sheetRowsProperty().addListener { observable, oldValue, newValue ->

            refreshConstraints(newValue, gridPane.rowConstraints, { newN ->
                RowConstraints().apply {
                    percentHeight = 100.0 / newN
                }
            })
        }

        loadedSheetInfo.sheetColsProperty().addListener({ observable, oldValue, newValue ->

            refreshConstraints(newValue, gridPane.columnConstraints, { newN ->
                ColumnConstraints().apply {
                    percentWidth = 100.0 / newN
                }
            })
        })
    }

    private fun <T: ConstraintsBase> refreshConstraints(newN: Int, constraintsList: ObservableList<T>, creator: (Int) -> T) {

        if (gridChangeOk(newN)) {

            constraintsList.clear()

            (0 until newN).forEach {

                constraintsList.add(creator(newN))
            }
        }
    }

    private fun gridChangeOk(newValue: Int) =
            newValue >= 1 && loadedImage != null

}