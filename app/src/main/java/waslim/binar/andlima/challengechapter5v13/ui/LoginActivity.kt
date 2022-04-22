package waslim.binar.andlima.challengechapter5v13.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengechapter5v13.R
import waslim.binar.andlima.challengechapter5v13.model.user.UserResponse
import waslim.binar.andlima.challengechapter5v13.network.ApiClient
import waslim.binar.andlima.challengechapter5v13.viewmodel.ViewModelUser


class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (getSharedPreferences("LOGIN", Context.MODE_PRIVATE).contains("EMAIL") &&
            getSharedPreferences("LOGIN", Context.MODE_PRIVATE).contains("PASSWORD")) {
            startActivity(Intent(this, HomeActivity::class.java))
        }


        daftar()
        login()

    }



    private fun login(){
        btn_login.setOnClickListener {
            when {
                masukanEmail_login.text.toString().isEmpty() -> {
                    Toast.makeText(this, "Masukan Data Email", Toast.LENGTH_LONG).show()
                }
                masukanPassword_login.text.toString().isEmpty() -> {
                    Toast.makeText(this, "Masukan Data Password", Toast.LENGTH_LONG).show()
                }
                else -> {
                    sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                    makeApiUserLogin()

                }
            }
        }
    }


//    private fun proses(){
//        val email = masukanEmail_login.text.toString()
//        val password = masukanPassword_login.text.toString()
//
//        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
//
//        viewModel.getLiveLogin().observe(this, Observer {
//            if (it != null) {
//                sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//
//                val sharedPref = sharedPreference.edit()
//                sharedPref.putString("ID", it.responseuser.id)
//                sharedPref.putString("EMAIL", it.responseuser.email)
//                sharedPref.putString("PASSWORD", it.responseuser.password)
//                sharedPref.putString("USERNAME", it.responseuser.username)
//                sharedPref.putString("COMPLETENAME", it.responseuser.completeName)
//                sharedPref.putString("DOB", it.responseuser.dateofbirth)
//                sharedPref.putString("ADDRESS", it.responseuser.address)
//                sharedPref.apply()
//
//                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this, HomeActivity::class.java))
//
//            }
//            else {
//                Toast.makeText(this, "Gagal Login", Toast.LENGTH_LONG).show()
//            }
//        })
//
//        viewModel.makeApiUserLogin(email, password)
//    }


    private fun makeApiUserLogin(){
        val email = masukanEmail_login.text.toString()
        val password = masukanPassword_login.text.toString()

        ApiClient.instance.postLogin(email, password)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

                        val sharedPref = sharedPreference.edit()
                        sharedPref.putString("ID", response.body()?.responseuser?.id)
                        sharedPref.putString("EMAIL", email)
                        sharedPref.putString("PASSWORD", password)
                        sharedPref.putString("USERNAME", response.body()?.responseuser?.username)
                        sharedPref.putString("COMPLETENAME", response.body()?.responseuser?.completeName)
                        sharedPref.putString("DOB", response.body()?.responseuser?.dateofbirth)
                        sharedPref.putString("ADDRESS", response.body()?.responseuser?.address)
                        sharedPref.apply()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))

                        Toast.makeText(this@LoginActivity, "Berhasil Login", Toast.LENGTH_LONG).show()

                    } else {

                        Toast.makeText(this@LoginActivity, "Gagal Login", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Kesalahan Jaringan", Toast.LENGTH_LONG).show()
                }

            })
    }


        private fun daftar(){
        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


}