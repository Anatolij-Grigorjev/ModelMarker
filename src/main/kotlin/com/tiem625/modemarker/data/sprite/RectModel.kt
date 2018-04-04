package com.tiem625.modemarker.data.sprite

import com.tiem625.modemarker.data.WithPropsTable
import tornadofx.*

class RectModel: WithPropsTable<String, String>() {

    var rectKeyName: String by property("body")
    fun rectKeyNameProperty() = getProperty(RectModel::rectKeyName)

    var isPropArray: Boolean by property(false)
    fun isPropArrayProperty() = getProperty(RectModel::isPropArray)



}