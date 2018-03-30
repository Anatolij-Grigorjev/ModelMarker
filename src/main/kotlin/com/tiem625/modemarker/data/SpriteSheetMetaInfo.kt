package com.tiem625.modemarker.data

import tornadofx.*

class SpriteSheetMetaInfo(
        sheetWidth: Double,
        sheetHeight: Double,
        sheetRows: Int,
        sheetCols: Int,
        sheetSpriteSpacingX: Int,
        sheetSpriteSpacingY: Int
) {

    var sheetWidth: Double by property(sheetWidth)
    fun sheetWidthProperty() = getProperty(SpriteSheetMetaInfo::sheetWidth)

    var sheetHeight: Double by property(sheetHeight)
    fun sheetHeightProperty() = getProperty(SpriteSheetMetaInfo::sheetHeight)

    var sheetRows: Int by property(sheetRows)
    fun sheetRowsProperty() = getProperty(SpriteSheetMetaInfo::sheetRows)

    var sheetCols: Int by property(sheetCols)
    fun sheetColsProperty() = getProperty(SpriteSheetMetaInfo::sheetCols)

    var sheetSpriteSpacingX: Int by property(sheetSpriteSpacingX)
    fun sheetSpriteSpacingXProperty() = getProperty(SpriteSheetMetaInfo::sheetSpriteSpacingX)

    var sheetSpriteSpacingY: Int by property(sheetSpriteSpacingY)
    fun sheetSpriteSpacingYProperty() = getProperty(SpriteSheetMetaInfo::sheetSpriteSpacingY)

    constructor():this(0.0, 0.0, 1, 1, 0, 0)

    data class SpriteDimensions(
            val width: Double,
            val height: Double
    ) {
        constructor(): this(0.0, 0.0)
    }

    fun getSpriteDimensions(): SpriteDimensions {

        return if (listOf(sheetWidth, sheetHeight, sheetRows, sheetCols).any {
                    when(it) {

                        is Double -> it <= 0.0
                        is Int -> it <= 0
                        else ->
                                throw RuntimeException()
                    }
                }) {

            SpriteDimensions()
        } else {

            SpriteDimensions(
                    width = (sheetWidth - ((sheetCols - 1) * sheetSpriteSpacingX)) / sheetCols,
                    height = (sheetHeight - ((sheetRows - 1) * sheetSpriteSpacingY)) / sheetRows
            )
        }
    }


}