package com.kosmasfn.utils

class NativeLib {

    /**
     * A native method that is implemented by the 'utils' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'utils' library on application startup.
        init {
            System.loadLibrary("utils")
        }
    }
}