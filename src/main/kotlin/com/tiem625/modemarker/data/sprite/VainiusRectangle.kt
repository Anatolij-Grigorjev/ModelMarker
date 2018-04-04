package com.tiem625.modemarker.data.sprite

import com.tiem625.modemarker.data.WithPropsTable
import tornadofx.*

class VainiusRectangle: WithPropsTable<String, String>() {

    var width: Int by property(0)
    fun widthProeprty() = getProperty(VainiusRectangle::width)

    var height: Int by property(0)
    fun heightProperty() = getProperty(VainiusRectangle::height)

    var startX: Int by property(0)
    fun startXProperty() = getProperty(VainiusRectangle::startX)

    var startY: Int by property(0)
    fun startYProperty() = getProperty(VainiusRectangle::startY)

}