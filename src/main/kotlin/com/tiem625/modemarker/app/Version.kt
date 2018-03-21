package com.tiem625.modemarker.app

import java.util.*

object Version {

    val majorNumber: Int = 0
    val minorNumber: Int = 0

    private val rnd: Random = Random()

    val versionString: String get() = "$majorNumber.$minorNumber.${rnd.nextInt(100)}"

}