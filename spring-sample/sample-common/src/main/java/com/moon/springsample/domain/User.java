package com.moon.springsample.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:33
 * @description
 */
@Data
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String email;
    private Date birthday;
    private String gender;
    private String mobile;
    private String nickname;

}
