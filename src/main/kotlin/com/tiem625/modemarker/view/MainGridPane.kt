package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.controller.MainViewController
import javafx.beans.binding.Bindings
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import tornadofx.*
import java.util.concurrent.Callable

class MainGridPane : View("Sprites Grid") {

    val mainViewController: MainViewController by inject()

    val gridButtons: MutableMap<Pair<Int, Int>, Button> = mutableMapOf()

    override val root = gridpane {

        addClass(Styles.imgPane)

        mainViewController.loadedSheetInfo.sheetRowsProperty().addListener { observable, oldValue, newValue ->
            newValue?.let {
                refreshConstraints(newValue, rowConstraints, { newN ->
                    RowConstraints().apply {
                        percentHeight = 100.0 / newN
                    }
                })
                refillWithChildren(this)
            }
        }

        mainViewController.loadedSheetInfo.sheetColsProperty().addListener({ observable, oldValue, newValue ->
            newValue?.let {
                refreshConstraints(newValue, columnConstraints, { newN ->
                    ColumnConstraints().apply {
                        percentWidth = 100.0 / newN
                    }
                })
                refillWithChildren(this)
            }
        })


        hgapProperty().bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingXProperty())
        vgapProperty().bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingYProperty())
    }


    private fun refillWithChildren(gridPane: GridPane) {

        //clear current children
        gridPane.children.clear()
        gridButtons.clear()

        log.info("Creating ${gridPane.rowConstraints.size}x${gridPane.columnConstraints.size} buttons!")

        //fill em up for rows and columns
        for (rIdx in 0 until gridPane.rowConstraints.size) {
            for (cIdx in 0 until gridPane.columnConstraints.size) {

                val button = Button().apply {
                    addClass(Styles.gridCellButton)

                    setOnAction {
                        log.info("Clicked button ($rIdx; $cIdx)")

                        //clear style from prev selected button
                        mainViewController.selectedCell?.let { selection ->

                            val node = gridButtons[selection]
                            node?.removeClass(Styles.selectedGridCellButton)
                        }

                        //deselect button
                        if (mainViewController.selectedCell == Pair(rIdx, cIdx)) {

                            mainViewController.selectedCell = null
                        } else {

                            //select button
                            addClass(Styles.selectedGridCellButton)
                            mainViewController.selectedCell = Pair(rIdx, cIdx)
                        }

                    }
                }
                gridPane.add(button, cIdx, rIdx)
                gridButtons[Pair(rIdx, cIdx)] = button
            }
        }
    }


    private fun <T : ConstraintsBase> refreshConstraints(newN: Int, constraintsList: ObservableList<T>, creator: (Int) -> T) {

        if (gridChangeOk(newN)) {

            constraintsList.clear()

            (0 until newN).forEach {

                constraintsList.add(creator(newN))
            }
        }
    }

    private fun gridChangeOk(newValue: Int) =
            newValue >= 1 && mainViewController.loadedImage != null
}
