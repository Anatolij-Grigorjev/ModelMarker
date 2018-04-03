package com.tiem625.modemarker.view

import com.tiem625.modemarker.controller.EditSpriteViewController
import tornadofx.*

class EditSpriteView : View("Edit Sprite") {

    val editSpriteViewController: EditSpriteViewController by inject()

    override val root = borderpane {

        //sprite label and ROW/COL
        top {

        }

        //left is meta info about main rects + SAVE
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
