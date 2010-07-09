%include "asm_io.inc"

section .bss
   static resb 0

section .text

proccualquiercosa:
enter 8, 0
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
label0:
leave
ret

procotracosa:
enter 16, 0
mov rax, rbp
sub rax, -24
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
push rdi
mov rax, rbp
sub rax, -16
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
call proccualquiercosa
label1:
leave
ret

   global main
main:
enter 16, 0
mov rax, 3
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
mov rax, rbp
sub rax, 8
push qword [rax]
mov rax, 5
push rax
call procotracosa
pop rax
pop rax
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
label5:
leave
ret
