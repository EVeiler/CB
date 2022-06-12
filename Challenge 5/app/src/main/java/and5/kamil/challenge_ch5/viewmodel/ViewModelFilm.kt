package and5.kamil.challenge_ch5.viewmodel

import and5.kamil.challenge_ch5.api.ApiClient
import and5.kamil.challenge_ch5.model.GetAllFilmItem
import and5.kamil.challenge_ch5.model.ResponseFilmItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm:ViewModel() {
    lateinit var liveDataFilm : MutableLiveData<List<ResponseFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun getLivedatFilm (): MutableLiveData<List<ResponseFilmItem>>{
        return  liveDataFilm
    }

    fun getApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseFilmItem>>,
                    response: Response<List<ResponseFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }
                override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }
}