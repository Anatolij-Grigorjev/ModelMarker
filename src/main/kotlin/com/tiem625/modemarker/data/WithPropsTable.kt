package com.tiem625.modemarker.data

import tornadofx.*

abstract class WithPropsTable<K, V> {

    var propsTable: MutableMap<K, V> by property(mutableMapOf())
    fun propsTableProperty() = getProperty(WithPropsTable<K, V>::propsTable)
}