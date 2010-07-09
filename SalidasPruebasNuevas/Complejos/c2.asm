%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 40, 0
mov rax, 3
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 8
mov rdx, 1
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
mov rax, 4
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 8
mov rdx, 2
cmp rdx, 0
mov rdi, rdx
jl label12
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label12
imul rcx, rdx
sub rbx, rcx
jmp label13
label12:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label13:
pop rdi
mov [rbx], rax
mov rax, 1
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 8
mov rdx, 3
cmp rdx, 0
mov rdi, rdx
jl label17
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label17
imul rcx, rdx
sub rbx, rcx
jmp label18
label17:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label18:
pop rdi
mov [rbx], rax
mov rax, 2
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 8
mov rdx, 0
cmp rdx, 0
mov rdi, rdx
jl label22
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label22
imul rcx, rdx
sub rbx, rcx
jmp label23
label22:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label23:
pop rdi
mov [rbx], rax
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 8
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label24
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label24
imul rbx, rcx
sub rax, rbx
jmp label25
label24:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label25:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 8
mov rcx, 1
cmp rcx, 0
mov rdx, rcx
jl label26
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label26
imul rbx, rcx
sub rax, rbx
jmp label27
label26:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label27:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 8
mov rcx, 2
cmp rcx, 0
mov rdx, rcx
jl label28
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label28
imul rbx, rcx
sub rax, rbx
jmp label29
label28:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label29:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 8
mov rcx, 3
cmp rcx, 0
mov rdx, rcx
jl label30
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label30
imul rbx, rcx
sub rax, rbx
jmp label31
label30:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label31:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
label0:
leave
ret
