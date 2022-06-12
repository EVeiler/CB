package and5.kamil.challenge_ch5.login

import and5.kamil.challenge_ch5.R

import and5.kamil.challenge_ch5.home.HomeActvty
import and5.kamil.challenge_ch5.model.Responseuser
import and5.kamil.challenge_ch5.register.RegisterActvty
import and5.kamil.challenge_ch5.viewmodel.ViewModelUser
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login_actvty.*

class LoginActvty : AppCompatActivity() {
    lateinit var sf : SharedPreferences
    lateinit var listuserlogin : List<Responseuser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvty)

        val dataUser = getSharedPreferences("datauser" , Context.MODE_PRIVATE)

        if (dataUser.contains("email")){
            startActivity(Intent(this, HomeActvty::class.java))
        }

        sf = this.getSharedPreferences("datauser",Context.MODE_PRIVATE)
        btnLogin.setOnClickListener{
            if (login_email.text.toString().isEmpty()){
                login_email.setError("Isi email")
            }else if(login_password.text.toString().isEmpty()){
                login_password.setError("Isi Password")
            }else{
                Login()
            }
        }
        register.setOnClickListener {
            startActivity(Intent(this, RegisterActvty::class.java))
        }
    }

    fun Login(){
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.loginUserAPI()
        viewModel.getLiveLogin().observe(this, Observer {
            if (it != null){
                listuserlogin = it
                loginAuth(listuserlogin)
                startActivity(Intent(this, HomeActvty::class.java))
            }else{
                Toast.makeText(this, "gagal Login", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loginAuth(listlogin : List<Responseuser>){
        val email = login_email.text.toString()
        val  password = login_password.text.toString()
        for(i in listlogin.indices){
            if (email == listlogin[i].email && password == listlogin[i].password) {
                sf = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                val sfe = sf.edit()
                sfe.putString("email", listlogin[i].email).apply()
                sfe.putString("id", listlogin[i].id).apply()
                Toast.makeText(this, "Selamat Datang Di indomaret", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}