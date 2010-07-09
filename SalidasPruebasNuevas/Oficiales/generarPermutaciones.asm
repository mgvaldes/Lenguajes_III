%include "asm_io.inc"

section .bss
   static resb 0

section .text

procmostrarArreglo:
enter 16, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label8
label7:
push rdi
mov rax, rbp
sub rax, -80
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label10
mov rdx, 9
cmp rcx, rdx
mov rdx, rcx
jge label10
imul rbx, rcx
sub rax, rbx
jmp label11
label10:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label11:
pop rdi
mov rdi, [rax]
call print_char
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label8:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -88
mov rbx, [rbx]
mov rcx, 1
sub rbx, rcx
cmp rax, rbx
jl label7
jmp label9
label9:
mov rax, rbp
sub rax, -88
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
jg label15
jmp label16
label15:
push rdi
mov rax, rbp
sub rax, -80
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label18
mov rdx, 9
cmp rcx, rdx
mov rdx, rcx
jge label18
imul rbx, rcx
sub rax, rbx
jmp label19
label18:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label19:
pop rdi
mov rdi, [rax]
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label17
label16:
label17:
label0:
leave
ret

procswap:
enter 16, 0
mov rax, rbp
sub rax, -104
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, -24
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label24
mov rdx, 9
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
mov rbx, rbp
sub rbx, 8
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, -104
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, -16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label29
mov rdx, 9
cmp rcx, rdx
mov rdx, rcx
jge label29
imul rbx, rcx
sub rax, rbx
jmp label30
label29:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label30:
pop rdi
mov rbx, rbp
sub rbx, -104
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, -24
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label31
mov rdi, 9
cmp rdx, rdi
mov rdi, rdx
jge label31
imul rcx, rdx
sub rbx, rcx
jmp label32
label31:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label32:
pop rdi
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, 8
mov rbx, rbp
sub rbx, -104
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, -16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label36
mov rdi, 9
cmp rdx, rdi
mov rdi, rdx
jge label36
imul rcx, rdx
sub rbx, rcx
jmp label37
label36:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label37:
pop rdi
mov rax, [rax]
mov [rbx], rax
label20:
leave
ret

procgenerarPermutacionesAux:
enter 16, 0
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
je label39
jmp label40
label39:
mov rax, rbp
sub rax, -96
push qword [rax]
mov rax, rbp
sub rax, -88
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
call procmostrarArreglo
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
jmp label41
label40:
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
jg label42
jmp label43
label42:
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 1
sub rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label51
label50:
mov rax, rbp
sub rax, -88
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
add rax,8
push rax
mov rax, rbp
sub rax, 8
push qword [rax]
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 1
sub rax, rbx
push rax
call procswap
pop rax
pop rax
pop rax
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
mov rax, rbp
sub rax, -96
push qword [rax]
mov rax, rbp
sub rax, -88
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 1
sub rax, rbx
push rax
call procgenerarPermutacionesAux
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
mov rax, rbp
sub rax, -88
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
add rax,8
push rax
mov rax, rbp
sub rax, 8
push qword [rax]
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 1
sub rax, rbx
push rax
call procswap
pop rax
pop rax
pop rax
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
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
sub rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label51:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
jge label50
jmp label52
label52:
jmp label41
label43:
label41:
label38:
leave
ret

procgenerarPermutaciones:
enter 8, 0
mov rax, rbp
sub rax, -88
push qword [rax]
mov rax, rbp
sub rax, -80
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
mov rax, rbp
sub rax, -88
push qword [rax]
call procgenerarPermutacionesAux
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
label56:
leave
ret

   global main
main:
enter 88, 0
mov rax, 9
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
mov rbx, rbp
sub rbx, 16
mov rcx, 97
mov [rbx - 0], rcx
mov rcx, 98
mov [rbx - 8], rcx
mov rcx, 99
mov [rbx - 16], rcx
mov rcx, 100
mov [rbx - 24], rcx
mov rcx, 101
mov [rbx - 32], rcx
mov rcx, 102
mov [rbx - 40], rcx
mov rcx, 103
mov [rbx - 48], rcx
mov rcx, 104
mov [rbx - 56], rcx
mov rcx, 105
mov [rbx - 64], rcx
mov rax, rbp
sub rax, 8
push qword [rax]
mov rax, rbp
sub rax, 16
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
call procgenerarPermutaciones
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
label57:
leave
ret
