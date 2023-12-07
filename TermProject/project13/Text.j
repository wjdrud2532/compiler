.class public Test
.super java/lang/Object
; strandard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method public static sum111(I)I 
.limit stack 32
.limit locals 32
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual java/io/PrintStream/println(I)V
iload 0
sipush 1
iadd
istore 0
iload 0
ireturn
.end method
.method public static sum222(I)I 
.limit stack 32
.limit locals 32
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual java/io/PrintStream/println(I)V
iload 0
sipush 1
iadd
istore 0
iload 0
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 32
.limit locals 32
sipush 5
istore 1
sipush 10
istore 2
return
.end method
