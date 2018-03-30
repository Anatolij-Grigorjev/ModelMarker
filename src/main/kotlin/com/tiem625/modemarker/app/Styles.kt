package com.tiem625.modemarker.app

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {

        val statusBar by cssclass()
        val info by cssclass()
        val imgMeta by cssclass()
        val heading by cssclass()
        val imgPane by cssclass()
        val imgPaneContainer by cssclass()
        val noEdit by cssclass()
        val gridCellButton by cssclass()
    }

    init {
        imgMeta {

            padding = box(0.em, 2.em)

            fieldset {

                label {
                    padding = box(0.5.em, 0.em)
                    fontSize = 20.px
                    fontWeight = FontWeight.BOLD
                }

                field {

                    padding = box(0.em, 0.em, 1.em, 0.em)
                    label {

                        padding = box(0.5.em)
                        fontSize = 14.px
                        fontWeight = FontWeight.findByWeight(300)
                    }

                    textField {

                        alignment = Pos.CENTER

                        and(noEdit) {

                            textFill = Paint.valueOf("white")
                            fontWeight = FontWeight.MEDIUM
                            backgroundColor = multi(Paint.valueOf("#676767"))
                            borderColor = multi(box(Paint.valueOf("white")))
                        }
                    }
                }
            }

            borderColor = multi(box(Paint.valueOf("grey")))
        }

        statusBar {
            maxHeight = 5.em
            spacing = 1.em

            borderColor = multi(box(Paint.valueOf("grey")))

            label {

                prefWidth = 15.em
                alignment = Pos.CENTER_RIGHT
                info {

                    alignment = Pos.CENTER_LEFT
                }
            }
        }

        imgPaneContainer {

            minWidth = 100.percent
            minHeight = 100.percent

            padding = box(5.em)
            alignment = Pos.CENTER
            backgroundColor = multi(Color.GREY)
        }

        imgPane {

            minWidth = 100.px
            minHeight = 100.px
            gridLinesVisible = true

        }

        gridCellButton {

            backgroundColor = multi(Color.TRANSPARENT)
            borderWidth = multi(box(2.px))
            borderColor = multi(box(Color.GREEN))
            maxWidth = Double.MAX_VALUE.px
            maxHeight = Double.MAX_VALUE.px
        }
    }

}