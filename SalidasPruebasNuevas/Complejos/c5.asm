%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 80, 0
mov rbx, rbp
sub rbx, 8
mov rcx, 1
mov [rbx - 0], rcx
mov rcx, 2
mov [rbx - 8], rcx
mov rcx, 3
mov [rbx - 16], rcx
mov rcx, 4
mov [rbx - 24], rcx
mov rcx, 5
mov [rbx - 32], rcx
mov rcx, 6
mov rdx, 10
add rcx, rdx
mov [rbx - 40], rcx
mov rdx, 48
sub rbx, rdx
mov rcx, 1
mov [rbx], rcx
sub rbx, 8
jmp label31
label31: 
jmp label32
label32: 
jmp label28
label28:
mov rcx, 1
jmp label30
label29:
mov rcx, 0
label30:
mov [rbx], rcx
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rdx, 16
sub rbx, rdx
mov rcx, 0x3f947ae147ae147b
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
push rdi
mov rax, rbp
sub rax, 8
sub rax, 0
push rdi
mov rbx, 24
mov rcx, 1
cmp rcx, 0
mov rdx, rcx
jl label36
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label36
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, 2
cmp rcx, 0
mov rdx, rcx
jl label38
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label38
imul rbx, rcx
sub rax, rbx
jmp label39
label38:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label39:
pop rdi
jmp label37
label36:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label37:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
sub rax, 48
mov rbx, 1
cmp [rax], rbx
je label40
call print_error_discriminante
call print_nl
mov eax, 1
int 80h
label40:
sub rax, 8
mov rdi, [rax]
call print_bool
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
sub rax, 64
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
