package com.codeblin.archcore.data

import android.content.Context
import com.codeblin.archcore.utils.ConnectivityNetworkManager
import com.codeblin.archcore.utils.NetworkManager

abstract class ArchCoreRepository<T>(context: Context){
    val networkManager: NetworkManager = ConnectivityNetworkManager(context)
    abstract suspend fun getData(): T
}