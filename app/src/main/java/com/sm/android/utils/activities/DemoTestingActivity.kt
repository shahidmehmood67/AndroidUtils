package com.sm.android.utils.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sm.android.utils.R
import com.sm.android.utils.databinding.ActivityDemoTestingBinding
import com.sm.android.utils.rxutils.clicks
import com.sm.android.utils.rxutils.BindLife

class DemoTestingActivity : AppCompatActivity() , BindLife by BindLife() {

    lateinit var binding: ActivityDemoTestingBinding

    var clickcount = 1
    var clickcount2 = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnClickHandled.setOnClickListener {
                Log.e("DemoTestingActivity  ", "onCreate  : line(24) clicknumber: ${clickcount}");
                clickcount++
                startActivity(Intent(this@DemoTestingActivity,MainActivity::class.java))
            }


            btnClickHandled2.clicks()
                .doOnNext {
                    Log.e("DemoTestingActivity  ", "onCreate  : line(33) clicknumber: ${clickcount2}");
                    clickcount2++
                    startActivity(Intent(this@DemoTestingActivity,MainActivity::class.java))
                }.bindLife()


        }


    }
}