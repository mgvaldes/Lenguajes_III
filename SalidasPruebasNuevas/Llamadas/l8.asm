%include "asm_io.inc"

section .bss
   static resb 0

section .text

procproc:
enter 8, 0
mov rbx, rbp
sub rbx, -72
mov rcx, 5
mov [rbx - 0], rcx
mov rcx, 4
mov [rbx - 8], rcx
mov rcx, 3
mov [rbx - 16], rcx
mov rcx, 2
mov [rbx - 24], rcx
mov rcx, 1
mov [rbx - 32], rcx
mov rdx, 40
sub rbx, rdx
mov rcx, 1
mov [rbx], rcx
sub rbx, 8
jmp label26
label25:
mov rcx, 1
jmp label27
label26:
mov rcx, 0
label27:
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rdx, 16
sub rbx, rdx
label0:
leave
ret

   global main
main:
enter 64, 0
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
mov rdx, 40
sub rbx, rdx
mov rcx, 1
mov [rbx], rcx
sub rbx, 8
jmp label53
label53:
mov rcx, 1
jmp label55
label54:
mov rcx, 0
label55:
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rdx, 16
sub rbx, rdx
mov rax, 1
push rax
mov rax, 2
push rax
mov rax, rbp
sub rax, 8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
mov rbx, [rax]
push qword [rax]
sub rax,8
cmp rbx, 0
jne label57
push qword [rax]
sub rax,8
jmp label56
label57:
cmp rbx, 1
jne label58
push qword [rax]
sub rax,8
jmp label56
label58:
label56:
add rax,8
push rax
call procproc
pop rax
pop rbx
mov [rax], rbx
add rax,8
pop rbx
mov [rax], rbx
mov rbx, [rax]
sub rax,8
cmp rbx, 0
jne label60
sub rax,8
jmp label59
label60:
cmp rbx, 1
jne label61
sub rax,8
jmp label59
label61:
label59:
add rax, 16+8
pop rbx
mov [rax], rbx
add rax,8
pop rbx
mov [rax], rbx
add rax,8
pop rbx
mov [rax], rbx
add rax,8
pop rbx
mov [rax], rbx
add rax,8
pop rbx
mov [rax], rbx
add rax,8
pop rax
pop rax
push rdi
mov rax, rbp
sub rax, 8
sub rax, 0
push rdi
mov rbx, 8
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label62
mov rdx, 5
cmp rcx, rdx
mov rdx, rcx
jge label62
imul rbx, rcx
sub rax, rbx
jmp label63
label62:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label63:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
label28:
leave
ret
