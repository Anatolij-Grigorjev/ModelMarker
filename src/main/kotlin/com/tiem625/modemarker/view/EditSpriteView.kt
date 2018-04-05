package com.tiem625.modemarker.view

import com.tiem625.modemarker.controller.EditSpriteViewController
import tornadofx.*

class EditSpriteView : View("Edit Sprite") {

    val editSpriteViewController: EditSpriteViewController by inject()

    override val root = borderpane {

        //sprite label and ROW/COL
        top {

            hbox {
                vbox {
                    label("Sprite Name")
                    textfield {
                        bind(editSpriteViewController.spriteModel.spriteNameProperty())
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

        }

        //accordion with props of drawn rects
        right {

        }

        //status bar at bottom
        bottom {

        }

        //image with a certain viewport on top of which to daw rectangles
        center {


        }

    }
}