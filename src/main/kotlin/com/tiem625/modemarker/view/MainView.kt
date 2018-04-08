package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.app.Version
import com.tiem625.modemarker.controller.MainViewController
import com.tiem625.modemarker.styles.MainViewStyles
import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.stage.FileChooser
import javafx.stage.StageStyle
import tornadofx.*
import java.util.concurrent.Callable

class MainView : View("Model Marker v${Version.versionString} (tm)") {

    private fun mainPlus(keyCode: KeyCode): KeyCombination =
            KeyCodeCombination(keyCode, KeyCodeCombination.SHORTCUT_DOWN)

    lateinit var spriteSizeLbl: Label
    lateinit var selectedRowColLbl: Label

    val mainViewController: MainViewController by inject()
    val imgGridPane: MainGridPane by inject()
    val editSpriteView: EditSpriteView by inject()

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


    override val root = borderpane {

        addClass(Styles.outerContainer)

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

                addClass(MainViewStyles.imgMeta)
                form {
                    fieldset("Image Info", labelPosition = Orientation.VERTICAL) {

                        addClass(MainViewStyles.heading)

                        field("Image Width:") {
                            textfield {
                                isEditable = false
                                addClass(MainViewStyles.noEdit)
                                bind(mainViewController.loadedSheetInfo.sheetWidthProperty())
                            }
                        }
                        field("Image Height:") {
                            textfield {
                                isEditable = false
                                addClass(MainViewStyles.noEdit)
                                bind(mainViewController.loadedSheetInfo.sheetHeightProperty())
                            }
                        }
                        field("Spritesheet Rows:") {
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

                        vbox {

                            addClass(MainViewStyles.btnHolder)

                            button("Edit Sprite...") {

                                enableWhen(Bindings.createBooleanBinding(Callable<Boolean> {
                                    mainViewController.selectedCell != null
                                }, mainViewController.selectedCellProperty()))

                                shortcut(mainPlus(KeyCode.E))

                                setOnAction {

                                    editSpriteView.openModal()
                                }
                            }
                        }
                    }

                }

            }
        }

        //status bar on the bottom
        bottom {

            hbox {
                addClass(MainViewStyles.statusBar)

                label("Sprite dimensions: ")
                spriteSizeLbl = label {
                    HBox.setHgrow(this, Priority.ALWAYS)
                    mainViewController.loadedSheetInfo.let {

                        textProperty().bind(Bindings.createStringBinding({

                            mainViewController.convertSpriteDimensions(it)
                        }, arrayOf(
                                it.sheetWidthProperty(),
                                it.sheetHeightProperty(),
                                it.sheetRowsProperty(),
                                it.sheetColsProperty(),
                                it.sheetSpriteSpacingXProperty(),
                                it.sheetSpriteSpacingYProperty())
                        ))
                    }
                }
                separator(Orientation.VERTICAL)
                label("Selected row/col: ")
                selectedRowColLbl = label {

                    contentDisplay = ContentDisplay.LEFT

                    textProperty().bind(Bindings.createStringBinding({

                        mainViewController.selectedCell?.let {
                            "(${it.first};${it.second})"
                        } ?: ""
                    }, arrayOf(mainViewController.selectedCellProperty())))
                }
                separator(Orientation.VERTICAL)
            }
        }

        //image grid in the center
        center {
            stackpane {
                addClass(Styles.imgPaneContainer)

                val imgView = imageview(mainViewController.loadedImageProperty()) {

                    imageProperty().addListener { obs, old, new ->
                        new?.let {

                            fitWidth = new.width
                            fitHeight = new.height
                        }
                    }
                }

                this += imgView

                this += imgGridPane.root.apply {

                    prefWidthProperty().bind(imgView.fitWidthProperty())
                    minWidthProperty().bind(imgView.fitWidthProperty())
                    maxWidthProperty().bind(imgView.fitWidthProperty())

                    prefHeightProperty().bind(imgView.fitHeightProperty())
                    minHeightProperty().bind(imgView.fitHeightProperty())
                    maxHeightProperty().bind(imgView.fitHeightProperty())
                }
            }
        }
    }
}