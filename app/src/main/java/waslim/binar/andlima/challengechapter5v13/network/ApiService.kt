package waslim.binar.andlima.challengechapter5v13.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import waslim.binar.andlima.challengechapter5v13.model.datafilm.DataFilmResponseItem
import waslim.binar.andlima.challengechapter5v13.model.user.UserResponse

interface ApiService {

    // get data film
    @GET("apifilm.php")
    fun getAllFilm() : Call<List<DataFilmResponseItem>>

    // post data register
    @POST("register.php")
    @FormUrlEncoded
    fun postRegister(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("username") username : String
    ) : Call<UserResponse>

    // post data login
    @POST("login.php")
    @FormUrlEncoded
    fun postLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<UserResponse>

    //update data user
    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id : String,
        @Field("username") username : String,
        @Field("complete_name") complete_name : String,
        @Field("dateofbirth") dateofbirth : String,
        @Field("address") address : String
    ) : Call<UserResponse>

}