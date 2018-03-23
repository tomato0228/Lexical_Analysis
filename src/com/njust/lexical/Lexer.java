package com.njust.lexical;

import com.njust.lexical.tokens.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 词法分析器
 *
 * @author tomato
 * @create 2018-03-22 下午8:28
 */
public class Lexer {
    /**
     * 记录行号
     */
    private static int line = 1;
    /**
     * 存放最新读入的字符
     */
    private char character = ' ';
    /**
     * 保留字
     */
    private Hashtable<String, KeyWord> keywords = new Hashtable<String, KeyWord>();
    /**
     * 读取文件变量
     */
    private BufferedReader reader = null;
    /**
     * token序列
     */
    private ArrayList<Token> tokens = new ArrayList<Token>();
    /**
     * 符号表
     */
    private ArrayList<Symbol> symtable = new ArrayList<Symbol>();
    /**
     * 保存当前是否读取到了文件的结尾
     */
    private Boolean isEnd = false;

    public Lexer(String address) {
        //初始化读取文件变量
        try {
            reader = new BufferedReader(new FileReader(new File(address)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*添加保留字*/
        this.reserve(KeyWord.AUTO);
        this.reserve(KeyWord.BREAK);
        this.reserve(KeyWord.CASE);
        this.reserve(KeyWord.CHAR);
        this.reserve(KeyWord.CONST);
        this.reserve(KeyWord.CONTINUE);
        this.reserve(KeyWord.DEFAULT);
        this.reserve(KeyWord.DO);
        this.reserve(KeyWord.DOUBLE);
        this.reserve(KeyWord.ENUM);
        this.reserve(KeyWord.EXTERN);
        this.reserve(KeyWord.FLOAT);
        this.reserve(KeyWord.FOR);
        this.reserve(KeyWord.GOTO);
        this.reserve(KeyWord.INT);
        this.reserve(KeyWord.LONG);
        this.reserve(KeyWord.REGISTER);
        this.reserve(KeyWord.RETURN);
        this.reserve(KeyWord.SHORT);
        this.reserve(KeyWord.SIGNED);
        this.reserve(KeyWord.SIZOEF);
        this.reserve(KeyWord.STATIC);
        this.reserve(KeyWord.STRUCT);
        this.reserve(KeyWord.SWITCH);
        this.reserve(KeyWord.TYPEDEF);
        this.reserve(KeyWord.UNION);
        this.reserve(KeyWord.UNSIGNED);
        this.reserve(KeyWord.VOID);
        this.reserve(KeyWord.VOLATILE);
        this.reserve(KeyWord.WHILE);
    }

    /**
     * 是否读取到文件的结尾
     *
     * @return 是或否
     */
    private Boolean getReaderState() {
        return this.isEnd;
    }

    /**
     * 打印tokens序列
     *
     * @throws IOException 文件操作异常
     */
    public void printToken() throws IOException {
        FileWriter writer = new FileWriter("/Users/tomato/Downloads/lexical_syntax_analysis-master/tokens序列.txt");
        System.out.println("词法分析结果如下: ");
        while (!getReaderState()) {
            Token tok = scan();
            String str = "line " + tok.line + "\t(" + tok.tag + "," + tok.pos + ")\t\t"
                    + tok.name + ": " + tok.toString() + "\r\n";
            writer.write(str);
            System.out.print(str);
        }
        writer.flush();
    }

    /**
     * 打印符号表
     *
     * @throws IOException 文件操作异常
     */
    public void printSymbolsTable() throws IOException {
        FileWriter writer = new FileWriter("/Users/tomato/Downloads/lexical_syntax_analysis-master/符号表.txt");
        System.out.print("\r\n\r\n符号表\r\n");
        System.out.print("编号\t行号\t名称\r\n");
        writer.write("符号表\r\n");
        writer.write("编号 " + "\t行号 " + "\t名称 \r\n");
        for (Symbol symbol : symtable) {
            String desc = symbol.pos + "\t" + symbol.line + "\t" + symbol.toString();
            System.out.print(desc + "\r\n");
            writer.write(desc + "\r\n");
        }
        writer.flush();
    }

    /**
     * 打印错误
     *
     * @param tok 错误信息
     * @throws IOException 文件操作异常
     */
    public void printError(Token tok) throws IOException {
        FileWriter writer = new FileWriter("/Users/tomato/Downloads/lexical_syntax_analysis-master/error.txt");
        System.out.print("\r\n\r\n错误词法如下：\r\n");
        writer.write("错误词法如下：\r\n");
        String str = "line " + tok.line + "\t(" + tok.tag + "," + tok.pos + ")\t\t"
                + tok.name + ": " + tok.toString() + "\r\n";
        writer.write(str);
    }

    /**
     * 添加保留字
     *
     * @param w 保留字
     */
    private void reserve(KeyWord w) {
        keywords.put(w.lexme, w);
    }

    /**
     * 按字符读
     *
     * @throws IOException IO异常
     */
    private void readch() throws IOException {
        character = (char) reader.read();
        if (character == '\t') {
            character = ' ';
        }
        if ((int) character == 0xffff) {
            this.isEnd = true;
        }
    }

    /**
     * 判断是否匹配
     *
     * @param ch 判断的字符
     * @return 是否匹配
     * @throws IOException IO异常
     */
    private Boolean readch(char ch) throws IOException {
        readch();
        if (this.character != ch) {
            return false;
        }
        this.character = ' ';
        return true;
    }

    /**
     * 数字的识别
     *
     * @return 是否是数字
     * @throws IOException IO异常
     */
    private Boolean isDigit() throws IOException {
        if (Character.isDigit(character)) {
            int value = 0;
            while (Character.isDigit(character)) {
                value = 10 * value + Character.digit(character, 10);
                readch();
            }
            Num n = new Num(value);
            n.line = line;
            tokens.add(n);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保留字、标识符的识别
     *
     * @return 是否识别成功
     * @throws IOException IO异常
     */
    private Boolean isLetter() throws IOException {
        if (Character.isLetter(character)) {
            StringBuilder sb = new StringBuilder();
            //首先得到整个的一个分割
            while (Character.isLetterOrDigit(character)) {
                sb.append(character);
                readch();
            }
            //判断是保留字还是标识符
            String s = sb.toString();
            KeyWord w = keywords.get(s);
            //如果是保留字的话，w不应该是空的
            if (w != null) {
                w.line = line;
                tokens.add(w);
            } else {
                //否则就是标识符，此处多出记录标识符编号的语句
                Symbol sy = new Symbol(s);
                //用于标记已存在标识符
                Symbol mark = sy;
                Boolean isRepeat = false;
                sy.line = line;
                for (Symbol i : symtable) {
                    if (sy.toString().equals(i.toString())) {
                        mark = i;
                        isRepeat = true;
                    }
                }
                if (!isRepeat) {
                    sy.pos = symtable.size() + 1;
                    symtable.add(sy);
                } else {
                    sy.pos = mark.pos;
                }
                tokens.add(sy);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 符号的识别
     *
     * @return 是否识别成功
     * @throws IOException IO异常
     */
    private Boolean isSign() throws IOException {
        switch (character) {
            case '#':
                readch();
                Delimiter.pou.line = line;
                tokens.add(Delimiter.pou);
                return true;
            case '\r':
                if (readch('\n')) {
                    readch();
                    LineEnd.lineEnd.line = line;
                    tokens.add(LineEnd.lineEnd);
                    line++;
                    return true;
                }
            case '\n':
                readch();
                LineEnd.lineEnd.line = line;
                tokens.add(LineEnd.lineEnd);
                line++;
                return true;
            case '(':
                readch();
                Delimiter.lpar.line = line;
                tokens.add(Delimiter.lpar);
                return true;
            case ')':
                readch();
                Delimiter.rpar.line = line;
                tokens.add(Delimiter.rpar);
                return true;
            case '{':
                readch();
                Delimiter.lbpar.line = line;
                tokens.add(Delimiter.lbpar);
                return true;
            case '}':
                readch();
                Delimiter.rbpar.line = line;
                tokens.add(Delimiter.rbpar);
                return true;
            case '[':
                readch();
                Delimiter.lbra.line = line;
                tokens.add(Delimiter.lbra);
                return true;
            case ']':
                readch();
                Delimiter.rbra.line = line;
                tokens.add(Delimiter.rbra);
                return true;
            case ';':
                readch();
                Delimiter.sem.line = line;
                tokens.add(Delimiter.sem);
                return true;
            case '\'':
                readch();
                Delimiter.sQuotes.line = line;
                tokens.add(Delimiter.sQuotes);
                return true;
            case '\"':
                readch();
                Delimiter.dQuotes.line = line;
                tokens.add(Delimiter.dQuotes);
                return true;
            case '+':
                if (readch('=')) {
                    readch();
                    CalcWord.addE.line = line;
                    tokens.add(CalcWord.addE);
                } else if (character == '+') {
                    readch();
                    CalcWord.dadd.line = line;
                    tokens.add(CalcWord.dadd);
                } else {
                    CalcWord.add.line = line;
                    tokens.add(CalcWord.add);
                }
                return true;
            case '-':
                if (readch('=')) {
                    readch();
                    CalcWord.subE.line = line;
                    tokens.add(CalcWord.subE);
                } else if (character == '-') {
                    readch();
                    CalcWord.dsub.line = line;
                    tokens.add(CalcWord.dsub);
                } else {
                    CalcWord.sub.line = line;
                    tokens.add(CalcWord.sub);
                }
                return true;
            case '*':
                if (readch('=')) {
                    readch();
                    CalcWord.mulE.line = line;
                    tokens.add(CalcWord.mulE);
                } else {
                    CalcWord.mul.line = line;
                    tokens.add(CalcWord.mul);
                }
                return true;
            case '/':
                if (readch('=')) {
                    readch();
                    CalcWord.divE.line = line;
                    tokens.add(CalcWord.divE);
                } else {
                    CalcWord.div.line = line;
                    tokens.add(CalcWord.div);
                }
                return true;
            case '%':
                readch();
                CalcWord.mod.line = line;
                tokens.add(CalcWord.mod);
                return true;
            case '~':
                readch();
                CalcWord.logic1.line = line;
                tokens.add(CalcWord.logic1);
                return true;
            case '^':
                readch();
                CalcWord.logic4.line = line;
                tokens.add(CalcWord.logic4);
                return true;
            case '&':
                if (readch('&')) {
                    readch();
                    CalcWord.logic5.line = line;
                    tokens.add(CalcWord.logic5);
                } else {
                    CalcWord.logic2.line = line;
                    tokens.add(CalcWord.logic2);
                }
                return true;
            case '|':
                if (readch('|')) {
                    readch();
                    CalcWord.logic6.line = line;
                    tokens.add(CalcWord.logic6);
                } else {
                    CalcWord.logic3.line = line;
                    tokens.add(CalcWord.logic3);
                }
                return true;
            case '=':
                if (readch('=')) {
                    readch();
                    CalcWord.equals.line = line;
                    tokens.add(CalcWord.equals);
                } else {
                    CalcWord.assign.line = line;
                    tokens.add(CalcWord.assign);
                }
                return true;
            case '>':
                if (readch('=')) {
                    readch();
                    CalcWord.ge.line = line;
                    tokens.add(CalcWord.ge);
                } else if (character == '>') {
                    readch();
                    CalcWord.rm.line = line;
                    tokens.add(CalcWord.rm);
                } else {
                    if ("include".equals(tokens.get(tokens.size() - 3).toString())) {
                        Delimiter.rjpar.line = line;
                        tokens.add(Delimiter.rjpar);
                    } else {
                        CalcWord.greater.line = line;
                        tokens.add(CalcWord.greater);
                    }
                }
                return true;
            case '<':
                if (readch('=')) {
                    readch();
                    CalcWord.le.line = line;
                    tokens.add(CalcWord.le);
                } else if (character == '<') {
                    readch();
                    CalcWord.lm.line = line;
                    tokens.add(CalcWord.lm);
                } else {
                    if ("include".equals(tokens.get(tokens.size() - 1).toString())) {
                        Delimiter.ljpar.line = line;
                        tokens.add(Delimiter.ljpar);
                    } else {
                        CalcWord.less.line = line;
                        tokens.add(CalcWord.less);
                    }
                }
                return true;
            case '!':
                if (readch('=')) {
                    readch();
                    CalcWord.ne.line = line;
                    tokens.add(CalcWord.ne);
                } else {
                    CalcWord.not.line = line;
                    tokens.add(CalcWord.not);
                }
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * 下面开始分割关键字，标识符等信息
     *
     * @return 识别源程序
     * @throws IOException IO异常
     */
    private Token scan() throws IOException {
        Token tok;
        while (character == ' ') {
            readch();
        }
        if (isDigit() || isSign() || isLetter()) {
            tok = tokens.get(tokens.size() - 1);
        } else {
            tok = new Token(character);
            printError(tok);
        }
        return tok;
    }

}
