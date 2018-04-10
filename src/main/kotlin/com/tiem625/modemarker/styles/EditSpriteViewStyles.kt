package com.tiem625.modemarker.styles

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*


class EditSpriteViewStyles: Stylesheet() {

    companion object {

        val centerContainer by cssclass()
        val propsTable by cssclass()
        val spriteHeader by cssclass()
        val spriteEditScrollPane by cssclass()
        val spriteEditImgPane by cssclass()
    }

    init {

        centerContainer {

            minWidth = 60.percent
            minHeight = 60.percent
            maxWidth = 85.percent
            maxHeight = 85.percent
        }

        propsTable {


        }

        spriteHeader {

            alignment = Pos.CENTER
            padding = box(1.0.em, 0.0.em)
            spacing = 5.em
            borderColor = multi(box(Color.BLACK))
            borderWidth = multi(box(1.px))
        }

        spriteEditScrollPane {

            fitToWidth = true
            fitToHeight = true
            padding = box(3.em)
        }

        spriteEditImgPane {

            fillWidth = true
            fillHeight = true

            alignment = Pos.CENTER
            padding = box(1.em)
            borderColor = multi(box(Color.BLACK))
            borderWidth = multi(box(1.px))
        }
    }
}