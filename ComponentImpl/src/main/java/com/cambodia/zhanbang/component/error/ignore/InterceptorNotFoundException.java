package com.cambodia.zhanbang.component.error.ignore;

/**
 * time   : 2019/01/10

 */
public class InterceptorNotFoundException extends RuntimeException {

    public InterceptorNotFoundException() {
    }

    public InterceptorNotFoundException(String message) {
        super(message);
    }

    public InterceptorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterceptorNotFoundException(Throwable cause) {
        super(cause);
    }

}
