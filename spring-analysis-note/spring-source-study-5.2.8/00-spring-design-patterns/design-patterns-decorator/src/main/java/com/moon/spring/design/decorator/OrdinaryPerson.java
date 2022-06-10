package com.moon.spring.design.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义普通人类。是被装饰对象，后续装饰者对该对象进行修改装饰
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-27 19:15
 * @description
 */
public class OrdinaryPerson implements Person {

    private List<String> languageList = new ArrayList<>();

    private List<String> motionList = new ArrayList<>();

    public OrdinaryPerson() {
        this.languageList.add("native language");
        this.motionList.add("walk");
    }

    @Override
    public void language() {
        System.out.println("普通人学会的语言 --> " + this.languageList.toString());
    }

    @Override
    public void motion() {
        System.out.println("普通人学会的动作 --> " + this.motionList.toString());
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<String> languageList) {
        this.languageList = languageList;
    }

    public List<String> getMotionList() {
        return motionList;
    }

    public void setMotionList(List<String> motionList) {
        this.motionList = motionList;
    }
}
