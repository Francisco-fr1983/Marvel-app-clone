package com.example.marvel_app_clone.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.marvel_app_clone.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.example.marvel_app_clone.ui.state.ResourceState
import com.example.marvel_app_clone.data.model.character.CharacterModelResponse
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

@HiltViewModel
class ListCharacterViewModel @Inject constructor(
        private val repository: MarvelRepository
): ViewModel() {

    private val _list = MutableStateFlow<ResourceState<CharacterModelResponse>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<CharacterModelResponse>> = _list

    //_list propriedade privada, já o list é uma propriedade publica.

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        try {
            val response = repository.list()
            _list.value = handleResponse(response)
        }catch (t: Throwable) {
            when(t) {
                is IOException -> _list.value = ResourceState.Error("Erro de conexão com a internet")
                else -> _list.value = ResourceState.Error("Falha na conexão de dados")
            }
        }
    }

    private fun handleResponse(response: Response<CharacterModelResponse>): ResourceState<CharacterModelResponse> {
            if(response.isSuccessful) {
                response.body()?.let { values ->
                    return ResourceState.Sucess(values)

                }
            }
            return ResourceState.Error(response.message())
    }
}
