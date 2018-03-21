package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import com.tiem625.modemarker.app.Version
import tornadofx.*

class MainView : View("Model Marker v${Version.versionString} (tm)") {

    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}