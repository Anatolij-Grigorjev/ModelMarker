package com.tiem625.modemarker.app

import com.tiem625.modemarker.view.MainView
import tornadofx.*

class ModelMarkerApp : App(MainView::class) {
     init {
         Styles.styleClasses.forEach { importStylesheet(it) }
         reloadStylesheetsOnFocus()
     }
}