package xyz.digicard.app.android.models

import xyz.digicard.app.android.R
import java.util.*

data class User(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val purpose: String? = null,
        val phone: String? = null,
        val company: String? = null,
        val photoUrl: String? = null,
        val linkedInUrl: String? = null,
        val tags: String? = null) {

    val fullName get() = "$firstName $lastName"

    fun findProfilePicDrawableResId(): Int {
        val codeInt = firstName.first().toInt().rem(9) + 1
        return when (codeInt) {
            1 -> R.drawable.a1
            2 -> R.drawable.a2
            3 -> R.drawable.a3
            4 -> R.drawable.a4
            5 -> R.drawable.a5
            6 -> R.drawable.a6
            7 -> R.drawable.a7
            8 -> R.drawable.a8
            9 -> R.drawable.a9
            else -> R.drawable.user_photo_fallback
        }
    }
}