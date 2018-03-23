package com.tiem625.modemarker.app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        fieldset {

            label {
                padding = box(1.em)
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

                }
            }
        }


    }
}