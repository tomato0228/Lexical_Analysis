package com.njust.lexical.tokens;

/**
 * @author tomato
 * @create 2018-03-21 下午5:22
 */
public class Token {
    public final int tag;
    public int line = 1;
    public String name = "";
    public int pos = 0;

    public Token(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "" + (char) tag;
    }

}