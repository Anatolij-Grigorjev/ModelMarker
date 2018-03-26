package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.app.Version
import com.tiem625.modemarker.controller.MainViewController
import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import tornadofx.*
import java.util.concurrent.Callable

class MainView : View("Model Marker v${Version.versionString} (tm)") {

    private fun mainPlus(keyCode: KeyCode): KeyCombination =
            KeyCodeCombination(keyCode, KeyCodeCombination.SHORTCUT_DOWN)

    lateinit var spriteSizeLbl: Label
    lateinit var selectedRowColLbl: Label

    val mainViewController: MainViewController by inject()

    val fileChooser = FileChooser().apply {
        this.extensionFilters.addAll(listOf(
                FileChooser.ExtensionFilter("Image files", listOf(
                        "*.bmp",
                        "*.jpg",
                        "*.jpeg",
                        "*.png"
                )),
                FileChooser.ExtensionFilter("All files", "*.*")
        ))

    }

    lateinit var imgGridPane: GridPane

    override val root = borderpane {

        minWidth = 640.0
        minHeight = 480.0

        //menu bar on the top
        top {

            useMaxWidth = true
            menubar {
                menu("File") {

                    item("New...", mainPlus(KeyCode.N)).setOnAction {

                        fileChooser.showOpenDialog(currentWindow)?.let {

                            mainViewController.loadImage(it)
                        }
                    }
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
                                bind(mainViewController.loadedSheetInfo.sheetWidthProperty())
                            }
                        }
                        field ("Image Height:") {
                            textfield {
                                isEditable = false
                                addClass(Styles.noEdit)
                                bind(mainViewController.loadedSheetInfo.sheetHeightProperty())
                            }
                        }
                        field("Spritesheet Rows:"){
                            textfield {

                                bind(mainViewController.loadedSheetInfo.sheetRowsProperty())
                            }
                        }
                        field("Spritesheet Columns:") {
                            textfield {

                                bind(mainViewController.loadedSheetInfo.sheetColsProperty())
                            }
                        }
                        field("Sprite Spacing X:") {
                            textfield {

                                bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingXProperty())
                            }
                        }
                        field("Sprite Spacing Y:") {
                            textfield {

                                bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingYProperty())
                            }
                        }
                    }
                }

            }
        }

        //status bar on the bottom
        bottom {

            hbox {
                addClass(Styles.statusBar)

                label("Sprite dimensions: ")
                spriteSizeLbl = label {

                    mainViewController.loadedSheetInfo.let {

                        val binding = Bindings.createStringBinding({

                            mainViewController.convertSpriteDimensions(it)
                        }, arrayOf(
                                it.sheetWidthProperty(),
                                it.sheetHeightProperty(),
                                it.sheetRowsProperty(),
                                it.sheetColsProperty(),
                                it.sheetSpriteSpacingXProperty(),
                                it.sheetSpriteSpacingYProperty())
                        )

                        textProperty().bind(binding)
                    }
                }
                separator(Orientation.VERTICAL)
                label("Selected row/col: ")
                selectedRowColLbl = label()
                separator(Orientation.VERTICAL)
            }
        }

        //image grid in the center
        center {
            hbox {

                addClass(Styles.imgPaneContainer)

                imgGridPane = gridpane {

                    addClass(Styles.imgPane)

                    backgroundProperty().bind(Bindings.createObjectBinding(Callable<Background> {
                        mainViewController.loadedImage?.let {
                            Background(BackgroundImage(
                                    it,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    BackgroundSize.DEFAULT
                            ))
                        } ?: Background(BackgroundFill(
                                Color.GREY, CornerRadii(1.0), Insets.EMPTY
                        ))
                    }, mainViewController.loadedImageProperty()))

                }
            }
        }
    }
}