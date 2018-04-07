package com.tiem625.modemarker.data

import com.sun.javafx.collections.ObservableListWrapper
import javafx.collections.ObservableList
import tornadofx.*

abstract class WithPropsTable<K, V> {

    var propsTable: ObservableList<Map.Entry<K, V>> by property(ObservableListWrapper(mutableListOf()))
    fun propsTableProperty() = getProperty(WithPropsTable<K, V>::propsTable)
}