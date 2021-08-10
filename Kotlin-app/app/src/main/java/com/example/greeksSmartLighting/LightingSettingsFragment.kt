package com.webianks.bluechat

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.RequiresApi
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import com.example.blue.R
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView


class MenuSelectionFragment : Fragment() {

    var currentView: View? = null
    var handlerAnimation = Handler()
    var statusAnimation = false
    lateinit var connectionPreferencesFragment: ConnectionPreferencesFragment

    companion object {
        fun newInstance(): MenuSelectionFragment{
            val myFragment = MenuSelectionFragment()
            val args = Bundle()
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView: View  = LayoutInflater.from(activity).inflate(R.layout.menu_selection_carousel, container, false)
        setupCarousel(mView)
        return mView
    }

    private fun getColorNumber(seekBar: SeekBar): Int
    {
        val seekbar_progress = seekBar.progress
        var colorNumber = 0
        if(seekbar_progress == 0)
        {
            //items[carousel.currentIndex].colorFilter = false
        }
        else if(seekbar_progress in 1..9) {

            colorNumber = 0x0d0d0d

        } else if (seekbar_progress in 10..19) {
            colorNumber = 0x171717
        }
        else if (seekbar_progress in 20..29) {
            colorNumber = 0x242424
        }
        else if (seekbar_progress in 30..39) {
            colorNumber = 0x363636
        }
        else if (seekbar_progress in 40..49) {
            colorNumber = 0x424242
        }
        else if (seekbar_progress in 50..59) {
            colorNumber = 0x525252
        }
        else if (seekbar_progress in 60..69) {
            colorNumber = 0x666666
        }
        else if (seekbar_progress in 70..79) {
            colorNumber = 0x7a7a7a
        }
        else if (seekbar_progress in 80..89) {
            colorNumber = 0x8a8a8a
        }
        else if (seekbar_progress in 90..100) {
            colorNumber = 0x969696
        }
        return colorNumber
    }

    private fun setupCarousel(mView: View) {
        val carousel = mView.findViewById<Carousel>(R.id.carouselMenu) ?: return
        val numImages = items.size
        var colorNumber = 0
        var currentColor = 0

        var connectionSettingsButton: MaterialTextView = mView.findViewById(R.id.text_connectionoptions)
        connectionSettingsButton.setOnClickListener()
        {

            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            connectionPreferencesFragment = ConnectionPreferencesFragment.newInstance()

            //TODO: Why does mainscreen work, but not mainscreen_preferences???
            fragmentTransaction.replace(R.id.mainScreen, connectionPreferencesFragment, "connectionPreferencesFragment")
            fragmentTransaction.addToBackStack("connectionPreferencesFragment")
            fragmentTransaction.commit()

            //childFragmentManager.beginTransaction().replace(R.id.fragment_frame, ConnectionPreferencesFragment.newInstance()).addToBackStack(null).commit()
        }

        val lightingsSettingsButton: MaterialTextView = mView.findViewById(R.id.text_lightingoptions)
        lightingsSettingsButton.setOnClickListener()
        {
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                val dialogView = layoutInflater.inflate(R.layout.lighting_settings, null)
                val seekbar: SeekBar = dialogView.findViewById(R.id.brightness_seekbar)




                // Set other dialog properties

                val radioButton: RadioButton = dialogView.findViewById(R.id.radio_pulsing_speed_slow)
                val floatingAnimation: ImageView = dialogView.findViewById(R.id.current_lighting_1)
                val hidden_floatingAnimation: ImageView = dialogView.findViewById(R.id.current_lighting_2)
                radioButton.setOnClickListener {
                    if (statusAnimation) {
                        stopPulse()
                    }
                    else {
                        startPulse(floatingAnimation, hidden_floatingAnimation)
                    }
                    statusAnimation = !statusAnimation
                }

                val materialCardView: MaterialCardView = dialogView.findViewById(R.id.material_card_color)
                val colorTextView: TextView = dialogView.findViewById(R.id.text_material_card_color)
                val colorPickerButton: Button = dialogView.findViewById(R.id.button_color_spectrum)


                colorPickerButton.setOnClickListener()
                {
                    ColorPickerDialog
                        .Builder(dialogView.context)        				// Pass Activity Instance
                        .setTitle("Pick Theme")           	// Default "Choose Color"
                        .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                        .setDefaultColor(0x00)     // Pass Default Color
                        .setColorListener { color, colorHex ->

                            materialCardView.setBackgroundColor(color)
                            colorTextView.text = colorHex

                            val filter: ColorFilter = LightingColorFilter(color, 1)
                            floatingAnimation.colorFilter = filter
                            hidden_floatingAnimation.colorFilter = filter


                            /*
                            floatingAnimation.outlineSpotShadowColor = color
                            hidden_floatingAnimation.outlineSpotShadowColor = color
*/
                        }
                        .show()

                }

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    colorNumber = getColorNumber(seekbar)
                    if(colorNumber > 0) items[carousel.currentIndex].colorFilter = true
                    items[carousel.currentIndex].colorNumber = colorNumber
                    carousel.refresh()
                }

                val step = 1
                val max = 100
                val min = 0
                seekbar?.setMax( (max - min) / step )

                seekbar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar, progress: Int,
                        fromUser: Boolean
                    ) {

                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                        colorNumber = getColorNumber(seekBar)
                        val filter: ColorFilter = LightingColorFilter(colorNumber, 1)
                        floatingAnimation.colorFilter = filter
                        hidden_floatingAnimation.colorFilter = filter

                    }
                })

                // Create the AlertDialog
                builder.setView(dialogView)
                val dialog = builder.create()
                builder.show()

            }
            carousel.refresh()
        }
