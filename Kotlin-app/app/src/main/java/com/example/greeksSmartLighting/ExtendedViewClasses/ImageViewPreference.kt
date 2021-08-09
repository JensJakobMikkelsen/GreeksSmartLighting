package com.example.blue.ExtendedViewClasses

import com.example.blue.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.preference.Preference

import androidx.preference.PreferenceViewHolder


// https://stackoverflow.com/questions/59507248/how-to-add-image-view-that-can-be-edited-inside-a-preferencescreen-using-prefere

internal class ImageViewPreference(context: Context?, attrs: AttributeSet?) :
    Preference(context, attrs) {
    private var imageView: ImageView? = null
    var imageClickListener: View.OnClickListener? = null

    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        imageView = holder.findViewById(R.id.imageview_preferences) as ImageView
        imageView!!.setOnClickListener(imageClickListener)
    }

    @JvmName("setImageClickListener1")
    fun setImageClickListener(onClickListener: View.OnClickListener?) {
        imageClickListener = onClickListener
    }
}