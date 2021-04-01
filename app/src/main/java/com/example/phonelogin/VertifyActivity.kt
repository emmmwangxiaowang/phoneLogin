package com.example.phonelogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class VertifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertify)

        //获取数据
        intent.getStringExtra("phone").also {
             Log.v("wxw",it.toString())
        }
    }
}