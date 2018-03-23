package com.njust.lexical.tokens;

/**
 * 行尾符
 *
 * @author tomato
 * @create 2018-03-21 下午5:24
 */
public class LineEnd extends Token {
    public static final LineEnd lineEnd = new LineEnd("\r\n");
    public String lexme = "";

    public LineEnd(String s) {
        super(Tag.LINE_END);
        this.lexme = s;
        this.name = "行尾符";
    }

    @Override
    public String toString() {
        return this.lexme;
    }
}