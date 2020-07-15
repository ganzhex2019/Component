package com.cambodia.zhanbang.component;

import java.lang.reflect.Field;

public class Test {

    public static void main(String args[]){

       // System.out.println("xx");
        User user = new User();

        Field[] fields = user.getClass().getDeclaredFields();
        for (Field f : fields) {
            Info info = f.getAnnotation(Info.class);
            if (info != null) {
                if (f.getName().equals("id")) {
                    System.out.println(f.getName() + "," + info.id());
                } else if (f.getName().equals("name")) {
                    System.out.println(f.getName() + "," + info.name());
                } else if (f.getName().equals("password")) {
                    System.out.println(f.getName() + "," + info.password());
                }
            }
        }

    }
}
