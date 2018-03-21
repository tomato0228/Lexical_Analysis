package com.njust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入正规文法创建NFA
 *
 * @author tomato
 * @create 2018-03-21 下午8:26
 */
public class Nfa {
    private List<NfaNode> nfaNodes;

    Nfa() {
        nfaNodes = new ArrayList<NfaNode>();
    }

    public List<NfaNode> getNfaNodes() {
        return nfaNodes;
    }

    public void setNfaNodes(List<NfaNode> nfaNodes) {
        this.nfaNodes = nfaNodes;
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

        System.out.println(nfaNodes.size());
        for (i = 0; i < nfaNodes.size(); i++) {
            System.out.print(nfaNodes.get(i).getName() + "\t");
            System.out.print(nfaNodes.get(i).getW() + "\t");
            System.out.print(nfaNodes.get(i).getNext());
            System.out.println();
        }
    }
}
