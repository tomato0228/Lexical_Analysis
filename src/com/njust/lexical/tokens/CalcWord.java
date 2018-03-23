package com.njust.lexical.tokens;

/**
 * 运算符
 *
 * @author tomato
 * @create 2018-03-21 下午5:23
 */
public class CalcWord extends Token {
    public static final CalcWord
            add = new CalcWord("+", Tag.ADD),
            dadd = new CalcWord("++", Tag.DADD),
            sub = new CalcWord("-", Tag.SUB),
            dsub = new CalcWord("--", Tag.DSUB),
            mul = new CalcWord("*", Tag.MUL),
            div = new CalcWord("/", Tag.DIV),
            addE = new CalcWord("+=", Tag.ADDE),
            subE = new CalcWord("-=", Tag.SUBE),
            mulE = new CalcWord("*=", Tag.MULE),
            divE = new CalcWord("/=", Tag.DIVE),
            not = new CalcWord("!", Tag.NOT),
            mod = new CalcWord("%", Tag.MOD),
            logic1 = new CalcWord("~", Tag.LOGIC1),
            logic2 = new CalcWord("&", Tag.LOGIC2),
            logic3 = new CalcWord("|", Tag.LOGIC3),
            logic4 = new CalcWord("^", Tag.LOGIC4),
            logic5 = new CalcWord("&&", Tag.LOGIC5),
            logic6 = new CalcWord("||", Tag.LOGIC6),
            le = new CalcWord("<=", Tag.LE),
            ge = new CalcWord(">=", Tag.GE),
            lm = new CalcWord("<<", Tag.LM),
            rm = new CalcWord(">>", Tag.RM),
            greater = new CalcWord(">", Tag.GREATER),
            less = new CalcWord("<", Tag.LESS),
            ne = new CalcWord("!=", Tag.NE),
            assign = new CalcWord("=", Tag.ASSIGN),
            equals = new CalcWord("==", Tag.EQUALS);

    public String lexme = "";

    public CalcWord(String s, int t) {
        super(t);
        this.lexme = s;
        this.name = "运算符";
    }

    @Override
    public String toString() {
        return this.lexme;
    }
}