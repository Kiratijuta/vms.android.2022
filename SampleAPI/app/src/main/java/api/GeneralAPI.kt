package api

import retrofit2.Call
import retrofit2.http.GET

interface GeneralAPI {

    @GET("/s/h9rbm9i7xl0koxs/profileandfaculty.json")
    fun getProfile(): Call<ProfileResponse>

    @GET("/s/rz6jsqx53jhwbtc/grades.json")
    fun getGrades(): Call<List<GradeReponse>>

}