/*
*/
        val pulseButton: MaterialTextView = mView.findViewById(R.id.text_pulse)
        pulseButton.setOnClickListener {
            items[carousel.currentIndex].pulsing = !items[carousel.currentIndex].pulsing
            carousel.refresh()
        }

        carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return numImages
            }

            @SuppressLint("ResourceAsColor")
            override fun populate(view: View, index: Int) {
                currentView = view
                val item = items[index]

                if (currentView is GifImageView) {
                    (currentView as GifImageView).setImageResource(item.Id)
                    val drawable = (currentView as GifImageView).getDrawable() as GifDrawable
                    if(item.pulsing == true)
                    {
                        drawable.start()
                    }
                    else
                    {
                        drawable.stop()
                    }

                    if(item.colorFilter == true)
                    {
                        val filter: ColorFilter = LightingColorFilter(item.colorNumber, 1)
                        drawable.setColorFilter(filter)
                    }
                }

            }

            override fun onNewItem(index: Int) {
            }
        })
    }

    var items = arrayOf(
        MenuItem(
            R.drawable.pulsing_blue, false, 0x808000, false,
        ),
        MenuItem(
            R.drawable.pulsing_red, false, 0x808000, false,
        ),
        MenuItem(
            R.drawable.pulsing_green, false, 0x808000, false,
        ),
        MenuItem(
            R.drawable.pulsing_yellow, false, 0x808000, false,
        ),
        MenuItem(
            R.drawable.pulsing_purple, false, 0x808000, false,
        ),

        MenuItem(
            R.drawable.pulsing_pink, false, 0x808000, false,

            ),

    )

    private fun startPulse(mView1: View, mView2: View) {
        runnable.run_custom(mView1, mView2)
    }

    private fun stopPulse() {
        handlerAnimation.removeCallbacks(runnable)
    }

    private var runnable = object : Runnable {
        fun run_custom(mView1: View, mView2: View) {

            mView1.animate().scaleX(1.3f).scaleY(1.3f).alpha(0f).setDuration(1000)
                .withEndAction {
                    mView1.scaleX = 1f
                    mView1.scaleY = 1f
                    mView1.alpha = 1f
                }

            mView2.animate().scaleX(1.3f).scaleY(1.3f).alpha(0f).setDuration(700)
                .withEndAction {
                    mView2.scaleX = 1f
                    mView2.scaleY = 1f
                    mView2.alpha = 1f
                }

            handlerAnimation.postDelayed(this, 1500)
        }

        override fun run() {
            var randomVar = 0
        }
    }
}

data class MenuItem(val Id: Int, var pulsing: Boolean, var colorNumber: Int, var colorFilter: Boolean)