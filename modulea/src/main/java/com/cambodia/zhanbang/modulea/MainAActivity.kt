package com.cambodia.zhanbang.modulea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.xiaojinzi.component.anno.RouterAnno

import com.cambodia.zhanbang.component.anno.RouterAnno

@RouterAnno(
    host = "modulea", // host 可省略不写
    path = "maina",
    desc = "业务组件1的测试界面"
)
class MainAActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maina)
    }
}
