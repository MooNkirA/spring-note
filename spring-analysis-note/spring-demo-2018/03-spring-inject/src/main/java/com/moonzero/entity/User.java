package com.moonzero.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String[] arrs;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties props;

    @Override
    public String toString() {
        return "User [arrs=" + Arrays.toString(arrs) + ", list=" + list + ", set=" + set + ", map=" + map + ", props="
                + props + "]";
    }

    public void test() {
        System.out.println("数组：" + Arrays.toString(arrs));
        System.out.println("list集合：" + list);
        System.out.println("set集合：" + set);
        System.out.println("Map集合：" + map);
        System.out.println("Properties：" + props);
    }

    public String[] getArrs() {
        return arrs;
    }

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
