package com.sm.android.utils.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.github.hujiaweibujidao.wava.Techniques
import com.github.hujiaweibujidao.wava.YoYo
import com.sm.android.utils.databinding.ActivityAnimationYoyoBinding
import render.animations.Bounce
import render.animations.Render
import render.animations.Slide

class AnimationYoyoActivity : AppCompatActivity() {

    lateinit var binding : ActivityAnimationYoyoBinding
    lateinit var render : Render

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationYoyoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create Render Class
        render = Render(this)

        binding.btnAnimation1.setOnClickListener {
            YoYo.with(Techniques.FadeIn).duration(1000).repeat(1)
                .interpolate(AccelerateDecelerateInterpolator())
                .listen(object : AnimatorListenerAdapter() {
                    override fun onAnimationCancel(animation: Animator) {
                        Toast.makeText(this@AnimationYoyoActivity, "test", Toast.LENGTH_SHORT).show()
                    }
                })
                .playOn(binding.btnAnimation)
        }

        binding.btnAnimation2.setOnClickListener {
            doanimationYoyo(Techniques.FadeInLeft)
        }

        binding.btnAnimation3.setOnClickListener {
            doanimationYoyo(Techniques.Flash)
        }
        binding.btnAnimation4.setOnClickListener {
            doanimationYoyo(Techniques.Pulse)
        }
        binding.btnAnimation5.setOnClickListener {
            doanimationYoyo(Techniques.RubberBand)
        }
        binding.btnAnimation6.setOnClickListener {
            doanimationYoyo(Techniques.Shake)
        }
        binding.btnAnimation7.setOnClickListener {
            doanimationYoyo(Techniques.Swing)
        }
        binding.btnAnimation8.setOnClickListener {
            doanimationYoyo(Techniques.Wobble)
        }
        binding.btnAnimation9.setOnClickListener {
            doanimationYoyo(Techniques.Bounce)
        }
        binding.btnAnimation10.setOnClickListener {
            doanimationYoyo(Techniques.Tada)
        }
        binding.btnAnimation11.setOnClickListener {
            doanimationYoyo(Techniques.StandUp)
        }
        binding.btnAnimation12.setOnClickListener {
            doanimationYoyo(Techniques.Wave)
        }
        binding.btnAnimation13.setOnClickListener {
            val abc = Bounce().InRight(binding.btnAnimation)
            doanimationyoyokotlin(abc)
        }
        binding.btnAnimation14.setOnClickListener {
            val abc = Bounce().InDown(binding.btnAnimation)
            doanimationyoyokotlin(abc)
        }
        binding.btnAnimation15.setOnClickListener {
            val abc = Slide().InRight(binding.btnAnimation)
            doanimationyoyokotlin(abc)
        }
    }


    private fun doanimationYoyo(techniques: Techniques) {
        YoYo.with(techniques)
            .duration(700)
            .repeat(1)
            .playOn(binding.btnAnimation);
    }

    private fun doanimationyoyokotlin(techniques: AnimatorSet){
        // Set Animation
        render.setAnimation(techniques)
        render.start()
    }
}


//More Animations

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

