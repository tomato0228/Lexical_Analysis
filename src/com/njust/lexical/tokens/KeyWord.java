package com.njust.lexical.tokens;

/**
 * 保留字
 *
 * @author tomato
 * @create 2018-03-21 下午5:23
 */
public class KeyWord extends Token {
    public static final KeyWord
            AUTO = new KeyWord("auto", Tag.AUTO),
            BREAK = new KeyWord("break", Tag.BREAK),
            CASE = new KeyWord("case", Tag.CASE),
            CHAR = new KeyWord("char", Tag.CHAR),
            CONST = new KeyWord("const", Tag.CONST),
            CONTINUE = new KeyWord("continue", Tag.CONTINUE),
            DEFAULT = new KeyWord("default", Tag.DEFAULT),
            DO = new KeyWord("do", Tag.DO),
            DOUBLE = new KeyWord("double", Tag.DOUBLE),
            ELSE = new KeyWord("else", Tag.ELSE),
            ENUM = new KeyWord("enum", Tag.ENUM),
            EXTERN = new KeyWord("extern", Tag.EXTERN),
            FLOAT = new KeyWord("float", Tag.FLOAT),
            FOR = new KeyWord("for", Tag.FOR),
            GOTO = new KeyWord("goto", Tag.GOTO),
            IF = new KeyWord("if", Tag.IF),
            INT = new KeyWord("int", Tag.INT),
            LONG = new KeyWord("long", Tag.LONG),
            REGISTER = new KeyWord("register", Tag.REGISTER),
            RETURN = new KeyWord("return", Tag.RETURN),
            SHORT = new KeyWord("short", Tag.SHORT),
            SIGNED = new KeyWord("signed", Tag.SIGNED),
            SIZOEF = new KeyWord("sizoef", Tag.SIZOEF),
            STATIC = new KeyWord("static", Tag.STATIC),
            STRUCT = new KeyWord("struct", Tag.STRUCT),
            SWITCH = new KeyWord("switch", Tag.SWITCH),
            TYPEDEF = new KeyWord("typedef", Tag.TYPEDEF),
            UNION = new KeyWord("union", Tag.UNION),
            UNSIGNED = new KeyWord("unsigned", Tag.UNSIGNED),
            VOID = new KeyWord("void", Tag.VOID),
            VOLATILE = new KeyWord("volatile", Tag.VOLATILE),
            WHILE = new KeyWord("while", Tag.WHILE);
    public String lexme = "";

    public KeyWord(String s, int t) {
        super(t);
        this.lexme = s;
        this.name = "保留字";
    }

    @Override
    public String toString() {
        return this.lexme;
    }
}