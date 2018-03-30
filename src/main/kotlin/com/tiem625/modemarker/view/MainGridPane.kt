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

    override val root = gridpane {

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

        val imgWidthBinding = Bindings.createObjectBinding(Callable<Double> {
            mainViewController.loadedImage?.width ?: 0.0
        }, mainViewController.loadedImageProperty()
        )
        val imgHeightBinding = Bindings.createObjectBinding(Callable<Double> {
            mainViewController.loadedImage?.height ?: 0.0
        }, mainViewController.loadedImageProperty())

        minWidthProperty().bind(imgWidthBinding)
        maxWidthProperty().bind(imgWidthBinding)
        minHeightProperty().bind(imgHeightBinding)
        minHeightProperty().bind(imgHeightBinding)

        mainViewController.loadedSheetInfo.sheetRowsProperty().addListener { observable, oldValue, newValue ->

            refreshConstraints(newValue, rowConstraints, { newN ->
                RowConstraints().apply {
                    percentHeight = 100.0 / newN
                }
            })
            refillWithChildren(this)
        }

        mainViewController.loadedSheetInfo.sheetColsProperty().addListener({ observable, oldValue, newValue ->

            refreshConstraints(newValue, columnConstraints, { newN ->
                ColumnConstraints().apply {
                    percentWidth = 100.0 / newN
                }
            })
            refillWithChildren(this)
        })


        hgapProperty().bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingXProperty())
        vgapProperty().bind(mainViewController.loadedSheetInfo.sheetSpriteSpacingYProperty())
    }


    private fun refillWithChildren(gridPane: GridPane) {

        //clear current children
        gridPane.children.clear()

        log.info("Creating ${gridPane.rowConstraints.size}x${gridPane.columnConstraints.size} buttons!")

        //fill em up for rows and columns
        for (rIdx in 0 until gridPane.rowConstraints.size) {
            for (cIdx in 0 until gridPane.columnConstraints.size) {

                gridPane.add(Button().apply {
                    addClass(Styles.gridCellButton)
                }, cIdx, rIdx)
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
