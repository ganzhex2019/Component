package com.cambodia.zhanbang.component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.xiaojinzi.component.impl.Router
import com.cambodia.zhanbang.component.impl.Router
import kotlinx.android.synthetic.main.activity_main.*


class Mainctivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn1.setOnClickListener {
            Router
                .with(this@Mainctivity)
                .host("modulea")
                .path("maina")

                .forward()

        }

        btn2.setOnClickListener {
            Router
                .with(this@Mainctivity)
                .host("moduleb")
                .path("mainb")

                .forward()
        }
    }
}