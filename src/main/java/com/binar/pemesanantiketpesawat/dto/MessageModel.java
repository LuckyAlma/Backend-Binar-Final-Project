package com.binar.pemesanantiketpesawat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModel<T> {

    private Integer status;
    private String message;
    private T data;
}
