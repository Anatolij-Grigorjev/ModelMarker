package com.tiem625.modemarker.app

import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {

        val statusBar by cssclass()
        val imgMeta by cssclass()
        val heading by cssclass()
        val imgPane by cssclass()
        val noEdit by cssclass()
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
                        fontWeight = FontWeight.findByWeight(350)
                    }

                    textField {

                        and(noEdit) {

                            backgroundColor = multi(Paint.valueOf("#ebe1ec"))
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

                prefWidth = 20.em
            }
        }

        imgPane {

            minWidth = 500.px
            minHeight = 400.px

            backgroundColor = multi(Paint.valueOf("grey"))
        }
    }
}