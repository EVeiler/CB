package and5.kamil.challenge_ch5.viewmodel

import and5.kamil.challenge_ch5.api.ApiClient
import and5.kamil.challenge_ch5.model.Detailuser
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProfile:ViewModel() {
    lateinit var liveDataDetailUser: MutableLiveData<List<Detailuser>>

    init {
        liveDataDetailUser = MutableLiveData()
    }

    fun getLiveProfile(): MutableLiveData<List<Detailuser>> {
        return liveDataDetailUser
    }

    fun DetailUserAPI(id : Int){
        ApiClient.instance.detailUser(id)
            .enqueue(object : Callback<List<Detailuser>> {
                override fun onResponse(call: Call<List<Detailuser>>, response: Response<List<Detailuser>>) {
                    if (response.isSuccessful){
                        liveDataDetailUser.postValue(response.body())
                    }else{
                        liveDataDetailUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Detailuser>>, t: Throwable) {
                    liveDataDetailUser.postValue(null)
                }

            })
    }

}