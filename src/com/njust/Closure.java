package com.njust;

import java.util.ArrayList;
import java.util.List;

/**
 * 闭包类，计算DFA
 *
 * @author tomato
 * @create 2018-03-21 下午10:28
 */
public class Closure {
    private List<String> set;
    private List<String> node;
    private boolean flag;

    Closure() {
        set = new ArrayList<String>();
        node = new ArrayList<String>();
        flag = false;
    }

    public List<String> getSet() {
        return set;
    }

    public void setSet(List<String> set) {
        this.set = set;
    }

    public void setSet(String set) {
        this.set.add(set);
    }

    public List<String> getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node.add(node);
    }

    public void setNode(List<String> node) {
        this.node = node;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void calculateClosure(List<NfaNode> nfaNodes) {
        int i = 0, j = 0, k = 0;
        for (k = 0; k < node.size(); k++) {
            for (i = 0; i < nfaNodes.size(); i++) {
                if (nfaNodes.get(i).getName().equals(node.get(k)) && nfaNodes.get(i).getW().equals(Main.EBCL)) {
                    for (j = 0; j < nfaNodes.get(i).getNext().size(); j++) {
                        if (!set.contains(nfaNodes.get(i).getNext().get(j))) {
                            set.add(nfaNodes.get(i).getNext().get(j));
                            node.add(nfaNodes.get(i).getNext().get(j));
                        }
                    }
                    break;
                }
            }
        }
    }
}
