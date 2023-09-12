package com.example.mvvmexample.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Corountines {
    /*
        Main function is taking work function as a parameter
        and executing the work inside the Coroutine scope
        so that we can execute/call our suspended function inside the coroutine scope.
     */
    fun main(work: suspend (()-> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}