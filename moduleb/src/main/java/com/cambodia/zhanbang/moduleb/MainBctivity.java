package com.cambodia.zhanbang.moduleb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//import com.xiaojinzi.component.anno.RouterAnno;

import com.cambodia.zhanbang.component.anno.RouterAnno;


@RouterAnno(
        host = "moduleb", // host 可省略不写
        path = "mainb",
        desc = "业务组件2的测试界面"
)
public class MainBctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainb);
    }
}
