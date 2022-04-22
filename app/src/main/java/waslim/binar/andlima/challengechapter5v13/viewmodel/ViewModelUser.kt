package waslim.binar.andlima.challengechapter5v13.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengechapter5v13.model.user.UserResponse
import waslim.binar.andlima.challengechapter5v13.network.ApiClient

class ViewModelUser : ViewModel() {

// ============================== PUBLIC VARIABEL ========================================//
    var liveDataUserLogin : MutableLiveData<UserResponse?> = MutableLiveData()
    var liveDataUserRegister : MutableLiveData<UserResponse?> = MutableLiveData()
    var liveDataUserDetail : MutableLiveData<UserResponse?> = MutableLiveData()


// ============================== LIVE DATA USER ========================================//
    fun getLiveLogin() : MutableLiveData<UserResponse?>{
        return liveDataUserLogin
    }

    fun getLiveRegister() : MutableLiveData<UserResponse?>{
        return liveDataUserRegister
    }

    fun getLiveDetail() : MutableLiveData<UserResponse?>{
        return liveDataUserDetail
    }



// ============================== API CLIENT USER ========================================//
    fun makeApiUserLogin(email : String, password : String){
        ApiClient.instance.postLogin(email, password)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            liveDataUserLogin.postValue(response.body())
                        }
                        else -> {
                            liveDataUserLogin.postValue(null)
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    liveDataUserLogin.postValue(null)
                }

            })
    }


    fun makeApiUserRegister(email : String, password : String, username : String){
        ApiClient.instance.postRegister(email, password, username)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            liveDataUserRegister.postValue(response.body())
                        }
                        else -> {
                            liveDataUserRegister.postValue(null)
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    liveDataUserRegister.postValue(null)
                }

            })
    }


    fun makeApiUserProfil(id : String, username: String, complete_name : String, dateofbirth : String, address : String){
        ApiClient.instance.updateUser(id, username, complete_name, dateofbirth, address)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    when {
                        response.isSuccessful -> {
                            liveDataUserDetail.postValue(response.body())
                        }
                        else -> {
                            liveDataUserDetail.postValue(null)
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    liveDataUserDetail.postValue(null)
                }

            })
    }
}

