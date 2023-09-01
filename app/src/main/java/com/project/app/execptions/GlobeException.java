package com.project.app.execptions;


import com.project.app.util.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeException {


    @ExceptionHandler(Throwable.class)
    public R exception(Throwable exception) {
        exception.printStackTrace();
        return R.fail(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception exception) {
        exception.printStackTrace();
        return R.fail(exception.getMessage());
    }

  @ExceptionHandler(AccessDeniedException.class)
    public R exception(AccessDeniedException exception) {
        exception.printStackTrace();
        return R.fail("暂无权限");
    }

    @ExceptionHandler(RuntimeException.class)
    public R exception(RuntimeException exception) {
        exception.printStackTrace();
        return R.fail(exception.getMessage());
    }


    @ExceptionHandler(MyRuntimeException.class)
    public R exception(MyRuntimeException exception) {
        exception.printStackTrace();
        return R.fail(exception.getMsg());
    }


}
