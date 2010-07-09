%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 24, 0
mov rbx, rbp
sub rbx, 8
mov rcx, 0
mov [rbx], rcx
sub rbx, 8
mov rcx, 3
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rax, rbp
sub rax, 8
mov rax, [rax]
cmp rax, 0
je label7
jmp label8
label7:
push rdi
mov rdi, 1
call print_bool
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label9
label8:
label9:
label0:
leave
ret
