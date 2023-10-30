package com.sm.android.utils.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sm.android.utils.databinding.ActivityAnimationsCustomBinding
import com.sm.android.utils.databinding.ActivityCustomViewsBinding

class AnimationsCustomActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimationsCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}