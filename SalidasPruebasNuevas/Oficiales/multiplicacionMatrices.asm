%include "asm_io.inc"

section .bss
   static resb 0

section .text

procleerMatriz34:
enter 40, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label5
label4:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label11
label10:
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
mov rdi, 101
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
mov rdi, 109
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
mov rdi, 116
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
mov rdi, 91
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 93
call print_char
pop rdi
push rdi
mov rdi, 91
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 16
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 122
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
sub rbx, 24
mov [rbx], rax
mov rax, rbp
sub rax, 24
mov rbx, rbp
sub rbx, 32
mov rax, [rax]
push rax
fld qword [rsp]
fistp qword [rsp]
pop rax
mov [rbx], rax
mov rbx, rbp
sub rbx, -208
push rdi
mov rcx, 64
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label22
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label22
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label24
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label24
imul rcx, rdx
sub rbx, rcx
jmp label25
label24:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label25:
pop rdi
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
mov rcx, rbp
sub rcx, 24
mov rcx, [rcx]
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rcx, rbp
sub rcx, 32
mov rcx, [rcx]
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label11:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -216
mov rbx, [rbx]
cmp rax, rbx
jl label10
jmp label12
label12:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label5:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -224
mov rbx, [rbx]
cmp rax, rbx
jl label4
jmp label6
label6:
label0:
leave
ret

procleerMatriz42:
enter 40, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label43
label42:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label49
label48:
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
mov rdi, 101
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
mov rdi, 109
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
mov rdi, 116
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
mov rdi, 91
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 8
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 93
call print_char
pop rdi
push rdi
mov rdi, 91
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, 16
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 122
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
sub rbx, 24
mov [rbx], rax
mov rax, rbp
sub rax, 24
mov rbx, rbp
sub rbx, 32
mov rax, [rax]
push rax
fld qword [rsp]
fistp qword [rsp]
pop rax
mov [rbx], rax
mov rbx, rbp
sub rbx, -144
push rdi
mov rcx, 32
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label60
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label60
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label62
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label62
imul rcx, rdx
sub rbx, rcx
jmp label63
label62:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label63:
pop rdi
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
mov rcx, rbp
sub rcx, 24
mov rcx, [rcx]
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rcx, rbp
sub rcx, 32
mov rcx, [rcx]
mov [rbx], rcx
mov rdx, 8
sub rbx, rdx
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label49:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -152
mov rbx, [rbx]
cmp rax, rbx
jl label48
jmp label50
label50:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label43:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -160
mov rbx, [rbx]
cmp rax, rbx
jl label42
jmp label44
label44:
label38:
leave
ret

