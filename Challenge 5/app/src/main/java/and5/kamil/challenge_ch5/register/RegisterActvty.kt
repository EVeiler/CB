package and5.kamil.challenge_ch5.register

import and5.kamil.challenge_ch5.R
import and5.kamil.challenge_ch5.api.ApiClient
import and5.kamil.challenge_ch5.login.LoginActvty
import and5.kamil.challenge_ch5.model.PostUserRespond
import and5.kamil.challenge_ch5.viewmodel.ViewModelUser
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register_actvty.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterActvty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actvty)

        btnRegis.setOnClickListener{
            if (in_pass.text.toString() != in_passwordulang.text.toString()){
                Snackbar.make(it, "Isi Password yang sama", Snackbar.LENGTH_LONG).show()
            }else{
                Register()
            }
        }
    }

    fun Register(){
        val userName = in_user.text.toString()
        val email = in_email.text.toString()
        val  pass = in_pass.text.toString()
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveRegister().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Berhasil membuat akun", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Gagal membuat akun", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.registerUserAPI(email,pass,userName)
    }
    }