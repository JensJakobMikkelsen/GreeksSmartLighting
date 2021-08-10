package com.example.blue.ExtendedViewClasses

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.example.blue.R

public class ConfirmationsPreference(context: Context?, attrs: AttributeSet?) :
    Preference(context, attrs) {

    var button_confirm: Button? = null
    //lateinit var button_exit: Button
    //var _exitClickListener: View.OnClickListener? = null

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        button_confirm = holder.findViewById(R.id.button_preferences_confirm) as Button
       // button_exit = holder.findViewById(R.id.button_preferences_exit) as Button
    }

/*
    @JvmName("setConfirmationOnClickListeners")
    fun setImageClickListener(onClickListener: View.OnClickListener?) {
        textViewClickListener = onClickListener
    }
  */
}