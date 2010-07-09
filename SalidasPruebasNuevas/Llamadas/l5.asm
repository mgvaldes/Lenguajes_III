%include "asm_io.inc"

section .bss
   static resb 0

section .text

procproc:
enter 8, 0
push rdi
mov rax, rbp
sub rax, -24
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 0x4010cccccccccccd
mov rbx, rbp
sub rbx, -24
mov [rbx], rax
push rdi
mov rax, rbp
sub rax, -24
mov rdi, [rax]
call print_float
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
mov rax, rbp
sub rax, 8
mov rbx, [rax]
push rbx
fild qword [rsp]
fstp qword [rsp]
pop rbx
push rbx
push rax
call procproc
pop rax
pop rbx
push rbx
fld qword [rsp]
fistp qword [rsp]
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
