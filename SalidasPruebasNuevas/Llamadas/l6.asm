%include "asm_io.inc"

section .bss
   static resb 0

section .text

procproc:
enter 16, 0
mov rax, rbp
sub rax, -24
push rdi
mov rbx, 8
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label4
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label4
imul rbx, rcx
sub rax, rbx
jmp label5
label4:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label5:
pop rdi
mov rbx, rbp
sub rbx, 8
mov rax, [rax]
mov [rbx], rax
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

   global main
main:
enter 16, 0
mov rax, 3
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
mov rax, 1
push rax
mov rax, 2
push rax
call procproc
pop rax
pop rax
label6:
leave
ret
