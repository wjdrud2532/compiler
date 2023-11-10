.class public Main2
.super java/lang/Object
; strandard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

; Sum 함수 시작
.method public static Sum(I)I   ; I 받아 I 리턴
.limit stack 32
.limit locals 32

iload_0                         ; n값(100)

iconst_1                        ; result 초기화
ldc 0                           ; result 값 0 스택에 저장
istore_1

;################################
;########   반복분 코드 ########

iconst_2                    ;i 초기화
ldc 1
istore_2                    ; i에 1 저장
1: iload_2                  ; i 불러옴
bipush 100                  ; 100과 i 비교
if_icmpgt 2                 ; 조건에 맞지 않으면 출력으로 넘어감   i > 100 일 경우
iload_1                     ; load result
iload_2                     ; load i
iadd                        ; result + i
istore_1                    ; result = result + i
iinc 2 1                    ; i ++
goto 1                      ; 반복

;################################
;################################

2: iload_1         ; result 불러옴
ireturn         ; result 반환
.end method     ; Sum 함수 종료

; Main2 함수 시작
.method public static main([Ljava/lang/String;)V
.limit stack 32
.limit locals 32


getstatic java/lang/System/out Ljava/io/PrintStream;

ldc 100             ; num의 초기값 0
istore_2            ; 0 저장
iload_2             ; 100 불러와 인자로 넘김

invokestatic Main2/Sum(I)I      ; 모두 더한 값 가져옴
invokevirtual java/io/PrintStream/println(I)V   ; 값 출력
return
.end method