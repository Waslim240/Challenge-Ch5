package waslim.binar.andlima.challengechapter5v13.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengechapter5v13.model.datafilm.DataFilmResponseItem
import waslim.binar.andlima.challengechapter5v13.network.ApiClient

class ViewModelFilm : ViewModel() {

// ============================== PUBLIC VARIABEL ========================================//
    var liveDataFilm : MutableLiveData<List<DataFilmResponseItem>?> = MutableLiveData()


// ============================== LIVE DATA USER ========================================//
    fun getLiveFilmObserver() : MutableLiveData<List<DataFilmResponseItem>?>{
        return liveDataFilm
    }


// ============================== API CLIENT FILM ========================================//
    fun makeApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<DataFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<DataFilmResponseItem>>,
                    response: Response<List<DataFilmResponseItem>>
                ) {
                    when {
                        response.isSuccessful -> {
                            liveDataFilm.postValue(response.body())
                        }
                        else -> {
                            liveDataFilm.postValue(null)
                        }
                    }
                }

                override fun onFailure(call: Call<List<DataFilmResponseItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }
            })
    }
}