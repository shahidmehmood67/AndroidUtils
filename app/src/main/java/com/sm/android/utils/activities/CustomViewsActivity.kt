package com.sm.android.utils.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sm.android.utils.R
import com.sm.android.utils.databinding.ActivityCustomViewsBinding

class CustomViewsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCustomViewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}