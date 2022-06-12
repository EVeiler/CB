package and5.kamil.challenge_ch5.home

import and5.kamil.challenge_ch5.R
import and5.kamil.challenge_ch5.api.ApiClient
import and5.kamil.challenge_ch5.login.LoginActvty
import and5.kamil.challenge_ch5.model.Detailuser
import and5.kamil.challenge_ch5.model.GetUserItem
import and5.kamil.challenge_ch5.model.PostUserRespond
import and5.kamil.challenge_ch5.viewmodel.ViewModelProfile
import and5.kamil.challenge_ch5.viewmodel.ViewModelUser
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_profile_actvty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActvty : AppCompatActivity() {

    lateinit var listuser : List<Detailuser>
    private lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_actvty)
        sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        getDataProfile()
        btnUpdate.setOnClickListener{
            updateDataProfile()
            finish()
        }
        btnLogout.setOnClickListener {
            logout()
        }

    }


    fun getDataProfile(){
        val id = sharedPreference.getString("id","")
        val viewModel = ViewModelProvider(this).get(ViewModelProfile::class.java)
        viewModel.DetailUserAPI(id!!.toInt())
        viewModel.getLiveProfile().observe(this, Observer {
            if (it != null){
                listuser = it
                initData(listuser)
                Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "failed" ,Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initData(userdatalist : List<Detailuser>){

        for (i in userdatalist.indices){
            upUser.setText(userdatalist[i].username)
            upNama.setText(userdatalist[i].completeName)
            upAdress.setText(userdatalist[i].address)
            upLahir.setText(userdatalist[i].dateofbirth)
        }
    }

    fun  updateDataProfile(){
        val id = sharedPreference.getString("id","")

        val cname = upNama.text.toString()

        val username = upUser.text.toString()
        val address = upAdress.text.toString()
        val dob = upLahir.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUpdate().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Gagal update user", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Berhasil Update User", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActvty::class.java))
            }
        })

        viewModel.updateUserAPI(id!!.toInt(),cname, username, address, dob)
    }

    fun logout(){
        AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Yakin keluar dari aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                val SF = sharedPreference.edit()
                SF.clear()
                SF.apply()
                startActivity(Intent(this, LoginActvty::class.java))
                finish()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }
}