package com.njust.lexical.tokens;

/**
 * 常数
 *
 * @author tomato
 * @create 2018-03-21 下午5:24
 */
public class Num extends Token {
    public final int value;

    public Num(int v) {
        super(Tag.CONSTANT);
        this.value = v;
        this.name = "常数";
    }

    @Override
    public String toString() {
        return "" + value;
    }
}