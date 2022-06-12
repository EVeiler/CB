package and5.kamil.challenge_ch5.viewmodel

import and5.kamil.challenge_ch5.api.ApiClient
import and5.kamil.challenge_ch5.model.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {
    lateinit var liveDataRegister : MutableLiveData<ResponseRegister>
    lateinit var liveDataLogin : MutableLiveData<List<Responseuser>>
    lateinit var liveDataUpdate : MutableLiveData<Data>

    init {
        liveDataRegister = MutableLiveData()
        liveDataLogin = MutableLiveData()
        liveDataUpdate = MutableLiveData()

    }

    fun getLiveRegister(): MutableLiveData<ResponseRegister>{
        return liveDataRegister
    }
    fun getLiveLogin(): MutableLiveData<List<Responseuser>>{
        return liveDataLogin
    }
    fun getLiveUpdate() : MutableLiveData<Data>{
        return liveDataUpdate
    }



    fun registerUserAPI(email : String, pass : String, username : String){
        ApiClient.instance.registerUser(email,pass,username)
            .enqueue(object : Callback<ResponseRegister>{
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response.isSuccessful){
                        liveDataRegister.postValue(response.body())
                    }else{
                        liveDataRegister.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    liveDataRegister.postValue(null)
                }

            })
    }

    fun loginUserAPI(){
        ApiClient.instance.allUser()
            .enqueue(object : Callback<List<Responseuser>>{
                override fun onResponse(call: Call<List<Responseuser>>, response: Response<List<Responseuser>>) {
                    if (response.isSuccessful){
                        liveDataLogin.postValue(response.body())
                    }else{
                        liveDataLogin.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Responseuser>>, t: Throwable) {
                    liveDataLogin.postValue(null)
                }

            })
    }

    fun updateUserAPI(id :Int, cname : String, username: String, address: String, dob : String){
        ApiClient.instance.updateUser(id.toString(), cname, username, address, dob)
            .enqueue(object : Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful){
                        liveDataUpdate.postValue(response.body())
                    }else{
                        liveDataUpdate.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    liveDataUpdate.postValue(null)
                }

            })
    }
}