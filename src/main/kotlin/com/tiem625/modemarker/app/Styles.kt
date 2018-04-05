package com.tiem625.modemarker.app

import com.tiem625.modemarker.styles.EditSpriteViewStyles
import com.tiem625.modemarker.styles.MainViewStyles
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {

    companion object {
        val styleClasses = listOf(
                MainViewStyles::class,
                EditSpriteViewStyles::class
        )
    }

}