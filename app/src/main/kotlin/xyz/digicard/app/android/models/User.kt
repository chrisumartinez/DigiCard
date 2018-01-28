package xyz.digicard.app.android.models

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
}