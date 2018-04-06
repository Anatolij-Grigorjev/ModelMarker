package com.tiem625.modemarker.data

import tornadofx.*

abstract class WithPropsTable<K, V> {

    var propsTable: MutableList<Map.Entry<K, V>> by property(mutableListOf())
    fun propsTableProperty() = getProperty(WithPropsTable<K, V>::propsTable)
}