package com.moon.springsample.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 用户信息的实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 17:36
 * @description
 */
public class Userinfo implements Serializable {

    private Integer id;
    private byte[] images;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Userinfo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("images=" + Arrays.toString(images))
                .add("description='" + description + "'")
                .toString();
    }
}
