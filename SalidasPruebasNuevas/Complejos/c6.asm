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
jmp label7
label7:
mov rax, 1
jmp label9
label8:
mov rax, 0
label9:
mov rbx, rbp
sub rbx, 8
mov rcx, 1
cmp [rbx], rcx
je label10
call print_error_discriminante
call print_nl
mov eax, 1
int 80h
label10:
sub rbx, 8
mov [rbx], rax
label0:
leave
ret
