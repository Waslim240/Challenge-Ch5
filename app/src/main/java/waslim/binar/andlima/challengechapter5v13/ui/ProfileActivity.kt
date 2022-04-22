package waslim.binar.andlima.challengechapter5v13.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_profile.*
import waslim.binar.andlima.challengechapter5v13.R
import waslim.binar.andlima.challengechapter5v13.viewmodel.ViewModelUser

class ProfileActivity : AppCompatActivity() {
    lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        logout()
        getDataShared()
        updateData()
    }


    private fun getDataShared(){
        sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        val getUsername = sharedPreference.getString("USERNAME", "")
        val getNamaLengkap = sharedPreference.getString("COMPLETENAME", "")
        val getTglLahir = sharedPreference.getString("DOB", "")
        val getAlamat = sharedPreference.getString("ADDRESS", "")

        username_profil.setText(getUsername)
        namaLengkap_profil.setText(getNamaLengkap)
        tanggalLahir_profil.setText(getTglLahir)
        alamat_profil.setText(getAlamat)

    }

    private fun updateData(){
        btn_update.setOnClickListener {
            sharedPreference = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

            val id = sharedPreference.getString("ID", "")
            val username = username_profil.text.toString()
            val namaLengkap = namaLengkap_profil.text.toString()
            val tglLahir = tanggalLahir_profil.text.toString()
            val alamat = alamat_profil.text.toString()

            val sharedPref = sharedPreference.edit()
            sharedPref.putString("USERNAME", username)
            sharedPref.putString("COMPLETENAME", namaLengkap)
            sharedPref.putString("DOB", tglLahir)
            sharedPref.putString("ADDRESS", alamat)
            sharedPref.apply()

            if ( tanggalLahir_profil.text.toString().isEmpty() || alamat_profil.text.toString().isEmpty()){
                Toast.makeText(this, "Lengkapi Data profile", Toast.LENGTH_LONG).show()
            } else {
                proses(id!!, username, namaLengkap,tglLahir,alamat)
            }
        }
    }

    private fun proses(id : String, username: String, complete_name : String, dateofbirth : String, address : String){
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        viewModel.getLiveDetail().observe(this, Observer {
            if (username_profil.text.toString().isEmpty() || namaLengkap_profil.text.toString().isEmpty()){
                Toast.makeText(this, "Data Tidak Boleh kosong", Toast.LENGTH_LONG).show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("KONFIRMASI UPDATE")
                    .setMessage("Anda yakin Ingin Update Profile ?")

                    .setPositiveButton("YA"){ dialogInterface: DialogInterface, i: Int ->
                        Toast.makeText(this, "Berhasil Update", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                    .setNegativeButton("TIDAK"){ dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    .show()
            }
        })

        viewModel.makeApiUserProfil(id, username, complete_name, dateofbirth, address)
    }




    private fun logout(){
        btn_logout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("KONFIRMASI LOGOUT")
                .setMessage("Anda Yakin Ingin Logout ?")

                .setPositiveButton("YA"){ dialogInterface: DialogInterface, i: Int ->
                    val sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                    val sharedPrefs = sharedPreferences.edit()
                    sharedPrefs.clear()
                    sharedPrefs.apply()

                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .setNegativeButton("TIDAK"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }

    }



}