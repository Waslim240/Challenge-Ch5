package waslim.binar.andlima.challengechapter5v13.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_film.*
import waslim.binar.andlima.challengechapter5v13.R
import waslim.binar.andlima.challengechapter5v13.model.datafilm.DataFilmResponseItem

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)


        val detailfilm = intent.getParcelableExtra<DataFilmResponseItem>("detailfilm")

        Glide.with(applicationContext).load(detailfilm?.image).into(imageFilmDetail)
        tvJudulDetail.text = detailfilm?.title
        tvTglRilisDetail.text = detailfilm?.releaseDate
        tvSutradaraDetail.text = detailfilm?.director
        tvSinopsisFilmDetail.text = detailfilm?.synopsis

    }
}