procproductoMatrices:
enter 32, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label81
label80:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label87
label86:
mov rax, 0
mov rbx, rbp
sub rbx, -112
sub rbx, 0
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label92
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label92
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label94
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label94
imul rcx, rdx
sub rbx, rcx
jmp label95
label94:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label95:
pop rdi
jmp label93
label92:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label93:
pop rdi
push rax
fild qword [rsp]
fstp qword [rsp]
pop rax
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, -112
sub rbx, 48
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label99
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label99
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label101
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label101
imul rcx, rdx
sub rbx, rcx
jmp label102
label101:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label102:
pop rdi
jmp label100
label99:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label100:
pop rdi
mov [rbx], rax
mov rax, 0
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
jmp label107
label106:
mov rax, rbp
sub rax, -112
sub rax, 0
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label112
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label112
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label114
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label114
imul rbx, rcx
sub rax, rbx
jmp label115
label114:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label115:
pop rdi
jmp label113
label112:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label113:
pop rdi
mov rax, [rax]
mov rbx, rbp
sub rbx, -432
push rdi
mov rcx, 64
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label116
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label116
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 24
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label118
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label118
imul rcx, rdx
sub rbx, rcx
sub rbx, 0
jmp label119
label118:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label119:
pop rdi
jmp label117
label116:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label117:
pop rdi
mov rbx, [rbx]
mov rcx, rbp
sub rcx, -240
push rdi
mov rdx, 32
mov rdi, rbp
sub rdi, 24
mov rdi, [rdi]
cmp rdi, 0
mov rsi, rdi
jl label120
mov rsi, 4
cmp rdi, rsi
mov rsi, rdi
jge label120
imul rdx, rdi
sub rcx, rdx
push rdi
mov rdx, 16
mov rdi, rbp
sub rdi, 16
mov rdi, [rdi]
cmp rdi, 0
mov rsi, rdi
jl label122
mov rsi, 2
cmp rdi, rsi
mov rsi, rdi
jge label122
imul rdx, rdi
sub rcx, rdx
sub rcx, 0
jmp label123
label122:
call print_error_indice
mov rdi, rsi
call print_int
call print_nl
mov eax, 1
int 80h
label123:
pop rdi
jmp label121
label120:
call print_error_indice
mov rdi, rsi
call print_int
call print_nl
mov eax, 1
int 80h
label121:
pop rdi
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
fadd qword [rsp]
fstp qword [rsp + 8]
pop rbx
pop rax
mov rbx, rbp
sub rbx, -112
sub rbx, 0
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label124
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label124
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label126
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label126
imul rcx, rdx
sub rbx, rcx
jmp label127
label126:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label127:
pop rdi
jmp label125
label124:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label125:
pop rdi
mov [rbx], rax
mov rax, rbp
sub rax, -112
sub rax, 48
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label131
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label131
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label133
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label133
imul rbx, rcx
sub rax, rbx
jmp label134
label133:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label134:
pop rdi
jmp label132
label131:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label132:
pop rdi
mov rax, [rax]
mov rbx, rbp
sub rbx, -432
push rdi
mov rcx, 64
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label135
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label135
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 24
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label137
mov rdi, 4
cmp rdx, rdi
mov rdi, rdx
jge label137
imul rcx, rdx
sub rbx, rcx
sub rbx, 8
jmp label138
label137:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label138:
pop rdi
jmp label136
label135:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label136:
pop rdi
mov rbx, [rbx]
mov rcx, rbp
sub rcx, -240
push rdi
mov rdx, 32
mov rdi, rbp
sub rdi, 24
mov rdi, [rdi]
cmp rdi, 0
mov rsi, rdi
jl label139
mov rsi, 4
cmp rdi, rsi
mov rsi, rdi
jge label139
imul rdx, rdi
sub rcx, rdx
push rdi
mov rdx, 16
mov rdi, rbp
sub rdi, 16
mov rdi, [rdi]
cmp rdi, 0
mov rsi, rdi
jl label141
mov rsi, 2
cmp rdi, rsi
mov rsi, rdi
jge label141
imul rdx, rdi
sub rcx, rdx
sub rcx, 8
jmp label142
label141:
call print_error_indice
mov rdi, rsi
call print_int
call print_nl
mov eax, 1
int 80h
label142:
pop rdi
jmp label140
label139:
call print_error_indice
mov rdi, rsi
call print_int
call print_nl
mov eax, 1
int 80h
label140:
pop rdi
mov rcx, [rcx]
imul rbx, rcx
add rax, rbx
mov rbx, rbp
sub rbx, -112
sub rbx, 48
push rdi
mov rcx, 16
mov rdx, rbp
sub rdx, 8
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label143
mov rdi, 3
cmp rdx, rdi
mov rdi, rdx
jge label143
imul rcx, rdx
sub rbx, rcx
push rdi
mov rcx, 8
mov rdx, rbp
sub rdx, 16
mov rdx, [rdx]
cmp rdx, 0
mov rdi, rdx
jl label145
mov rdi, 2
cmp rdx, rdi
mov rdi, rdx
jge label145
imul rcx, rdx
sub rbx, rcx
jmp label146
label145:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label146:
pop rdi
jmp label144
label143:
call print_error_indice
mov rdi, rdi
call print_int
call print_nl
mov eax, 1
int 80h
label144:
pop rdi
mov [rbx], rax
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
label107:
mov rax, rbp
sub rax, 24
mov rax, [rax]
mov rbx, rbp
sub rbx, -440
mov rbx, [rbx]
cmp rax, rbx
jl label106
jmp label108
label108:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label87:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -448
mov rbx, [rbx]
cmp rax, rbx
jl label86
jmp label88
label88:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
label81:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -456
mov rbx, [rbx]
cmp rax, rbx
jl label80
jmp label82
label82:
label76:
leave
ret

