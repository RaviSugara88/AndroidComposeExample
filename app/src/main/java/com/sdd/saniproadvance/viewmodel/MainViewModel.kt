package com.sdd.saniproadvance.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdd.saniproadvance.repo.MainRepository
import com.sdd.saniproadvance.retrofit.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    val response:MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    fun getPost() = viewModelScope.launch {
        mainRepository.getPost()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Failure(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
    }

}