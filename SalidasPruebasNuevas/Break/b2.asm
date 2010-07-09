%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 24, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label8
label7:
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
jmp label11
label10:
push rdi
mov rax, rbp
sub rax, 8
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 5
cmp rax, rbx
je label13
jmp label14
label13:
jmp label12
jmp label15
label14:
label15:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label11:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 10
cmp rax, rbx
jl label10
jmp label12
label12:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 5
cmp rax, rbx
je label19
jmp label20
label19:
push rdi
mov rdi, 1
call print_bool
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label9
jmp label21
label20:
label21:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label8:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 5
cmp rax, rbx
jl label7
jmp label9
label9:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 5
cmp rax, rbx
je label25
jmp label26
label25:
push rdi
mov rdi, 1
call print_bool
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label27
label26:
label27:
label0:
leave
ret
