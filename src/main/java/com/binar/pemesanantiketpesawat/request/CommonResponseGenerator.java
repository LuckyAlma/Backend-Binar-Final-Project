package com.binar.pemesanantiketpesawat.request;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> CommonResponse successResponse(T data) {
        // declare new object
        CommonResponse commonResponse = new CommonResponse();

        // set new data
        commonResponse.setData(data);

        // return object
        return commonResponse;
    }
}
