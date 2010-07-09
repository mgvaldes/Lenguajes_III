%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 48, 0
push rdi
mov rdi, 78
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
mov rdi, 113
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 108
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
mov rdi, 97
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
mov rdi, 102
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 98
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 110
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
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 10
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
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
mov rax, 1
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
jmp label17
label16:
mov rax, rbp
sub rax, 24
mov rbx, rbp
sub rbx, 40
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, 32
mov rbx, rbp
sub rbx, 24
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
add rax, rbx
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label17:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
cmp rax, rbx
jl label16
jmp label18
label18:
push rdi
mov rdi, 69
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
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 98
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 110
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
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rax, rbp
sub rax, 8
mov rdi, [rax]
call print_int
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
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 24
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
