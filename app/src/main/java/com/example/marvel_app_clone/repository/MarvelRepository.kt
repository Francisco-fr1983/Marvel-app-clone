package com.example.marvel_app_clone.repository

import com.example.marvel_app_clone.data.remote.ServiceApi
import javax.inject.Inject


class MarvelRepository @Inject constructor(
        private val api: ServiceApi
) {
    suspend fun list(nameStartsWith: String? = null) = api.list(nameStartsWith)
    suspend fun getComics(CharacterId: Int) = api.getComics(CharacterId)
}