package com.example.hilt.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.model.ActivityResponse
import com.example.hilt.network.Repository
import com.example.hilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val resultUser = MutableLiveData<Resource<ActivityResponse>>()


    fun getRepo() {
        resultUser.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRepo()
                withContext(Dispatchers.Main) {
                    if (response.code() == 200) {
                        resultUser.value =
                            Resource.success(response.body(), response.message().toString())
                    } else {
                        resultUser.value = Resource.error(null, response.message().toString())
                    }
                }
            } catch (t: Exception) {
                withContext(Dispatchers.Main) {
                    resultUser.value = Resource.error(null, t.message.toString())
                }
            }

        }
    }
}
