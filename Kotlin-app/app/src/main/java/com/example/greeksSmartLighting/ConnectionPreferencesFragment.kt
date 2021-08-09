package com.webianks.bluechat

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.example.blue.R
import java.util.logging.Handler

class ConnectionPreferencesFragment : PreferenceFragmentCompat() {

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
    }

}
