package com.njust;

import java.util.ArrayList;
import java.util.List;

/**
 * NFA节点，A->aB
 *
 * @author tomato
 * @create 2018-03-21 下午7:54
 */
public class NfaNode {
    /**
     * 起点终结符
     */
    private String name;
    /**
     * 经过的非终结符
     */
    private String w;
    /**
     * 到达的终结符
     */
    private List<String> next;

    /**
     * 构造函数初始化
     */
    NfaNode() {
        name = "";
        w = "";
        next = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public List<String> getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next.add(next);
    }

    public void setNext(List<String> next) {
        this.next = next;
    }
}
