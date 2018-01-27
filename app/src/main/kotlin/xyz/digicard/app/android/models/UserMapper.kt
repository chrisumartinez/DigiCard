package xyz.digicard.app.android.models

import com.squareup.moshi.*
import java.util.*

object UserMapper {

    private val userAdapter = Moshi.Builder()
            .add(UuidAdapter())
            .build()
            .adapter(User::class.java)

    fun toJson(user: User): String = userAdapter.toJson(user)
    fun fromJson(json: String): User = userAdapter.fromJson(json)!!


    private class UuidAdapter : JsonAdapter<UUID>() {

        @FromJson
        override fun fromJson(reader: JsonReader?): UUID? {
            val string = reader?.nextString()
            return when (string) {
                null -> null
                else -> UUID.fromString(string)
            }
        }

        @ToJson
        override fun toJson(writer: JsonWriter?, value: UUID?) {
            writer?.value(value?.toString())
        }
    }
}