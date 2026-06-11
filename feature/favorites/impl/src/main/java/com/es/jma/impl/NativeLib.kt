package com.es.jma.impl

class NativeLib {

    /**
     * A native method that is implemented by the 'impl' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'impl' library on application startup.
        init {
            System.loadLibrary("impl")
        }
    }
}