package com.example.kafkademo.bean;

import lombok.Data;

/**
 *  * @Description: user实体类
 *  * @Author: chenxue
 *  * @Date: 2020-10-31 14:39
 *  * @since
 *  
 */
@Data
public class UserEntity {
    /**
     * 姓名
     */
    private String name;

    /**
     * 住址
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话号码
     */
    private Integer phoneNum;
}
