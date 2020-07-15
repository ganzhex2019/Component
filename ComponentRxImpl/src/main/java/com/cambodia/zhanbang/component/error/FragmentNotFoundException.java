package com.cambodia.zhanbang.component.error;

/**
 * time   : 2019/01/09

 */
public class FragmentNotFoundException extends RuntimeException {
    public FragmentNotFoundException() {
    }

    public FragmentNotFoundException(String message) {
        super(message);
    }

    public FragmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FragmentNotFoundException(Throwable cause) {
        super(cause);
    }
}
