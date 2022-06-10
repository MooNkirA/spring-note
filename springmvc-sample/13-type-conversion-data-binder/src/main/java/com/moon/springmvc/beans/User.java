package com.moon.springmvc.beans;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-06 15:04
 * @description
 */
@Data
public class User {

    @DateTimeFormat(pattern = "yyyy|MM|dd")
    private Date birthday;
    private Address address;

}
