import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%char
%line
%column

%{
  public int getLine(){
    return yyline+1;
  }
  public int getColumn(){
    return yycolumn+1;
  }
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
%}

%eofval{
    return symbol(Symbols.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier =  [a-zA-Z] [a-zA-Z0-9]*
Num = [0-9]+
NFloat = [0-9]+ "." [0-9]+

%state CHAR

%%

<YYINITIAL> {

  /* Palabras reservadas */

  "int"           { return symbol(Symbols.INT); }
  "float"         { return symbol(Symbols.FLOAT); }
  "bool"          { return symbol(Symbols.BOOL); }
  "char"          { return symbol(Symbols.CHAR); }
  "union"         { return symbol(Symbols.UNION); }
  "struct"        { return symbol(Symbols.STRUCT); }
  "break"         { return symbol(Symbols.BREAK); }
  "if"            { return symbol(Symbols.IF); }
  "elif"          { return symbol(Symbols.ELIF); }
  "else"          { return symbol(Symbols.ELSE); }
  "switch"        { return symbol(Symbols.SWITCH); }
  "case"          { return symbol(Symbols.CASE); }
  "default"       { return symbol(Symbols.DEFAULT); }
  "typedef"       { return symbol(Symbols.TYPEDEF); }
  "for"           { return symbol(Symbols.FOR); }
  "while"         { return symbol(Symbols.WHILE); }
  "const"         { return symbol(Symbols.CONST); }
  "true"          { return symbol(Symbols.TRUE); }
  "false"         { return symbol(Symbols.FALSE); }
  "void"          { return symbol(Symbols.VOID); }
  "return"        { return symbol(Symbols.RETURN); }
  "print"         { return symbol(Symbols.PRINT); }
  "read"          { return symbol(Symbols.READ); }
  "main"          { return symbol(Symbols.MAIN); }
  "hasactive"          { return symbol(Symbols.HASACTIVE); }

  {Identifier}    { return symbol(Symbols.IDENTIFIER, yytext()); }
  {Num}           { return symbol(Symbols.NUM, new Long(Long.parseLong(yytext()))); }
  {NFloat}         { return symbol(Symbols.NFLOAT, new Double(Double.parseDouble(yytext()))); }
  \'              { yybegin(CHAR); return symbol(Symbols.COMILLAS);}

  /* Operadores */

  "="                            { return symbol(Symbols.ASIG); }
  "=="                           { return symbol(Symbols.EQ); }
  "+"                            { return symbol(Symbols.PLUS); }
  "-"                            { return symbol(Symbols.MINUS); }
  "++"                           { return symbol(Symbols.PLUSPLUS); }
  "--"                           { return symbol(Symbols.MINUSMINUS); }
  "*"                            { return symbol(Symbols.MULT); }
  "/"                            { return symbol(Symbols.DIV); }
  "%"                            { return symbol(Symbols.MOD); }
  "!="                           { return symbol(Symbols.NOTEQ); }
  "<"                            { return symbol(Symbols.LESS); }
  ">"                            { return symbol(Symbols.MORE); }
  ">="                           { return symbol(Symbols.MOREEQ); }
  "<="                           { return symbol(Symbols.LESSEQ); }
  "||"                           { return symbol(Symbols.OR); }
  "&&"                           { return symbol(Symbols.AND); }
  "!"                            { return symbol(Symbols.NOT); }

  /* Símbolos */

  "."                            { return symbol(Symbols.DOT); }
  ":"                            { return symbol(Symbols.DOSPUNTOS); }
  ";"                            { return symbol(Symbols.SEQ); }
  ","                            { return symbol(Symbols.COMA); }
  "("                            { return symbol(Symbols.PARLEFT); }
  ")"                            { return symbol(Symbols.PARRIGHT); }
  "{"                            { return symbol(Symbols.BRACKETLEFT); }
  "}"                            { return symbol(Symbols.BRACKETRIGHT); }
  "["                            { return symbol(Symbols.CORLEFT); }
  "]"                            { return symbol(Symbols.CORRIGHT); }
  "&"				 { return symbol(Symbols.REF); }

  /* Cosas que se ignoran */

  {Comment}                      {/* ignore */}
  {WhiteSpace}                   {/* ignore */}

}

<CHAR>{

  \'                             { yybegin(YYINITIAL); return symbol(Symbols.COMILLAS); }

  {Num}                          { return symbol(Symbols.CHAR, yytext()); }

  [^\n\r\"\'\\]                  { return symbol(Symbols.CHAR, yytext()); }
  \\t                            { return symbol(Symbols.CHAR, "\t"); }
  \\n                            { return symbol(Symbols.CHAR, "\n"); }

  \\r                            { return symbol(Symbols.CHAR, "\r"); }
  \\\"                           { return symbol(Symbols.CHAR, "\""); }
  \\\'                           { return symbol(Symbols.CHAR, "\'"); }
  \\\\                           { return symbol(Symbols.CHAR, "\\"); }

}

/* Errores */
.|\n                             { throw new Error("Caracter inválido '"+
                                   yytext()+"' en la línea: "+(yyline+1)+" columna "+(yycolumn+1)+"."); }

