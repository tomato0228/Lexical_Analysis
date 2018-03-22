package com.njust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入正规文法创建NFA，NFA-->DFA
 *
 * @author tomato
 * @create 2018-03-21 下午8:26
 */
public class Nfa {
    /**
     * 保存nfa结点
     */
    private List<NfaNode> nfaNodes;
    /**
     * 保存ε——closures
     */
    private List<Closure> closures;
    /**
     * 保存dfa结点
     */
    private List<DfaNode> dfaNodes;
    /**
     * DFA结点的编号
     */
    private int lastNode;

    Nfa() {
        nfaNodes = new ArrayList<NfaNode>();
        closures = new ArrayList<Closure>();
        dfaNodes = new ArrayList<DfaNode>();
        lastNode = 0;
    }

    public int inputNfa(String address) {
        try {
            fileRead(address);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    private void fileRead(String address) throws Exception {
        //定义一个file对象，用来初始化FileReader
        File file = new File(address);
        //定义一个fileReader对象，用来初始化BufferedReader
        FileReader reader = new FileReader(file);
        //new一个BufferedReader对象，将文件内容读取到缓存
        BufferedReader bReader = new BufferedReader(reader);
        String s;
        int i = 0, j = 0;
        NfaNode nfaNode;
        //逐行读取文件内容，不读取换行符和末尾的空格
        while ((s = bReader.readLine()) != null) {
            //清除字符串中的空格
            s = s.replace(" ", "");
            //该行为空，跳过
            if (s.length() <= 2) {
                continue;
            }
            if (Utils.isNotUpperLetter(s.charAt(3)) && s.length() >= 5) {
                for (i = 0; i < nfaNodes.size(); i++) {
                    if (nfaNodes.get(i).getName().equals(String.valueOf(s.charAt(0))) && nfaNodes.get(i).getW().equals(String.valueOf(s.charAt(3)))) {
                        nfaNodes.get(i).setNext(String.valueOf(s.charAt(4)));
                        break;
                    }
                }
                if (i == nfaNodes.size()) {
                    nfaNode = new NfaNode();
                    nfaNode.setName(String.valueOf(s.charAt(0)));
                    nfaNode.setW(String.valueOf(s.charAt(3)));
                    nfaNode.setNext(String.valueOf(s.charAt(4)));
                    nfaNodes.add(nfaNode);
                }
            } else if (Utils.isNotUpperLetter(s.charAt(3)) && s.length() < 5) {
                for (i = 0; i < nfaNodes.size(); i++) {
                    if (nfaNodes.get(i).getName().equals(String.valueOf(s.charAt(0))) && nfaNodes.get(i).getW().equals(String.valueOf(s.charAt(3)))) {
                        nfaNodes.get(i).setNext(Main.TERMINATOR);
                    }
                }
                if (i == nfaNodes.size()) {
                    nfaNode = new NfaNode();
                    nfaNode.setName(String.valueOf(s.charAt(0)));
                    nfaNode.setW(String.valueOf(s.charAt(3)));
                    nfaNode.setNext(Main.TERMINATOR);
                    nfaNodes.add(nfaNode);
                }
            } else if (Utils.isUpperLetter(s.charAt(3)) && s.length() < 5) {
                for (i = 0; i < nfaNodes.size(); i++) {
                    if (nfaNodes.get(i).getName().equals(String.valueOf(s.charAt(0))) && nfaNodes.get(i).getW().equals(Main.EBCL)) {
                        nfaNodes.get(i).setNext(String.valueOf(s.charAt(3)));
                    }
                }
                if (i == nfaNodes.size()) {
                    nfaNode = new NfaNode();
                    nfaNode.setName(String.valueOf(s.charAt(0)));
                    nfaNode.setW(Main.EBCL);
                    nfaNode.setNext(String.valueOf(s.charAt(3)));
                    nfaNodes.add(nfaNode);
                }
            }
        }
        bReader.close();
/*
        System.out.println(nfaNodes.size());
        for (i = 0; i < nfaNodes.size(); i++) {
            System.out.print(nfaNodes.get(i).getName() + "\t");
            System.out.print(nfaNodes.get(i).getW() + "\t");
            System.out.print(nfaNodes.get(i).getNext());
            System.out.println();
        }
*/
    }

    public int nfaToDfa() {
        String move = nfaNodes.get(0).getName();
        int i = 0, j = 0, k = 0, p = 0, q = 0, r = 0;
        Closure closureT = new Closure();
        DfaNode dfaNode = new DfaNode();
        List<String> lowCase = new ArrayList<String>();
        for (i = 0; i < nfaNodes.size(); i++) {
            if (!lowCase.contains(nfaNodes.get(i).getW())) {
                lowCase.add(nfaNodes.get(i).getW());
            }
        }
        closureT.setSet(move);
        closureT.setNode(move);
        closureT.calculateClosure(nfaNodes);
        closures.add(closureT);
        addDfaNode(closureT.getNode());
        while ((i = haveFalseT()) != -1) {
            closures.get(i).setFlag(true);
            for (p = 0; p < lowCase.size(); p++) {
                closureT = new Closure();
                for (j = 0; j < closures.get(i).getSet().size(); j++) {
                    for (k = 0; k < nfaNodes.size(); k++) {
                        if (closures.get(i).getSet().get(j).equals(nfaNodes.get(k).getName()) && nfaNodes.get(k).getW().equals(lowCase.get(p))) {
                            for (q = 0; q < nfaNodes.get(k).getNext().size(); q++) {
                                if (!closureT.getNode().contains(nfaNodes.get(k).getNext().get(q))) {
                                    closureT.setSet(nfaNodes.get(k).getNext().get(q));
                                    closureT.setNode(nfaNodes.get(k).getNext().get(q));
                                }
                            }
                            break;
                        }
                    }
                }
                closureT.calculateClosure(nfaNodes);
                if (!closureT.getNode().isEmpty()) {
                    addDfa(closures.get(i).getSet(), lowCase.get(p), closureT.getSet());
                    for (r = 0; r < closures.size(); r++) {
                        if ((closures.get(r).getSet().equals(closureT.getSet()))) {
                            break;
                        }
                    }
                    if (r == closures.size()) {
                        closures.add(closureT);
                    }
                }
            }
        }
/*
        for (r = 0; r < closures.size(); r++) {
            System.out.println(closures.get(r).getSet());
        }
        for (r = 0; r < lastNode; r++) {
            for (i = 0; i < dfaNodes.size();i++) {
                if (dfaNodes.get(i).getNode().equals(Integer.toString(r))) {
                    System.out.println(dfaNodes.get(i).getNode() + "\t" + dfaNodes.get(i).getName());
                    break;
                }
            }
            for(;i < dfaNodes.size();i++){
                if (dfaNodes.get(i).getNode().equals(Integer.toString(r))){
                    System.out.println("\t\t" + dfaNodes.get(i).getW() + "\t" + dfaNodes.get(i).getNext());
                }
            }
        }
*/
        return 0;
    }

    private int haveFalseT() {
        for (int i = 0; i < closures.size(); i++) {
            if (!closures.get(i).isFlag()) {
                return i;
            }
        }
        return -1;
    }

    private void addDfaNode(List<String> set) {
        int i = 0;
        for (i = 0; i < dfaNodes.size(); i++) {
            if (dfaNodes.get(i).getName().equals(set)) {
                break;
            }
        }
        if (i == dfaNodes.size()) {
            DfaNode dfaNode = new DfaNode(set, String.valueOf(lastNode++));
            dfaNode.setFinalState();
            dfaNodes.add(dfaNode);
        }
    }

    private void addDfa(List<String> set, String w, List<String> next) {
        int i = 0, j = 0, k = 0;
        for (i = 0; i < dfaNodes.size(); i++) {
            if (dfaNodes.get(i).getName().equals(next)) {
                k = Integer.valueOf(dfaNodes.get(i).getNode());
                break;
            }
        }
        if (i == dfaNodes.size()) {
            k = lastNode;
            addDfaNode(next);
        }
        for (i = 0; i < dfaNodes.size(); i++) {
            if (dfaNodes.get(i).getName().equals(set)) {
                if (dfaNodes.get(i).getW().equals(w) && !dfaNodes.get(i).getNext().contains(String.valueOf(k))) {
                    dfaNodes.get(i).setNext(String.valueOf(k));
                    break;
                } else if ("".equals(dfaNodes.get(i).getW())) {
                    dfaNodes.get(i).setNext(String.valueOf(k));
                    dfaNodes.get(i).setW(w);
                    break;
                }
            }
            if (dfaNodes.get(i).getName().equals(set)) {
                j = Integer.valueOf(dfaNodes.get(i).getNode());
            }
        }
        if (i == dfaNodes.size()) {
            DfaNode dfaNode = new DfaNode(set, String.valueOf(j));
            dfaNode.setFinalState();
            dfaNode.setW(w);
            dfaNode.setNext(String.valueOf(k));
            dfaNodes.add(dfaNode);
        }
    }

}