procescribirMatrizRegistros34:
enter 24, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label161
label160:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label167
label166:
push rdi
mov rdi, 40
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, -200
push rdi
mov rbx, 64
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label169
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label169
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label171
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label171
imul rbx, rcx
sub rax, rbx
sub rax, 0
jmp label172
label171:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label172:
pop rdi
jmp label170
label169:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label170:
pop rdi
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, -200
push rdi
mov rbx, 64
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label173
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label173
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label175
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label175
imul rbx, rcx
sub rax, rbx
sub rax, 8
jmp label176
label175:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label176:
pop rdi
jmp label174
label173:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label174:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 41
call print_char
pop rdi
push rdi
mov rdi, 9
call print_char
pop rdi
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label167:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -208
mov rbx, [rbx]
cmp rax, rbx
jl label166
jmp label168
label168:
push rdi
mov rdi, 10
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
label161:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -216
mov rbx, [rbx]
cmp rax, rbx
jl label160
jmp label162
label162:
label156:
leave
ret

procescribirMatrizRegistros42:
enter 24, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label188
label187:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label194
label193:
push rdi
mov rdi, 40
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, -136
push rdi
mov rbx, 32
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label196
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label196
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label198
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label198
imul rbx, rcx
sub rax, rbx
sub rax, 0
jmp label199
label198:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label199:
pop rdi
jmp label197
label196:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label197:
pop rdi
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 44
call print_char
pop rdi
push rdi
mov rdi, 32
call print_char
pop rdi
push rdi
mov rax, rbp
sub rax, -136
push rdi
mov rbx, 32
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label200
mov rdx, 4
cmp rcx, rdx
mov rdx, rcx
jge label200
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label202
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label202
imul rbx, rcx
sub rax, rbx
sub rax, 8
jmp label203
label202:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label203:
pop rdi
jmp label201
label200:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label201:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 41
call print_char
pop rdi
push rdi
mov rdi, 9
call print_char
pop rdi
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label194:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -144
mov rbx, [rbx]
cmp rax, rbx
jl label193
jmp label195
label195:
push rdi
mov rdi, 10
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
label188:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -152
mov rbx, [rbx]
cmp rax, rbx
jl label187
jmp label189
label189:
label183:
leave
ret

procescribirMatrizEnteros:
enter 24, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label215
label214:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label221
label220:
push rdi
mov rax, rbp
sub rax, -56
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label223
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label223
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label225
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label225
imul rbx, rcx
sub rax, rbx
jmp label226
label225:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label226:
pop rdi
jmp label224
label223:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label224:
pop rdi
mov rdi, [rax]
call print_int
pop rdi
push rdi
mov rdi, 9
call print_char
pop rdi
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label221:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -64
mov rbx, [rbx]
cmp rax, rbx
jl label220
jmp label222
label222:
push rdi
mov rdi, 10
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
label215:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -72
mov rbx, [rbx]
cmp rax, rbx
jl label214
jmp label216
label216:
label210:
leave
ret

