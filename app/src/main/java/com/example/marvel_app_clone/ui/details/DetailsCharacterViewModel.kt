package com.example.marvel_app_clone.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app_clone.data.model.character.CharacterModel
import com.example.marvel_app_clone.data.model.comic.ComicModelResponse
import com.example.marvel_app_clone.repository.MarvelRepository
import com.example.marvel_app_clone.ui.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Injectl

@HiltViewModel
class DetailsCharacterViewModel @Inject constructor(
    private val repository: MarvelRepository
): ViewModel() {
    private val _details =
        MutableStateFlow<ResourceState<ComicModelResponse>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<ComicModelResponse>> = _details

    fun fetch(characterId: Int) = viewModelScope.launch {
        safeFetch(characterId)
    }

    private suspend fun safeFetch(characterId: Int) {
        _details.value = ResourceState.Loading()
        try {
            val response = repository.getComics(characterId)
            _details.value = handleResponse(response)
        }catch (t: Throwable) {
            when(t) {
                is IOException -> _details.value = ResourceState.Error("Erro de rede ou conexão com a internet")
                else -> _details.value = ResourceState.Error("Erro na conversão")
            }
        }
    }

    private fun handleResponse(response: Response<ComicModelResponse>): ResourceState<ComicModelResponse> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Sucess(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    fun insert(characterModel: CharacterModel) = viewModelScope.launch {
        repository.insert(characterModel)
    }


}
