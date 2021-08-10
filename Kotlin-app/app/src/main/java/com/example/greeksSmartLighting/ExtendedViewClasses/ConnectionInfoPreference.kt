package com.example.blue.ExtendedViewClasses

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.example.blue.R

// https://stackoverflow.com/questions/59507248/how-to-add-image-view-that-can-be-edited-inside-a-preferencescreen-using-prefere

class ConnectionInfoPreference(context: Context?, attrs: AttributeSet?) :
    Preference(context, attrs) {
    private var textView_signal_strength: TextView? = null
    var textViewClickListener: View.OnClickListener? = null

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        textView_signal_strength = holder.findViewById(R.id.textview_preferences_signal_strength) as TextView
        textView_signal_strength!!.setOnClickListener(textViewClickListener)
    }

    @JvmName("setTextViewClickListener1")
    fun setImageClickListener(onClickListener: View.OnClickListener?) {
        textViewClickListener = onClickListener
    }
}