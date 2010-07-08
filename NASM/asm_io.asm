%macro prologue 0
        push    rbp
        mov     rbp,rsp
        push    rax
        push    rbx
        push    rcx
        push    rdx
        push    rdi
        push    rsi
        push    r8
        push    r9
        push    r10
        push    r11
        push    r12
        push    r13
        push    r14
        push    r15
%endmacro
%macro epilogue 0
        pop     r15
        pop     r14
        pop     r13
        pop     r12
        pop     r11
        pop     r10
        pop     r9
        pop     r8
        pop     rsi
        pop     rdi
        pop     rdx
        pop     rcx
        pop     rbx
        pop     rax
        leave
        ret
%endmacro

section .bss
   static resb 5
   memfpu resb 108

section .rodata
    int_format  db  "%li", 0
    string_format db "%s", 0
    float_format db "%lf", 0
    char_format db "%c", 0
    exp db " x 2^", 0
    minus db "-", 0
    true db "true", 0
    false db "false", 0
    error_indice db "Indice fuera de rango con valor ", 0
    error_discriminante db "Acceso a campo no activo", 0	

section .text
    global  print_string, print_nl, print_int, print_bool, print_char, print_float, print_reg, read_int, read_float, read_bool, read_char, memory_malloc, print_error_indice, print_error_discriminante
    extern printf, scanf, putchar, malloc ; fopen, fprintf, fclose

    memory_malloc:
	prologue
	mov rsi, rdi
	xor rax, rax
	call malloc
	epilogue	

    print_string:
        prologue
        mov     rsi,rdi
        mov     rdi,dword string_format
        xor     rax,rax
        call    printf
        epilogue

    print_char:
        prologue
        xor     rax,rax
        call    putchar
        epilogue

    print_nl:
        prologue
        mov     rdi,0xA
        xor     rax,rax
        call    putchar
        epilogue

    print_signo:
        prologue
        cmp     rdi, 1
        je      print_minus
    nexts:
        epilogue

    print_minus:
       mov     rdi, minus
       call    print_string
       jmp     nexts

    print_float:
        prologue
        fsave [memfpu]

        ;Signo
        mov  rax, rdi
        shr  rdi, 63
        call print_signo

        ;Parte natural
        mov  rdi, rax
        movq mm0, rdi
        mov  rbx, 0xFFFFFFFFFFFFF
        push rbx
        pand mm0, [rsp]
        mov  rcx, 0x10000000000000
        push rcx
        por  mm0, [rsp]
        pop  rcx
        movq [rsp], mm0
        pop  rdi
        call print_int

	;Exponente		
        mov  rdi, exp
        call print_string
        mov  rdi, rax
        movq mm0, rdi
        mov  rbx, 0x7FF0000000000000
        push rbx
        pand mm0, [rsp]
        movq [rsp], mm0
        pop  rdi
        shr  rdi, 52
        sub  rdi, 1023
        sub  rdi, 52
        call print_int

        frstor [memfpu]
        epilogue
        
    print_reg:

        prologue
        mov rax, rdi
        mov rcx, 63

        print_bit:
            mov  rdi, rax
            shr  rdi, cl
            and  rdi, 0x1
            call print_int

            sub  rcx, 1            
            cmp  rcx, -1
            jne  print_bit

       epilogue

    print_bool:
        prologue
        cmp     rdi, 0
        jz      print_false
        mov     rdi, true
        call    print_string
    nextb:
        epilogue

    print_false:
       mov     rdi, false
       call    print_string
       jmp     nextb

    print_int:
        prologue
        mov     rsi, rdi
        mov     rdi, dword int_format
        xor     rax,rax
        call    printf
        epilogue

    print_error_indice:
	prologue
        mov     rdi, error_indice
        call    print_string	
        epilogue

    print_error_discriminante:
	prologue
        mov     rdi, error_discriminante
        call    print_string	
        epilogue

    read_int:
        prologue
        mov     rsi, rdi
        mov     rdi, dword int_format
        xor     rax,rax
        call    scanf
        epilogue

    read_float:
        prologue
        mov     rsi, rdi
        mov     rdi, dword float_format
        xor     rax,rax
        call    scanf
        epilogue

    read_char:
        prologue
        mov     rsi, rdi
        mov     rdi, dword char_format
        xor     rax,rax
        call    scanf
        epilogue

    read_bool:
        prologue
        push    rdi
        mov     rsi, static
        mov     rdi, dword string_format
        xor     rax,rax
        call    scanf
        pop     rdi
        call    cmp_true
        epilogue

    cmp_true:
        prologue
        mov     rax, [static]
        mov     rbx, [true]
        movq    mm0, rbx
        mov     rcx, 0xFFFFFFFFFF
        push    rcx
        pand    mm0, [rsp]
        movq    [rsp], mm0
        pop     rbx
        cmp     rax, rbx
        je      is_true
        call    cmp_false
        nextt:
        epilogue

    is_true:
        mov qword [rdi], 1
        jmp nextt


    cmp_false:
        prologue
        mov     rax, [static]
        mov     rbx, [false]
        movq    mm0, rbx
        mov     rcx, 0xFFFFFFFFFFFF
        push    rcx
        pand    mm0, [rsp]
        movq    [rsp], mm0
        pop     rbx
        cmp     rax, rbx
        je      is_false
        nextf:
        epilogue

    is_false:
        mov qword [rdi], 0
        jmp nextf

;    open_file:
;	prologue
;	mov rdi, dword file_name
;	mov rsi, dword write_mode
;	xor rax,rax
;	call fopen
;	epilogue

;    write_file:
;	prologue
;	mov rsi, dword string_prueba
;	mov rdi, rax
;	xor rax,rax
;	call fprintf
;	epilogue

;    close_file:
;	prologue
;	xor rax, rax
;	call fclose
;	epilogue    

