package com.njust.lexical.tokens;

/**
 * 标识符
 *
 * @author tomato
 * @create 2018-03-21 下午5:23
 */
public class Symbol extends Token {
    public String lexme = "";

    public Symbol(String s) {
        super(Tag.SYMBOL);
        this.lexme = s;
        this.name = "标识符";
    }

    @Override
    public String toString() {
        return this.lexme;
    }

}