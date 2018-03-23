package com.njust.lexical.tokens;

/**
 * 符号标号
 *
 * @author tomato
 * @create 2018-03-21 下午5:22
 */
public class Tag {
    public final static int
            //保留字
            AUTO = 1,
            BREAK = 2,
            CASE = 3,
            CHAR = 4,
            CONST = 5,
            CONTINUE = 6,
            DEFAULT = 7,
            DO = 8,
            DOUBLE = 9,
            ELSE = 10,
            ENUM = 11,
            EXTERN = 12,
            FLOAT = 13,
            FOR = 14,
            GOTO = 15,
            IF = 16,
            INT = 17,
            LONG = 18,
            REGISTER = 19,
            RETURN = 20,
            SHORT = 21,
            SIGNED = 22,
            SIZOEF = 23,
            STATIC = 24,
            STRUCT = 25,
            SWITCH = 26,
            TYPEDEF = 27,
            UNION = 28,
            UNSIGNED = 29,
            VOID = 30,
            VOLATILE = 31,
            WHILE = 32,
    //标识符
    SYMBOL = 33,
    //常数
    CONSTANT = 34,
    //运算符 "+"
    ADD = 35,
    //运算符 "++"
    DADD = 36,
    //运算符 "-"
    SUB = 37,
    //运算符 "--"
    DSUB = 38,
    //运算符 "*"
    MUL = 39,
    //运算符 "/"
    DIV = 40,
    //运算符 "+"
    ADDE = 41,
    //运算符 "-"
    SUBE = 42,
    //运算符 "*"
    MULE = 43,
    //运算符 "/"
    DIVE = 44,
    //运算符 "<="
    LE = 45,
    //运算符 ">="
    GE = 46,
    //运算符 "<<"
    LM = 47,
    //运算符 ">>"
    RM = 48,
    //运算符 "!="
    NE = 49,
    //运算符 "="
    ASSIGN = 50,
    //运算符 "=="
    EQUALS = 51,
    //运算符 "!"
    NOT = 52,
    //运算符 "%"
    MOD = 53,
    //运算符 "~"
    LOGIC1 = 54,
    //运算符 "&"
    LOGIC2 = 55,
    //运算符 "|"
    LOGIC3 = 56,
    //运算符 "^"
    LOGIC4 = 57,
    //运算符 "&&"
    LOGIC5 = 58,
    //运算符 "||"
    LOGIC6 = 59,
    //运算符 "<"
    GREATER = 60,
    //运算符 ">"
    LESS = 61,
    //界符 "{"
    LBPAR = 62,
    //界符 "}"
    RBPAR = 63,
    //界符 "<"
    LJPAR = 64,
    //界符 ">"
    RJPAR = 65,
    //界符 "("
    LPAR = 66,
    //界符 ")"
    RPAR = 67,
    //界符 ";"
    SEM = 68,
    //界符 "#"
    POU = 69,
    //界符 "'"
    SQUOTES = 70,
    //界符 """
    DQUOTES = 71,
    //界符 "["
    LBRA = 72,
    //界符 "]"
    RBRA = 73,
    //行尾符
    LINE_END = 74;
}