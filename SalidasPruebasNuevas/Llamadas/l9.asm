%include "asm_io.inc"

section .bss
   static resb 0

section .text

procfactorial:
enter 16, 0
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
jl label1
jmp label2
label1:
push rdi
mov rdi, 69
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 1
neg rax
mov rbx, rbp
add rbx, 24
mov [rbx], rax
jmp label3
label2:
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
je label7
jmp label9
label9: 
mov rbx, rbp
sub rbx, -16
mov rbx, [rbx]
mov rcx, 1
cmp rbx, rcx
je label7
jmp label8
label7:
mov rax, 1
mov rbx, rbp
add rbx, 24
mov [rbx], rax
jmp label3
label8:
push 0
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, 1
sub rax, rbx
push rax
call procfactorial
pop rax
mov rbx, rbp
sub rbx, 8
pop rax
mov [rbx], rax
mov rax, rbp
sub rax, -16
mov rax, [rax]
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
imul rax, rbx
mov rbx, rbp
add rbx, 24
mov [rbx], rax
label3:
label0:
leave
ret

   global main
main:
enter 24, 0
push rdi
mov rdi, 73
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 103
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 117
call print_char
pop rdi
push rdi
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 100
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 102
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 116
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 117
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 58
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
push rax
mov rdi, rsp
call read_int
pop rax
pop rdi
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
push 0
mov rax, rbp
sub rax, 8
push qword [rax]
call procfactorial
pop rax
mov rbx, rbp
sub rbx, 16
pop rax
mov [rbx], rax
push rdi
mov rdi, 82
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 117
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 116
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 100
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 58
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 16
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
label19:
leave
ret
