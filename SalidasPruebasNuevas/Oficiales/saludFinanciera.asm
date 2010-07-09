%include "asm_io.inc"

section .bss
   static resb 0

section .text

   global main
main:
enter 168, 0
mov rax, 12
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
push rdi
mov rdi, 67
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
mov rdi, 105
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
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 108
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
call read_float
pop rax
pop rdi
mov rbx, rbp
sub rbx, 112
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 128
mov [rbx], rax
jmp label17
label16:
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
mov rdi, 118
call print_char
pop rdi
push rdi
mov rdi, 91
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 128
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 93
call print_char
pop rdi
push rdi
mov rdi, 32
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
call read_float
pop rax
pop rdi
mov rbx, rbp
sub rbx, 16
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 128
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label22
mov rdi, 12
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
mov rax, rbp
sub rax, 128
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 128
mov [rbx], rax
label17:
mov rax, rbp
sub rax, 128
mov rax, [rax]
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
cmp rax, rbx
jl label16
jmp label18
label18:
push rdi
mov rdi, 76
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
mov rdi, 109
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
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
mov rdi, 116
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
mov rdi, 117
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
mov rdi, 99
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
mov rdi, 105
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
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 108
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
sub rax, 112
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label27
label27:
jmp label39
label39:
mov rax, 1
jmp label41
label40:
mov rax, 0
label41:
mov rbx, rbp
sub rbx, 120
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 128
push rax
fild qword [rsp]
fstp qword [rsp]
pop rax
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 136
mov [rbx], rax
jmp label49
label48:
mov rax, rbp
sub rax, 120
mov rax, [rax]
mov rbx, rbp
sub rbx, 16
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 136
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label55
mov rdi, 12
cmp rdx, rdi
mov rdi, rdx
jge label55
imul rcx, rdx
sub rbx, rcx
jmp label56
label55:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label56:
pop rdi
mov rbx, [rbx]
mov rcx, 0x3fb999999999999a
push rcx
fld qword [rsp]
fchs
fstp qword [rsp]
pop rcx
mov rdx, rbp
sub rdx, 112
mov rdx, [rdx]
push rcx
fld qword [rsp]
push rdx
fmul qword [rsp]
fstp qword [rsp + 8]
pop rdx
pop rcx
push rbx
fld qword [rsp]
push rcx
fld qword [rsp]
fcomip st1
finit
pop rcx
pop rbx
jle label54
jmp label52
label54: 
cmp rax, 1
je label51
jmp label52
label51:
mov rax, 1
jmp label53
label52:
mov rax, 0
label53:
mov rbx, rbp
sub rbx, 120
mov [rbx], rax
mov rax, rbp
sub rax, 128
mov rax, [rax]
mov rbx, rbp
sub rbx, 16
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 136
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label60
mov rdi, 12
cmp rdx, rdi
mov rdi, rdx
jge label60
imul rcx, rdx
sub rbx, rcx
jmp label61
label60:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label61:
pop rdi
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fadd qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 128
mov [rbx], rax
mov rax, rbp
sub rax, 136
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 136
mov [rbx], rax
label49:
mov rax, rbp
sub rax, 136
mov rax, [rax]
mov rbx, rbp
sub rbx, 8
mov rbx, [rbx]
cmp rax, rbx
jl label48
jmp label50
label50:
mov rax, rbp
sub rax, 128
mov rax, [rax]
mov rbx, 0x3fa999999999999a
mov rcx, rbp
sub rcx, 112
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
jl label68
jmp label66
label68: 
mov rbx, rbp
sub rbx, 120
mov rbx, [rbx]
cmp rbx, 1
je label65
jmp label66
label65:
mov rax, 1
jmp label67
label66:
mov rax, 0
label67:
mov rbx, rbp
sub rbx, 120
mov [rbx], rax
mov rax, rbp
sub rax, 112
mov rax, [rax]
mov rbx, rbp
sub rbx, 128
mov rbx, [rbx]
push rax
fld qword [rsp]
push rbx
fadd qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, 112
mov [rbx], rax
push rdi
mov rdi, 83
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
mov rdi, 98
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
mov rdi, 97
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
mov rdi, 101
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
mov rdi, 99
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
mov rdi, 102
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
sub rax, 128
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 46
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, rbp
sub rax, 128
mov rax, [rax]
mov rbx, 0x3fa999999999999a
mov rcx, rbp
sub rcx, 112
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
jl label72
jmp label73
label72:
mov rbx, rbp
sub rbx, 144
mov rcx, 32
mov [rbx - 0], rcx
mov rcx, 32
mov [rbx - 8], rcx
mov rcx, 32
mov [rbx - 16], rcx
jmp label74
label73:
mov rbx, rbp
sub rbx, 144
mov rcx, 110
mov [rbx - 0], rcx
mov rcx, 111
mov [rbx - 8], rcx
mov rcx, 32
mov [rbx - 16], rcx
label74:
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
mov rdi, 98
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
mov rdi, 97
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
mov rdi, 101
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
mov rdi, 99
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
mov rax, rbp
sub rax, 144
push rdi
mov rbx, 8
mov rcx, 0
cmp rcx, 0
mov rdx, rcx
jl label99
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label99
imul rbx, rcx
sub rax, rbx
jmp label100
label99:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label100:
pop rdi
mov rdi, [rax]
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 144
push rdi
mov rbx, 8
mov rcx, 1
cmp rcx, 0
mov rdx, rcx
jl label101
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label101
imul rbx, rcx
sub rax, rbx
jmp label102
label101:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label102:
pop rdi
mov rdi, [rax]
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 144
push rdi
mov rbx, 8
mov rcx, 2
cmp rcx, 0
mov rdx, rcx
jl label103
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label103
imul rbx, rcx
sub rax, rbx
jmp label104
label103:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label104:
pop rdi
mov rdi, [rax]
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 121
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
mov rdi, 53
call print_char
pop rdi
push rdi
mov rdi, 37
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
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 112
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
mov rdi, 105
call print_char
pop rdi
push rdi
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
jmp label29
label28:
label29:
push rdi
mov rdi, 76
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
mov rdi, 109
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
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
mov rdi, 116
call print_char
pop rdi
push rdi
mov rdi, 105
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
mov rdi, 101
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
mov rdi, 112
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
mov rdi, 99
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 108
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
sub rax, 112
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, rbp
sub rax, 120
label105:
push rdi
mov rdi, 76
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
mov rdi, 109
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
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
mov rdi, 115
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
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 108
call print_char
pop rdi
push rdi
mov rdi, 117
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
mov rdi, 98
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
mov rdi, 10
call print_char
pop rdi
jmp label107
label106:
push rdi
mov rdi, 76
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
mov rdi, 109
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
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 115
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
mov rdi, 110
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
mov rdi, 115
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
mov rdi, 117
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
mov rdi, 98
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
mov rdi, 10
call print_char
pop rdi
label107:
label0:
leave
ret
