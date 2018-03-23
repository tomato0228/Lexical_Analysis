package com.njust.lexical.tokens;

/**
 * 界符
 *
 * @author tomato
 * @create 2018-03-21 下午5:24
 */
public class Delimiter extends Token {
    public static final Delimiter
            lpar = new Delimiter("(", Tag.LPAR),
            rpar = new Delimiter(")", Tag.RPAR),
            sem = new Delimiter(";", Tag.SEM),
            pou = new Delimiter("#", Tag.POU),
            lbpar = new Delimiter("{", Tag.LBPAR),
            rbpar = new Delimiter("}", Tag.RBPAR),
            ljpar = new Delimiter("<", Tag.LJPAR),
            rjpar = new Delimiter(">", Tag.RJPAR),
            lbra = new Delimiter("[", Tag.LBRA),
            rbra = new Delimiter("]", Tag.RBRA),
            sQuotes = new Delimiter("\'", Tag.SQUOTES),
            dQuotes = new Delimiter("\"", Tag.DQUOTES);
    public String lexme = "";

    public Delimiter(String s, int t) {
        super(t);
        this.lexme = s;
        this.name = "界符";
    }

    @Override
    public String toString() {
        return this.lexme;
    }
}
