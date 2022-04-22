package waslim.binar.andlima.challengechapter5v13.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_register.*
import waslim.binar.andlima.challengechapter5v13.R
import waslim.binar.andlima.challengechapter5v13.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()

    }



    private fun register(){
        btn_regist.setOnClickListener {
            proses()
        }
    }


    private fun proses(){
        val username = masukanUsername_regist.text.toString()
        val email = masukanEmail_regist.text.toString()
        val konfPass = masukanKonfPassword_regist.text.toString()
        val password = masukanPassword_regist.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        viewModel.getLiveRegister().observe(this, Observer {
            when {
                username.isEmpty() -> {
                    Toast.makeText(this, "Username Harus Di Isi", Toast.LENGTH_LONG).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(this, "Email Harus Di Isi", Toast.LENGTH_LONG).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Password Harus Di Isi", Toast.LENGTH_LONG).show()
                }
                konfPass.isEmpty() -> {
                    Toast.makeText(this, "Konfirmasi Password Harus Di Isi", Toast.LENGTH_LONG).show()
                }
                konfPass != password -> {
                    Toast.makeText(this, "Password & Konfirmasi Password Berbeda", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        })

        viewModel.makeApiUserRegister(email, password, username)
    }



}