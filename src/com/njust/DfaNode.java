package com.njust;

import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * DFA节点，T0->wT1
 *
 * @author tomato
 * @create 2018-03-22 下午2:12
 */
public class DfaNode {
    /**
     * 起点终结符
     */
    private List<String> name;
    /**
     * 起点终结符
     */
    private String node;
    /**
     * 经过的非终结符
     */
    private String w;
    /**
     * 到达的终结符
     */
    private List<String> next;
    /**
     * 是否是终态
     */
    private boolean finalState;

    /**
     * 构造函数初始化
     */
    DfaNode() {
        name = new ArrayList<String>();
        node = "";
        w = "";
        next = new ArrayList<String>();
        finalState = false;
    }

    DfaNode(List<String> name, String node) {
        this.name = name;
        this.node = node;
        w = "";
        next = new ArrayList<String>();
        finalState = false;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.add(name);
    }

    public void setName(List<String> name) {
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

    public boolean isFinalState() {
        return finalState;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setFinalState() {
        for (String aName : name) {
            if (aName.equals(Main.TERMINATOR)) {
                this.finalState = true;
                return;
            }
        }
    }
}
