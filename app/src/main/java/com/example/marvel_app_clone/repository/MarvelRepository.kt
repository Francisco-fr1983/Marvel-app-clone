package com.example.marvel_app_clone.repository

import com.example.marvel_app_clone.data.local.MarvelDao
import com.example.marvel_app_clone.data.model.character.CharacterModel
import com.example.marvel_app_clone.data.remote.ServiceApi
import javax.inject.Inject


class MarvelRepository @Inject constructor(
        private val api: ServiceApi,
        private val dao: MarvelDao
) {
    suspend fun list(nameStartsWith: String? = null) = api.list(nameStartsWith)
    suspend fun getComics(CharacterId: Int) = api.getComics(CharacterId)

    suspend fun insert(characterModel: CharacterModel) = dao.insert(characterModel)
    fun getAll() = dao.getAll()
    suspend fun delete(characterModel: CharacterModel) = dao.delete(characterModel)
}