procescribirMatrizReales:
enter 24, 0
mov rax, 0
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
jmp label238
label237:
mov rax, 0
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
jmp label244
label243:
push rdi
mov rax, rbp
sub rax, -56
push rdi
mov rbx, 16
mov rcx, rbp
sub rcx, 8
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label246
mov rdx, 3
cmp rcx, rdx
mov rdx, rcx
jge label246
imul rbx, rcx
sub rax, rbx
push rdi
mov rbx, 8
mov rcx, rbp
sub rcx, 16
mov rcx, [rcx]
cmp rcx, 0
mov rdx, rcx
jl label248
mov rdx, 2
cmp rcx, rdx
mov rdx, rcx
jge label248
imul rbx, rcx
sub rax, rbx
jmp label249
label248:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label249:
pop rdi
jmp label247
label246:
call print_error_indice
mov rdi, rdx
call print_int
call print_nl
mov eax, 1
int 80h
label247:
pop rdi
mov rdi, [rax]
call print_float
pop rdi
push rdi
mov rdi, 9
call print_char
pop rdi
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, 1
add rax, rbx
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
label244:
mov rax, rbp
sub rax, 16
mov rax, [rax]
mov rbx, rbp
sub rbx, -64
mov rbx, [rbx]
cmp rax, rbx
jl label243
jmp label245
label245:
push rdi
mov rdi, 10
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
label238:
mov rax, rbp
sub rax, 8
mov rax, [rax]
mov rbx, rbp
sub rbx, -72
mov rbx, [rbx]
cmp rax, rbx
jl label237
jmp label239
label239:
label233:
leave
ret

   global main
main:
enter 544, 0
mov rax, 3
mov rbx, rbp
sub rbx, 8
mov [rbx], rax
mov rax, 4
mov rbx, rbp
sub rbx, 16
mov [rbx], rax
mov rax, 2
mov rbx, rbp
sub rbx, 24
mov [rbx], rax
push rdi
mov rdi, 76
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 121
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
mov rdi, 112
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
mov rdi, 116
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
mov rdi, 122
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 3
push rax
mov rax, 4
push rax
mov rax, rbp
sub rax, 32
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
call procleerMatriz34
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
pop rax
pop rax
push rdi
mov rdi, 76
call print_char
pop rdi
push rdi
mov rdi, 101
call print_char
pop rdi
push rdi
mov rdi, 121
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
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 101
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
mov rdi, 110
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 122
call print_char
pop rdi
push rdi
mov rdi, 10
call print_char
pop rdi
mov rax, 4
push rax
mov rax, 2
push rax
mov rax, rbp
sub rax, 224
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
call procleerMatriz42
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
pop rax
pop rax
mov rax, 3
push rax
mov rax, 2
push rax
mov rax, 4
push rax
mov rax, rbp
sub rax, 32
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
sub rax, 224
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
sub rax, 448
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
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
push qword [rax]
sub rax,8
add rax,8
push rax
call procproductoMatrices
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
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
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
mov rdi, 112
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
mov rdi, 116
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
mov rdi, 122
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
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rax, 3
push rax
mov rax, 4
push rax
mov rax, rbp
sub rax, 32
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
call procescribirMatrizRegistros34
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
pop rax
pop rax
pop rax
pop rax
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
mov rdi, 115
call print_char
pop rdi
push rdi
mov rdi, 101
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
mov rdi, 110
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 122
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
mov rdi, 110
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rax, 4
push rax
mov rax, 2
push rax
mov rax, rbp
sub rax, 224
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
call procescribirMatrizRegistros42
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
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
push rdi
mov rdi, 76
call print_char
pop rdi
push rdi
mov rdi, 97
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
mov rdi, 112
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
mov rdi, 116
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
mov rdi, 122
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
mov rdi, 10
call print_char
pop rdi
mov rax, 3
push rax
mov rax, 2
push rax
mov rax, rbp
sub rax, 448
sub rax, 0
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
call procescribirMatrizReales
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
push rdi
mov rdi, 76
call print_char
pop rdi
push rdi
mov rdi, 97
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
mov rdi, 101
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
mov rdi, 110
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
mov rdi, 109
call print_char
pop rdi
push rdi
mov rdi, 97
call print_char
pop rdi
push rdi
mov rdi, 116
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
mov rdi, 122
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
mov rdi, 10
call print_char
pop rdi
mov rax, 3
push rax
mov rax, 2
push rax
mov rax, rbp
sub rax, 448
sub rax, 48
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
call procescribirMatrizEnteros
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
pop rax
label256:
leave
ret
