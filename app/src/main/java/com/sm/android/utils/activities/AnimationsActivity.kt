package com.sm.android.utils.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sm.android.utils.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {
    lateinit var binding : ActivityAnimationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAnimationYolo.setOnClickListener {
            startActivity(Intent(this, AnimationYoyoActivity::class.java))
        }

        binding.btnAnimationCustom.setOnClickListener {
            startActivity(Intent(this, AnimationsCustomActivity::class.java))
        }

    }
}

