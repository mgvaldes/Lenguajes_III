%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 64, 0
push rdi
mov rdi, 86
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
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 114
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
mov rdi, 78
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
jmp label7
label7:
mov rax, 0
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
mov rax, 1
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
mov rax, 1
mov rbx, rbp
sub rbx, 48
mov [rbx], rax
mov rax, rbp
sub rax, 8
mov rbx, rbp
sub rbx, 40
mov rax, [rax]
mov [rbx], rax
jmp label29
label28:
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, 2
push rbx
push rdx
mov rax, rax
mov rbx, rbx
mov rdx, 0
idiv rbx 
mov rax, rdx 
pop rdx
pop rbx
mov rbx, 0
cmp rax, rbx
je label31
jmp label32
label31:
mov rax, rbp
sub rax, 24
mov rbx, rbp
sub rbx, 56
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
imul rax, rbx
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 32
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
mov rax, rbp
sub rax, 56
mov rax, [rax]
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
imul rax, rbx
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 56
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 32
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, 2
push rbx
push rdx
mov rax, rax
mov rbx, rbx
mov rdx, 0
idiv rbx 
mov rax, rax 
pop rdx
pop rbx
mov rbx, rbp
sub rbx, 40
mov [rbx], rax
jmp label33
label32:
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, 2
push rbx
push rdx
mov rax, rax
mov rbx, rbx
mov rdx, 0
idiv rbx 
mov rax, rdx 
pop rdx
pop rbx
mov rbx, 1
cmp rax, rbx
je label46
jmp label47
label46:
mov rax, rbp
sub rax, 16
mov rbx, rbp
sub rbx, 56
mov rax, [rax]
mov [rbx], rax
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, 16
mov rbx, [rbx]
imul rax, rbx
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 48
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
mov rax, rbp
sub rax, 32
mov rax, [rax]
mov rbx, rbp
sub rbx, 56
mov rbx, [rbx]
imul rax, rbx
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 48
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 32
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 48
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, 48
mov [rbx], rax
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, 1
sub rax, rbx
mov rbx, rbp
sub rbx, 40
mov [rbx], rax
jmp label33
label47:
label33:
label29:
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, 0
cmp rax, rbx
jne label28
jmp label30
label30:
jmp label9
label8:
label9:
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
mov rdi, 118
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
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 114
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
sub rax, 16
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
