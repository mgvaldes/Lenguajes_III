%include "asm_io.inc"

section .bss
   static resb 0

section .text

procproc:
enter 8, 0
push rdi
mov rax, rbp
sub rax, -64
sub rax, 0
push rdi
mov rbx, 8
mov rcx, 2
cmp rcx, 0
mov rdx, rcx
jl label1
mov rdx, 5
cmp rcx, rdx
mov rdx, rcx
jge label1
imul rbx, rcx
sub rax, rbx
jmp label2
label1:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label2:
pop rdi
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
enter 8, 0
mov rax, 1
push rax
mov rax, 2
push rax
mov rax, 1
push rax
mov rax, 2
push rax
mov rax, 3
push rax
mov rax, 4
push rax
mov rax, 5
push rax
push 1
jmp label4
label4:
push 1
jmp label6
label5:
push 0
label6:
call procproc
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
label3:
leave
ret
