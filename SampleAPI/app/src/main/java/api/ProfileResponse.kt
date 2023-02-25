package api

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    var id: Long = 0,
    var name: String = "",
    var gpa: Double = 0.0,
    var credit: Int = 0,
//    @SerializedName("faculty") var facultyName: String = ""
    @SerializedName("faculty") var facultyResponse: FacultyResponse
)

data class FacultyResponse(
    var name: String = "",
    var building: String = ""
)

/*

{
	"id": 6412021,
	"name" : "John",
	"gpa" : 3.61,
	"credit": 142,
	"faculty": {
	    "name" : "Vincent Mary School of Science and Technology",
	    "building" : "VMS"
	}
}

 */