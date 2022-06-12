package and5.kamil.challenge_ch5.home

import and5.kamil.challenge_ch5.R
import and5.kamil.challenge_ch5.adapter.AdapterFilm
import and5.kamil.challenge_ch5.viewmodel.ViewModelFilm
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home_actvty.*

class HomeActvty : AppCompatActivity() {
    lateinit var adapterfilm : AdapterFilm
    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_actvty)

        prefs = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        welcome.text = "Hello, " + prefs.getString("email","")

        rvFilm.layoutManager = LinearLayoutManager(this)
        adapterfilm = AdapterFilm (){
            val pindahdata = Intent(applicationContext, DetailActvty::class.java)
            pindahdata.putExtra("detailfilm", it)
            startActivity(pindahdata)
        }
        rvFilm.adapter = adapterfilm
        getDataFilm()
        avatar.setOnClickListener {
            startActivity(Intent(this, ProfileActvty::class.java))
        }

    }
    fun getDataFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getLivedatFilm().observe(this, Observer {
            if (it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModel.getApiFilm()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}

