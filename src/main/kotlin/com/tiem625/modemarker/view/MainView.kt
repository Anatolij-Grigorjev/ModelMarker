package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.app.Version
import javafx.application.Platform
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.GridPane
import tornadofx.*

class MainView : View("Model Marker v${Version.versionString} (tm)") {

    private fun mainPlus(keyCode: KeyCode): KeyCombination =
            KeyCodeCombination(keyCode, KeyCodeCombination.SHORTCUT_DOWN)

    lateinit var spriteSizeLbl: Label
    lateinit var selectedRowColLbl: Label

    lateinit var imgGridPane: GridPane

    override val root = borderpane {

        minWidth = 640.0
        minHeight = 480.0

        //menu bar on the top
        top {

            useMaxWidth = true
            menubar {
                menu("File") {

                    item("New...", mainPlus(KeyCode.N))
                    item("Open...", mainPlus(KeyCode.O))
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
            hbox {

                addClass(Styles.imgMeta)
                form {
                    fieldset("Image Info", labelPosition = Orientation.VERTICAL) {

                        addClass(Styles.heading)

                        field("Image Width:") {
                            textfield {
                                isEditable = false
                                addClass(Styles.noEdit)
                            }
                        }
                        field ("Image Height:") {
                            textfield {
                                isEditable = false
                                addClass(Styles.noEdit)
                            }
                        }
                        field("Spritesheet Rows:"){
                            textfield()
                        }
                        field("Spritesheet Columns:") {
                            textfield()
                        }
                        field("Sprite Padding X:") {
                            textfield()
                        }
                        field("Sprite Padding Y:") {
                            textfield()
                        }
                    }
                }

            }
        }

        //status bar on the bottom
        bottom {

            hbox {
                addClass(Styles.statusBar)

                spriteSizeLbl = label("Sprite dimensions: ")
                separator(Orientation.VERTICAL)
                selectedRowColLbl = label("Selected row/col: ")
                separator(Orientation.VERTICAL)
            }
        }

        //image grid in the center
        center {

            imgGridPane = gridpane {

                addClass(Styles.imgPane)

            }
        }
    }
}