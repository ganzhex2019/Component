package com.cambodia.zhanbang.component;

import android.app.Application;
import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.annotation.Keep;
import android.util.SparseArray;
import java.lang.Class;

public class User  {


    @Info(id = 1)
    public int id;
    @Info()
    public String name;
    @Info(password = "123456")
    public String password;
}
