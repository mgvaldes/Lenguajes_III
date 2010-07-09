%include "asm_io.inc"

section .bss
   static resb 0

section .text

procotracosa:
enter 8, 0
push rdi
mov rax, rbp
sub rax, -24
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 2
mov rbx, rbp
sub rbx, -24
mov [rbx], rax
push rdi
mov rax, rbp
sub rax, -24
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
mov rax, rbp
sub rax, 8
push qword [rax]
push rax
call procotracosa
pop rax
pop rbx
mov [rax], rbx
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
label4:
leave
ret
