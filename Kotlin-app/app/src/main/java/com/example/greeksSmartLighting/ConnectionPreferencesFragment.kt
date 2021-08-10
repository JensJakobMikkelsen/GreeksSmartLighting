package com.webianks.bluechat

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.blue.ExtendedViewClasses.ConfirmationsPreference
import com.example.blue.R


class ConnectionPreferencesFragment : PreferenceFragmentCompat() {

    var confirmationsPreference: ConfirmationsPreference? = null

    companion object {
        fun newInstance(): ConnectionPreferencesFragment{
            val myFragment = ConnectionPreferencesFragment()
            val args = Bundle()
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.layout_connection_settings, rootKey);

        confirmationsPreference = findPreference("confirmations_preference")

        //TODO: DOESNT WORK. REPLACE WITH https://stackoverflow.com/questions/5298370/how-to-add-a-button-to-a-preferencescreen
        // ?
        confirmationsPreference?.button_confirm?.setOnClickListener()
        {
            /*
            requireActivity().supportFragmentManager.popBackStack()
            //getActivity()?.onBackPressed();
            Toast.makeText(activity, "Its toast!", Toast.LENGTH_LONG).show()
             */
        }
    }

}
