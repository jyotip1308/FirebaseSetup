package com.android.firebasesetup.firestoredb.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.firebasesetup.firestoredb.FireStoreModelResponse
import com.android.firebasesetup.firestoredb.repository.FireStoreRepository
import com.android.firebasesetup.utility.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireStoreViewModel @Inject constructor(
    private val repo:FireStoreRepository
) : ViewModel(){

    private val _res:MutableState<FireStoreState> = mutableStateOf(FireStoreState())
    val res:State<FireStoreState> = _res

    fun insert(item: FireStoreModelResponse.FireStoreItem) = repo.insert(item)

    private val _updateData:MutableState<FireStoreModelResponse> = mutableStateOf(
        FireStoreModelResponse(
        item = FireStoreModelResponse.FireStoreItem()
    )
    )

    val updateData:State<FireStoreModelResponse> = _updateData

    fun setData(data:FireStoreModelResponse){
        _updateData.value = data
    }

    init {
        getItems()
    }

    fun getItems() = viewModelScope.launch {
        repo.getItems().collect{
            when(it){
                is ResultState.Success ->{
                    _res.value = FireStoreState(
                        data = it.data
                    )
                }

                is ResultState.Failure ->{
                    _res.value = FireStoreState(
                        error = it.msg.toString()
                    )
                }
                is ResultState.Loading ->{
                    _res.value = FireStoreState(
                        isLoading = true
                    )
                }
            }
        }
    }

    fun delete(key:String) = repo.delete(key)
    fun update(item:FireStoreModelResponse) = repo.update(item)
}

data class FireStoreState(
    val data:List<FireStoreModelResponse> = emptyList(),
    val error:String = "",
    val isLoading:Boolean = false
)