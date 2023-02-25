package kiratijuta.sampleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import api.GeneralAPI
import api.GradeReponse
import api.ProfileResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // you build the retrofit and GeneralAPI to be used for HTTP request or API request
        // ScalarsConverterFactory is used for webpage response (non json format)
        // GsonConverterFactory is used for JSON response
        val retrofit = Retrofit.Builder().baseUrl("https://dl.dropboxusercontent.com")
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .build()
        val generalAPI = retrofit.create(GeneralAPI::class.java)

        // request generalAPI function for JSON object example
        val getProfileResponse: Call<ProfileResponse> = generalAPI.getProfile()
        getProfileResponse.enqueue(object: Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                var result = response.body()
                if (result != null) {
                    displayId.text = "${result.id}"
                    displayName.text = result.name
                    displayGPA.text = "${result.gpa}"
                    displayCredit.text = "${result.credit}"
                    displayFaculty.text = "${result.facultyResponse.name} (${result.facultyResponse.building})"
                }
            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("GENERAL-API", "Failed to request GoogleResponse ${t.message}")
            }
        })

        // request generalAPI function for JSON list of object example
        val getGradeResponse: Call<List<GradeReponse>> = generalAPI.getGrades()
        getGradeResponse.enqueue(object: Callback<List<GradeReponse>> {
            override fun onResponse(
                call: Call<List<GradeReponse>>,
                response: Response<List<GradeReponse>>
            ) {
                var result = response.body()
                if (result != null) {
                    Log.d("GENERAL-API-GRADES", result[0].name)
                }
            }

            override fun onFailure(call: Call<List<GradeReponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}