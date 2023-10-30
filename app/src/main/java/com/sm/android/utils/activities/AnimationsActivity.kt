package com.sm.android.utils.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.github.hujiaweibujidao.wava.Techniques
import com.github.hujiaweibujidao.wava.YoYo
import com.sm.android.utils.databinding.ActivityAnimationsBinding



class AnimationsActivity : AppCompatActivity() {
    lateinit var binding : ActivityAnimationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAnimation1.setOnClickListener {
            YoYo.with(Techniques.FadeIn).duration(1000).repeat(1)
                .interpolate(AccelerateDecelerateInterpolator())
                .listen(object : AnimatorListenerAdapter() {
                    override fun onAnimationCancel(animation: Animator) {
                        Toast.makeText(this@AnimationsActivity, "test", Toast.LENGTH_SHORT).show()
                    }
                })
                .playOn(binding.btnAnimation1)
        }

        binding.btnAnimation2.setOnClickListener {
            doanimation(Techniques.FadeInLeft)
        }

        binding.btnAnimation3.setOnClickListener {
            doanimation(Techniques.Flash)
        }
        binding.btnAnimation4.setOnClickListener {
            doanimation(Techniques.Pulse)
        }
        binding.btnAnimation5.setOnClickListener {
            doanimation(Techniques.RubberBand)
        }
        binding.btnAnimation6.setOnClickListener {
            doanimation(Techniques.Shake)
        }
        binding.btnAnimation7.setOnClickListener {
            doanimation(Techniques.Swing)
        }
        binding.btnAnimation8.setOnClickListener {
            doanimation(Techniques.Wobble)
        }
        binding.btnAnimation9.setOnClickListener {
            doanimation(Techniques.Bounce)
        }
        binding.btnAnimation10.setOnClickListener {
            doanimation(Techniques.Tada)
        }
        binding.btnAnimation11.setOnClickListener {
            doanimation(Techniques.StandUp)
        }
        binding.btnAnimation12.setOnClickListener {
            doanimation(Techniques.Wave)
        }
        binding.btnAnimation13.setOnClickListener {
            doanimation(Techniques.Hinge)
        }
        binding.btnAnimation14.setOnClickListener {
            doanimation(Techniques.RollIn)
        }
        binding.btnAnimation15.setOnClickListener {
            doanimation(Techniques.RollOut)
        }
    }


    private fun doanimation(techniques: Techniques) {
        YoYo.with(techniques)
            .duration(700)
            .repeat(1)
            .playOn(binding.btnAnimation);
    }
}


//Attension
//Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave
//
//Special
//Hinge, RollIn, RollOut,Landing,TakingOff,DropOut
//
//Bounce
//BounceIn, BounceInDown, BounceInLeft, BounceInRight, BounceInUp
//
//Fade
//FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight
//
//FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp
//
//Flip
//FlipInX, FlipOutX, FlipOutY
//
//Rotate
//RotateIn, RotateInDownLeft, RotateInDownRight, RotateInUpLeft, RotateInUpRight
//
//RotateOut, RotateOutDownLeft, RotateOutDownRight, RotateOutUpLeft, RotateOutUpRight
//
//Slide
//SlideInLeft, SlideInRight, SlideInUp, SlideInDown
//
//SlideOutLeft, SlideOutRight, SlideOutUp, SlideOutDown
//
//Zoom
//ZoomIn, ZoomInDown, ZoomInLeft, ZoomInRight, ZoomInUp
//
//ZoomOut, ZoomOutDown, ZoomOutLeft, ZoomOutRight, ZoomOutUp