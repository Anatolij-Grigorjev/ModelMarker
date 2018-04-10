package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.controller.EditSpriteViewController
import com.tiem625.modemarker.styles.EditSpriteViewStyles
import com.tiem625.modemarker.styles.MainViewStyles
import javafx.beans.binding.Bindings
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.geometry.Rectangle2D
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Label
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import tornadofx.*
import java.util.concurrent.Callable

class EditSpriteView : View("Edit Sprite") {

    val editSpriteViewController: EditSpriteViewController by inject()

    lateinit var mousePosLbl: Label
    lateinit var rectangleWidthHeightLbl: Label

    override val root = borderpane {

        addClass(Styles.outerContainer)
        //sprite label and ROW/COL
        top {

            hbox {

                addClass(EditSpriteViewStyles.spriteHeader)
                vbox {
                    label("Sprite Name")
                    textfield {

                        bind(
                            Bindings.createStringBinding(Callable<String> {
                                    editSpriteViewController.spriteModel.spriteName ?: ""
                                }, editSpriteViewController.spriteModel.spriteNameProperty()
                            )
                        )
                    }
                }
                vbox {
                    label("Sprite Row Idx")
                    textfield {

                        bind(editSpriteViewController.spriteModel.spriteRowIdxProperty())
                    }
                }
                vbox {
                    label("Sprite Col Idx")
                    textfield {

                        bind(editSpriteViewController.spriteModel.spriteColIdxProperty())
                    }
                }
            }
        }

        //left is meta info about main rects + SAVE
        //if not array picked,
        //and more than one rect, they will be keys in prop object
        left {

            hbox {


                form {
                    fieldset("Rects Model", labelPosition = Orientation.VERTICAL) {

                        field("Rects Prop Name:") {
                            textfield {

                                bind(editSpriteViewController.rectModel.rectKeyNameProperty())
                            }
                        }
                        field("Rects Prop is Array:") {
                            checkbox {

                                bind(editSpriteViewController.rectModel.isPropArrayProperty())
                            }
                        }
                        field("Model Props:") {

                            tableview(editSpriteViewController.rectModel.propsTableProperty()) {

                                addClass(EditSpriteViewStyles.propsTable)
                            }
                        }

                        vbox {

                            addClass(MainViewStyles.btnHolder)

                            button("Apply Changes") {


                            }
                        }
                    }

                }

            }

        }

        //accordion with props of drawn rects
        right {

        }

        //status bar at bottom
        bottom {
            hbox {
                addClass(MainViewStyles.statusBar)

                label("Mouse pos: ")
                mousePosLbl = label {
                    HBox.setHgrow(this, Priority.ALWAYS)


                }
                separator(Orientation.VERTICAL)
                label("Rectangle (W/H): ")
                rectangleWidthHeightLbl = label {
                    contentDisplay = ContentDisplay.LEFT


                }
                separator(Orientation.VERTICAL)
            }
        }

        //image with a certain viewport on top of which to daw rectangles
        center {

            addClass(EditSpriteViewStyles.centerContainer)
            stackpane {

                addClass(Styles.imgPaneContainer)

                val imgView = imageview(editSpriteViewController.loadedImageProperty()) {

                    addClass(EditSpriteViewStyles.spriteEditImgPane)

                    imageProperty().addListener { obs, old, new ->
                        new?.let {

                            fitWidth = new.width
                            fitHeight = new.height
                        }
                    }

                    viewportProperty().bind(Bindings.createObjectBinding({

                        val dimensions = editSpriteViewController.loadedSheetInfo.getSpriteDimensions()
                        val selected = editSpriteViewController.selectedCell?:Pair(0, 0)
                        val rec =Rectangle2D(
                                dimensions.width * selected.first,
                                dimensions.height * selected.second,
                                dimensions.width,
                                dimensions.height
                        )
                        log.info("Rectangle: $rec")
                        rec

                    }, arrayOf(
                            editSpriteViewController.loadedSheetInfo.sheetWidthProperty(),
                            editSpriteViewController.loadedSheetInfo.sheetHeightProperty(),
                            editSpriteViewController.loadedSheetInfo.sheetRowsProperty(),
                            editSpriteViewController.loadedSheetInfo.sheetColsProperty(),
                            editSpriteViewController.loadedSheetInfo.sheetSpriteSpacingXProperty(),
                            editSpriteViewController.loadedSheetInfo.sheetSpriteSpacingYProperty())
                    ))
                }

                this += scrollpane {
                    addClass(EditSpriteViewStyles.spriteEditScrollPane)
                    addEventFilter(ScrollEvent.ANY, {
                        log.info("fit: ${imgView.fitWidth}|${imgView.fitHeight} ")
                        val aspect = imgView.fitWidth / imgView.fitHeight
                        if (it.deltaY > 0.0) {
                            imgView.fitWidth *= 1.1
                            imgView.fitHeight *= (1.1 / aspect)
                        }
                        if (it.deltaY < 0.0) {
                            imgView.fitWidth /= 1.1
                            imgView.fitHeight /= (1.1 / aspect)
                        }

                        log.info("fit: ${imgView.fitWidth}|${imgView.fitHeight} ")
                    })
                    content = imgView
                }

                this += canvas {

                }
            }
        }

    }
}
