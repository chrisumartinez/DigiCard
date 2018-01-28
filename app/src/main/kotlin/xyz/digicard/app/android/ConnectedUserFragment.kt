package xyz.digicard.app.android

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jayrave.falkon.dao.findById
import xyz.digicard.app.android.models.User
import xyz.digicard.app.android.models.UsersTable
import java.util.*

class ConnectedUserFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_connected_user, container, false)
        populateViews(view, extractUser())
        return view
    }


    private fun extractUser(): User {
        return UsersTable.instance.dao.findById(
                UUID.fromString(arguments.getString(USER_ID_KEY))
        )!!
    }


    private fun populateViews(view: View, user: User) {
        view.setTextOrHide(R.id.full_name, user.fullName)
        view.setTextOrHide(R.id.email, user.email)
        view.setTextOrHide(R.id.company, user.company)
        view.setTextOrHide(R.id.phone_number, user.phone)

        view.findViewById<ImageView>(R.id.profile_image).setImageDrawable(
                ResourcesCompat.getDrawable(
                        context.resources,
                        user.findProfilePicDrawableResId(),
                        context.theme
                )
        )
    }



    companion object {

        private const val USER_ID_KEY = "user_id_key"

        fun newInstance(userId: UUID): Fragment {
            return ConnectedUserFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID_KEY, userId.toString())
                }
            }
        }

        private fun View.setTextOrHide(@IdRes resId: Int, text: String?) {
            val textView = findViewById<TextView>(resId)
            when {
                text.isNullOrEmpty() -> textView.visibility = View.GONE
                else -> {
                    textView.visibility = View.VISIBLE
                    textView.text = text
                }
            }
        }
    }
}