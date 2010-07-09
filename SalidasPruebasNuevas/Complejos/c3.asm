%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 40, 0
mov rax, 2
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 8
mov rdx, 5
cmp rdx, 0
mov rdi, rdx
jl label7
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label7
imul rcx, rdx
sub rbx, rcx
jmp label8
label7:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label8:
pop rdi
mov [rbx], rax
label0:
leave
ret
