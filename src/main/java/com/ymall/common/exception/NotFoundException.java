package com.ymall.common.exception;

import com.ymall.common.ServerResponse;
import org.springframework.http.HttpStatus;

/**
 * Created by zc on 2017/6/13.
 */
public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public Object getErrorObject() {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.NOT_FOUND.value(),this.getMessage());
    }
}
