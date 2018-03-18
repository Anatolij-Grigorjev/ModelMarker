package com.tiem625.modemarker.view

import com.tiem625.modemarker.app.Styles
import tornadofx.*

class MainView : View("Hello TornadoFX") {

    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}