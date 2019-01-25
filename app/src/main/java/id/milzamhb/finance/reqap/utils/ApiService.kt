package id.milzamhb.finance.reqap.utils

import id.milzamhb.finance.reqap.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("newmember")
    @FormUrlEncoded
    fun register(@Field("name") name : String,
                 @Field("email") email : String,
                 @Field("phone_number") phone_number : String,
                 @Field("password") password : String) : Call<List<User>>}

    object datarepo{

        fun create() : ApiService{
            val retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.100:8000/api/")
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }