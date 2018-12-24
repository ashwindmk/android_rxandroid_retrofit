package samples.android.ashwin.com.rxandroidretrofit

import com.squareup.moshi.Json

/**
 * Created by Ashwin on 12/24/2018.
 */
data class Profile (
    @field:Json(name = "id")
    val id: String?,

    @field:Json(name = "firstname")
    val firstname: String?,

    @field:Json(name = "lastname")
    val lastname: String?,

    @field:Json(name = "age")
    val age: Int,

    @field:Json(name = "phone")
    val phone: String?,

    @field:Json(name = "points")
    val points: Double
)
