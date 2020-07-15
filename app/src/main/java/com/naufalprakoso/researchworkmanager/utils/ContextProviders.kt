package com.naufalprakoso.researchworkmanager.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ContextProviders {
    val main: CoroutineContext = Dispatchers.Main
    val io: CoroutineContext = Dispatchers.IO
}