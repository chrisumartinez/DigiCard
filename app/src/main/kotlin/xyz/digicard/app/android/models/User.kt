package xyz.digicard.app.android.models

import java.util.*

data class User(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phone: String? = null,
        val photoUrl: String? = null,
        val linkedInUrl: String? = null
)