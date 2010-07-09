%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 328, 0
mov rax, 3
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 80
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
push rdi
mov rcx, 40
mov rdx, 0
cmp rdx, 0
mov rdi, rdx
jl label9
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label9
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, 3
cmp rdx, 0
mov rdi, rdx
jl label11
mov rdi, 5
cmp rdx, rdi
mov rdi, rdx
jge label11
imul rcx, rdx
sub rbx, rcx
jmp label12
label11:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label12:
pop rdi
jmp label10
label9:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label10:
pop rdi
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
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 80
mov rcx, 1
cmp rcx, 0
mov rdx, rcx
jl label13
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label13
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 40
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label15
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label15
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, 3
cmp rcx, 0
mov rdx, rcx
jl label17
mov rdx, 5
cmp rcx, rdx
mov rdx, rcx
jge label17
imul rbx, rcx
sub rax, rbx
jmp label18
label17:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label18:
pop rdi
jmp label16
label15:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label16:
pop rdi
jmp label14
label13:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label14:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 80
mov rcx, 1
cmp rcx, 0
mov rdx, rcx
jl label22
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label22
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 40
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label24
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label24
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, 3
cmp rcx, 0
mov rdx, rcx
jl label26
mov rdx, 5
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
jmp label23
label22:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label23:
pop rdi
mov rax, [rax]
mov rbx, 5
add rax, rbx
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 80
mov rdx, 0
cmp rdx, 0
mov rdi, rdx
jl label28
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label28
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 40
mov rdx, 1
cmp rdx, 0
mov rdi, rdx
jl label30
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label30
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, 4
cmp rdx, 0
mov rdi, rdx
jl label32
mov rdi, 5
cmp rdx, rdi
mov rdi, rdx
jge label32
imul rcx, rdx
sub rbx, rcx
jmp label33
label32:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label33:
pop rdi
jmp label31
label30:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label31:
pop rdi
jmp label29
label28:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label29:
pop rdi
mov [rbx], rax
push rdi
mov rax, rbp
sub rax, 8
push rdi
mov rbx, 80
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label34
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label34
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 40
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
mov rcx, 4
cmp rcx, 0
mov rdx, rcx
jl label38
mov rdx, 5
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
jmp label35
label34:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label35:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 3
mov rbx, rbp
sub rbx, 8
push rdi
mov rcx, 80
mov rdx, 1
cmp rdx, 0
mov rdi, rdx
jl label43
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label43
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 40
mov rdx, 10
cmp rdx, 0
mov rdi, rdx
jl label45
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label45
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, 1
cmp rdx, 0
mov rdi, rdx
jl label47
mov rdi, 5
cmp rdx, rdi
mov rdi, rdx
jge label47
imul rcx, rdx
sub rbx, rcx
jmp label48
label47:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label48:
pop rdi
jmp label46
label45:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label46:
pop rdi
jmp label44
label43:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label44:
pop rdi
mov [rbx], rax
label0:
leave
ret
