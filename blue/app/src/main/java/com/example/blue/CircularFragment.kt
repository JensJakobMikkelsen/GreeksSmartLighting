package com.webianks.bluechat

import android.R.attr.button
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.blue.R
import java.util.*

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView


/**
 * Created by ramankit on 24/7/17.
 */

class CircularFragment : Fragment() {

    val visibility = 0

    companion object {
        fun newInstance(): CircularFragment {
            val myFragment = CircularFragment()
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

        val mView: View  = LayoutInflater.from(activity).inflate(R.layout.circular_reveal, container, false)

        mView.setOnClickListener(View.OnClickListener {
            toggleInformationView(mView)
        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mView!!.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onLayoutChange(
                    v: View,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    v.removeOnLayoutChangeListener(this)
                    toggleInformationView(mView)
                }
            })
        }
        return mView
    }

    private fun toggleInformationView(view: View) {
        val infoContainer = view.findViewById<View>(R.id.bluecircle)

        infoContainer.setOnClickListener(View.OnClickListener {
            //toggleInformationView(infoContainer)
        })

        val cx = (view.left + view.right) / 2
        val cy = (view.top + view.bottom) / 2
        val radius: Float = Math.max(infoContainer.getWidth(), infoContainer.getHeight()) * 2.0f
        if (infoContainer.getVisibility() === View.INVISIBLE) {
            infoContainer.setVisibility(View.VISIBLE)
            ViewAnimationUtils.createCircularReveal(infoContainer, cx, cy, 0f, radius).start()
        } else {
            val reveal = ViewAnimationUtils.createCircularReveal(
                infoContainer, cx, cy, radius, 0f
            )
            reveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    infoContainer.setVisibility(View.INVISIBLE)
                }
            })
            reveal.start()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun revealFAB(mView: View) {
        val view: View = mView.findViewById(R.id.bluecircle)
        val cx = view.width / 2
        val cy = view.height / 2
        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
        view.visibility = View.VISIBLE
        anim.start()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun hideFAB(mView: View) {
        val view: View = mView.findViewById(R.id.bluecircle)
        val cx = view.width / 2
        val cy = view.height / 2
        val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        anim.start()
    }


}