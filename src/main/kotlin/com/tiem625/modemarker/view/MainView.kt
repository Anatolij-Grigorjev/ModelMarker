package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Version
import javafx.application.Platform
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import tornadofx.*

class MainView : View("Model Marker v${Version.versionString} (tm)") {

    private fun mainPlus(keyCode: KeyCode): KeyCombination =
            KeyCodeCombination(keyCode, KeyCodeCombination.SHORTCUT_DOWN)

    override val root = borderpane {
        //menu bar on the top
        top {

            useMaxWidth = true
            menubar {
                menu("File") {

                    item("New", mainPlus(KeyCode.N))
                    item("Open", mainPlus(KeyCode.O))
                    item("Save", mainPlus(KeyCode.S))
                    separator()
                    item("Exit", mainPlus(KeyCode.Q)).setOnAction {

                        Platform.exit()
                    }
                }
            }
        }

        //image parameters on the left
        left {

        }

        //status bar on the bottom
        bottom {

        }

        //image grid in the center
        center {

        }
    }
}