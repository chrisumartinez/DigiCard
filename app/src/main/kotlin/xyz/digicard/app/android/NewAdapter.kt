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

        private val fullName: TextView
        private val company: TextView
        private val imgView: ImageView
        private lateinit var user: User

        init {
            v.setOnClickListener { _ ->
                ConnectedUserActivity.startActivity(v.context, user.id)
            }

            fullName = v.findViewById(R.id.full_name)
            company = v.findViewById(R.id.company)
            imgView = v.findViewById<View>(R.id.profile_image) as ImageView
        }

        fun bind(u: User) {
            user = u
            fullName.text = u.fullName
            company.text = u.company

            imgView.setImageDrawable(ResourcesCompat.getDrawable(
                    imgView.context.resources,
                    user.findProfilePicDrawableResId(),
                    imgView.context.theme
            ))
        }
    }
}


