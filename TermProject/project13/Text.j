.class public Test
.super java/lang/Object
; strandard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method public static sum1(I)I 
.limit stack 32
.limit locals 32
iload 0
sipush 5
iadd
istore 0
iload 0
ireturn
.end method
.method public static sum2(I)I 
.limit stack 32
.limit locals 32
iload 0
sipush 5
iadd
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 32
.limit locals 32
sipush 5
istore 1
iload 1
invokestatic Test/sum2(I)I
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokestatic Test/sum1(I)I
iload 1
invokestatic Test/sum2(I)I
iadd
invokevirtual java/io/PrintStream/println(I)V
return
.end method
