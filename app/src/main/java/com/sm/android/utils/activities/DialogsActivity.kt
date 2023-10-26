package com.sm.android.utils.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sm.android.utils.databinding.ActivityDialogsBinding
import com.sm.android.utils.databinding.ActivityMainBinding

class DialogsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}