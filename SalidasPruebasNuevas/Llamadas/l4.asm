%include "asm_io.inc"

section .bss
   static resb 0

section .text

procproc:
enter 8, 0
push rdi
mov rax, rbp
sub rax, -16
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
call procproc
pop rax
label1:
leave
ret
