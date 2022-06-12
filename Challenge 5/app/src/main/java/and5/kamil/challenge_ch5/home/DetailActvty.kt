package and5.kamil.challenge_ch5.home

import and5.kamil.challenge_ch5.R
import and5.kamil.challenge_ch5.model.GetAllFilmItem
import and5.kamil.challenge_ch5.model.ResponseFilmItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_actvty.*

class DetailActvty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_actvty)
        val detailfilm = intent.getParcelableExtra("detailfilm") as ResponseFilmItem?

        tvJudul.text = detailfilm?.title
        tvsutradara.text = detailfilm?.director
        tvtglFilm.text = detailfilm?.releaseDate
        tvdesc.text = detailfilm?.synopsis

        Glide.with(this).load(detailfilm?.image).into(imgdetail)

    }
    }
