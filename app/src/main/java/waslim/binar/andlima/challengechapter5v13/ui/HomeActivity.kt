package waslim.binar.andlima.challengechapter5v13.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import waslim.binar.andlima.challengechapter5v13.R
import waslim.binar.andlima.challengechapter5v13.adapter.AdapterFilm
import waslim.binar.andlima.challengechapter5v13.model.user.UserResponse
import waslim.binar.andlima.challengechapter5v13.viewmodel.ViewModelFilm

@SuppressLint("SetTextI18n")
class HomeActivity : AppCompatActivity() {
    lateinit var sharedPrefe : SharedPreferences
    lateinit var adapterFilm: AdapterFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPrefe = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        val data = sharedPrefe.getString("USERNAME", "")
        welcomeUsernaem.text = "Welcome, $data !"


        initRecycler()
        getDataFilm()
        goToProfile()
    }



    private fun initRecycler(){
        rvFilm.layoutManager = LinearLayoutManager(this)
        adapterFilm = AdapterFilm() {
            val pdh = Intent(this, DetailFilmActivity::class.java)
            pdh.putExtra("detailfilm", it)
            startActivity(pdh)
        }

        rvFilm.adapter = adapterFilm
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getLiveFilmObserver().observe(this, Observer {
            when {
                it != null -> {
                    adapterFilm.setDataFilm(it)
                    adapterFilm.notifyDataSetChanged()
                }
            }
        })

        viewModel.makeApiFilm()
    }


    private fun goToProfile(){
        imageProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }




}