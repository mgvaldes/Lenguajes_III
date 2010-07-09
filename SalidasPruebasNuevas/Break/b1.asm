%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 16, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label8
label7:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 2
cmp rax, rbx
jg label10
jmp label11
label10:
jmp label9
jmp label12
label11:
label12:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label8:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 5
cmp rax, rbx
jl label7
jmp label9
label9:
push rdi
mov rax, rbp
sub rax, 8
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
label0:
leave
ret
