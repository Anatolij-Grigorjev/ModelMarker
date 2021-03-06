package com.tiem625.modemarker.styles

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class MainViewStyles: Stylesheet() {

    companion object {

        val statusBar by cssclass()
        val info by cssclass()
        val imgMeta by cssclass()
        val heading by cssclass()
        val btnHolder by cssclass()
        val imgPane by cssclass()
        val noEdit by cssclass()
        val gridCellButton by cssclass()
        val selectedGridCellButton by cssclass()
    }

    init {
        imgMeta {

            padding = box(0.em, 2.em)

            fieldset {

                btnHolder {

                    alignment = Pos.BOTTOM_CENTER

                    minHeight = 4.em

                    button {

                        padding = box(0.5.em, 0.em)
                        maxWidth = Double.MAX_VALUE.px
                    }
                }

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
            spacing = 0.5.em

            prefWidth = 100.percent

            borderColor = multi(box(Paint.valueOf("grey")))

            label {

                prefWidth = 15.em
                alignment = Pos.CENTER_RIGHT
                info {

                    alignment = Pos.CENTER_LEFT
                }
                borderColor = multi(box(Color.BLACK))
                borderWidth = multi(box(1.px))
            }
        }

        imgPane {

            minWidth = 100.px
            minHeight = 100.px
            gridLinesVisible = true

            backgroundColor = multi(Color.TRANSPARENT)
        }

        gridCellButton {

            backgroundColor = multi(Color.TRANSPARENT)
            borderWidth = multi(box(2.px))
            borderColor = multi(box(Color.GREEN))
            maxWidth = Double.MAX_VALUE.px
            maxHeight = Double.MAX_VALUE.px
        }

        selectedGridCellButton {

            backgroundColor = multi(Color(0.0, 0.0, 0.5, 0.25))
        }

    }
}