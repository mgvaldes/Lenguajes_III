%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 72, 0
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
mov rdi, 120
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
call read_float
pop rax
pop rdi
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
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
mov rdi, 108
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
mov rdi, 104
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 103
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 112
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 116
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 100
call print_char
pop rdi
push rdi
mov rdi, 97
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
call read_float
pop rax
pop rdi
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
mov rax, 1
neg rax
mov rbx, rbp
sub rbx, 24
push rax
fild qword [rsp]
fstp qword [rsp]
pop rax
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 32
push rax
fild qword [rsp]
fstp qword [rsp]
pop rax
mov [rbx], rax
jmp label13
label13:
mov rax, 0
mov rbx, rbp
sub rbx, 40
push rax
fild qword [rsp]
fstp qword [rsp]
pop rax
mov [rbx], rax
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
push rbx
fild qword [rsp]
fstp qword [rsp]
pop rbx
push rax
fld qword [rsp]
push rbx
fadd qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 48
mov [rbx], rax
jmp label23
label22:
mov rax, 1
jmp label24
label23:
mov rax, 0
label24:
mov rbx, rbp
sub rbx, 56
mov [rbx], rax
jmp label26
label25:
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, rbp
sub rbx, 48
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fadd qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, 2
push rbx
fild qword [rsp]
fstp qword [rsp]
pop rbx
push rax
fld qword [rsp]
push rbx
fdiv qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 64
mov [rbx], rax
mov rax, rbp
sub rax, 64
mov rax, [rax]
mov rbx, rbp
sub rbx, 64
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jl label31
jmp label32
label31:
mov rax, rbp
sub rax, 64
mov rax, [rax]
mov rbx, rbp
sub rbx, 64
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fsub qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
jmp label33
label32:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, 64
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 64
mov rcx, [rcx]
push rbx
fld qword [rsp]
push rcx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rcx
pop rbx
push rax
fld qword [rsp]
push rbx
fsub qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
label33:
mov rax, rbp
sub rax, 32
mov rax, [rax]
mov rbx, rbp
sub rbx, 16
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jge label40
jmp label41
label40:
mov rax, rbp
sub rax, 48
mov rbx, rbp
sub rbx, 40
mov rax, [rax]
mov [rbx], rax
jmp label46
label46:
mov rax, 1
jmp label48
label47:
mov rax, 0
label48:
mov rbx, rbp
sub rbx, 56
mov [rbx], rax
mov rax, rbp
sub rax, 64
mov rbx, rbp
sub rbx, 24
mov rax, [rax]
mov [rbx], rax
jmp label42
label41:
mov rax, rbp
sub rax, 64
mov rax, [rax]
mov rbx, rbp
sub rbx, 64
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jl label52
jmp label53
label52:
mov rax, rbp
sub rax, 64
mov rbx, rbp
sub rbx, 48
mov rax, [rax]
mov [rbx], rax
jmp label42
label53:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, 64
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 64
mov rcx, [rcx]
push rbx
fld qword [rsp]
push rcx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rcx
pop rbx
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jl label57
jmp label58
label57:
mov rax, rbp
sub rax, 64
mov rbx, rbp
sub rbx, 40
mov rax, [rax]
mov [rbx], rax
jmp label42
label58:
label42:
label26:
mov rax, rbp
sub rax, 40
mov rax, [rax]
mov rbx, rbp
sub rbx, 48
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jne label62
jmp label27
label62: 
mov rbx, rbp
sub rbx, 56
mov rbx, [rbx]
cmp rbx, 1
je label27
jmp label25
label27:
jmp label15
label14:
label15:
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fld qword [rsp]
fcomip st1
finit
pop rbx
pop rax
jl label63
jmp label64
label63:
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fsub qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
jmp label65
label64:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
mov rcx, rbp
sub rcx, 24
mov rcx, [rcx]
push rbx
fld qword [rsp]
push rcx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rcx
pop rbx
push rax
fld qword [rsp]
push rbx
fsub qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 32
mov [rbx], rax
label65:
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
mov rdi, 108
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
mov rdi, 114
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 122
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
mov rdi, 112
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
mov rdi, 120
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 109
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
mov rdi, 97
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
call print_float
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
mov rdi, 58
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
call print_float
pop rdi
push rdi
mov rdi, 46
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 83
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
mov rdi, 104
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
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 114
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
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 104
call print_char
pop rdi
push rdi
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 103
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 120
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 109
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
mov rdi, 112
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 116
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 100
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
sub rax, 16
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 46
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
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
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 114
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
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
mov rdi, 32
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
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 122
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
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, 24
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 40
mov [rbx], rax
push rdi
mov rax, rbp
sub rax, 40
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 121
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
mov rdi, 117
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
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 102
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 110
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
mov rdi, 111
call print_char
pop rdi
push rdi
mov rdi, 110
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
call print_float
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
sub rax, 32
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
