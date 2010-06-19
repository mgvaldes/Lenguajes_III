#! /bin/bash

jflex Scanner.jflex

java -jar java-cup-11a.jar -parser Parser -symbols Symbols Parser.cup

javac -source 1.4 -classpath java-cup-11a.jar *.java

java -classpath .:java-cup-11a.jar CNC $1 NASM/$2.asm

cd NASM

nasm -f elf64 asm_io.asm

nasm -f elf64 $2.asm

gcc -o $2 asm_io.o $2.o -lc

cd ..

./NASM/$2

rm *.class Lexer.java Parser.java Symbols.java
