package com.njust;

import com.njust.lexical.Lexer;
import com.njust.lexical.Nfa;

/**
 * @author tomato
 * @create 2018-03-21 下午7:43
 */
public class Main {
    /**
     * NFA终态
     */
    public static final String TERMINATOR = "Y";
    public static final String EBCL = "@";

    public static void main(String[] args) {
        try {
            Nfa nfa = new Nfa();
            String address = "/Users/tomato/Downloads/lexical_syntax_analysis-master/词法分析_文法.txt";
            if (nfa.inputNfa(address) == -1){
                System.out.println(ErrorMessage.CREATNFA);
            }
            if (nfa.nfaToDfa() == -1){
                System.out.println(ErrorMessage.NFATODFA);
            }
            address = "/Users/tomato/Downloads/lexical_syntax_analysis-master/词法分析_源程序的副本.txt";
            Lexer lexer = new Lexer(address);
            lexer.printToken();
            lexer.printSymbolsTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
