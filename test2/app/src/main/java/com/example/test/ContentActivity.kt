 package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.content_layout.*
import kotlinx.android.synthetic.main.content_layout.im
import kotlinx.android.synthetic.main.content_layout.tvTitle
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.content_layout.tvContent as tvContent1

 class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)
        tvTitle.text = intent.getStringExtra("title")
        tvContent.text = intent.getStringExtra("content")
        im.setImageResource(intent.getIntExtra("image", R.drawable.ic_kurs))
    }
}
