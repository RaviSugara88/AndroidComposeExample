package com.sdd.saniproadvance.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sdd.saniproadvance.repo.MainRepository
import com.sdd.saniproadvance.repo.UserRepository
import com.sdd.saniproadvance.retrofit.util.ApiState
import com.sdd.saniproadvance.retrofit.util.UserState
import com.sdd.saniproadvance.room_db.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel
    @Inject constructor( val userRepository: UserRepository, mainRepository: MainRepository)
    :MainViewModel(mainRepository) {

     val userLoginRes = MutableLiveData<UserData?>()
     val userDetailRes = MutableLiveData<UserData?>()

    // Game UI state
//    private val _uiState = MutableStateFlow()
  //  val uiState: StateFlow<UserData> = StateFlow()

    //  private val _uiState = MutableStateFlow(UserData())

    // var userLoginRes: MutableState<UserState> = mutableStateOf(UserState.Empty)

    fun addUser(userData: UserData){
            viewModelScope.launch {
                userRepository.insertUser(userData)
            }
        }

    fun userLogin(email:String){
        viewModelScope.launch {
           // userRepository.loginUser(email)?.let { userLoginRes.value = UserState.Success(it) }
            userLoginRes.postValue(userRepository.loginUser(email))

        }
    }

    fun userDetail(id:Int){
        viewModelScope.launch {
            userDetailRes.postValue(userRepository.getUserDetail(id))
        }
    }




}