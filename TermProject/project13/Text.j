.class public Test
.super java/lang/Object
; strandard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 32
.limit locals 32
sipush 8
istore 1
sipush 2
istore 2
iload 1
sipush 8
if_icmpne 01
iload 1
sipush 2
iadd
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
goto 10000
01: iload 1
sipush 9
if_icmpne 02
iload 1
sipush 2
iadd
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
goto 10000
02: getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
sipush 2
iadd
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
iload 1
sipush 10
if_icmpne 03
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
sipush 2
iadd
invokevirtual java/io/PrintStream/println(I)V
goto 10001
03: goto 10001
10001: goto 20000
20000: goto 10000
10000: goto 20001
20001: getstatic java/lang/System/out Ljava/io/PrintStream;
ldc "end"
invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V
return
.end method
