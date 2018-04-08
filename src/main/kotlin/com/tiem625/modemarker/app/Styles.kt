package com.tiem625.modemarker.app

import com.tiem625.modemarker.styles.EditSpriteViewStyles
import com.tiem625.modemarker.styles.MainViewStyles
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {

    companion object {
        val styleClasses = listOf(
                MainViewStyles::class,
                EditSpriteViewStyles::class
        )

        val imgPaneContainer by cssclass()
        val outerContainer by cssclass()
    }

        init {

            outerContainer {

                minHeight = 500.px
                minWidth = 800.px
            }

            imgPaneContainer {

                minWidth = 100.percent
                minHeight = 100.percent

                padding = box(5.em)
                alignment = Pos.CENTER
                backgroundColor = multi(Color.GREY)
            }
        }
}