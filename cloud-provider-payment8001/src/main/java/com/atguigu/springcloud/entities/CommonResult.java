package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *      Json封装实体类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data; //泛型表示是什么对象就传什么对象过去

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
