package com.carbit3333333.giphyapp.repository

import com.carbit3333333.giphyapp.repository.api.ServerCommunicator
import com.carbit3333333.giphyapp.repository.database.AppDatabase

class AppRepository(private val serverCommunicator: ServerCommunicator,
                    private val mainDatabase: AppDatabase) {

}