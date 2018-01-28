package xyz.digicard.app.android

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import xyz.digicard.app.android.models.User

class NewAdapter(private val data: List<User>) : RecyclerView.Adapter<NewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.user_profile_contact_container, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val firstName: TextView
        private val lastName: TextView
        private val email: TextView
        private val imgView: ImageView
        private lateinit var user: User

        init {
            v.setOnClickListener { _ ->
                ConnectedUserActivity.startActivity(v.context, user.id)
            }

            this.firstName = v.findViewById<View>(R.id.contact_first_name) as TextView
            this.lastName = v.findViewById<View>(R.id.contact_last_name) as TextView
            this.imgView = v.findViewById<View>(R.id.profile_image) as ImageView
            this.email = v.findViewById<View>(R.id.email) as TextView
        }

        fun bind(u: User) {
            user = u
            firstName.text = u.firstName
            lastName.text = u.lastName
            email.text = u.email

            imgView.setImageDrawable(ResourcesCompat.getDrawable(
                    imgView.context.resources,
                    findDrawableResId(),
                    imgView.context.theme
            ))
        }

        private fun findDrawableResId(): Int {
            val codeInt = user.firstName.first().toInt().rem(9) + 1
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
}


