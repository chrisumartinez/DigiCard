package xyz.digicard.app.android

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val firstNameGroup = view.findViewById<View>(R.id.first_name)
        firstNameGroup.assignInfoName(R.string.firstName)
        firstNameGroup.assignInfoValue(user.firstName)

        val lastNameGroup = view.findViewById<View>(R.id.last_name)
        lastNameGroup.assignInfoName(R.string.lastName)
        lastNameGroup.assignInfoValue(user.lastName)

        val emailGroup = view.findViewById<View>(R.id.email)
        emailGroup.assignInfoName(R.string.email)
        emailGroup.assignInfoValue(user.email)

        val phoneGroup = view.findViewById<View>(R.id.phone_number)
        phoneGroup.assignInfoName(R.string.phone_number)
        phoneGroup.assignInfoValue(user.phone)
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

        private fun View.assignInfoName(@StringRes resId: Int) {
            val infoName = findViewById<TextView>(R.id.info_name)
            infoName.text = context.getString(resId)
        }

        private fun View.assignInfoValue(value: String?) {
            val infoValue = findViewById<TextView>(R.id.info_value)
            infoValue.text = value
        }
    }
}