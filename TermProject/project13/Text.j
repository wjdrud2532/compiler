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
1: ldc 8
2: istore 1
3: iload 1
4: ldc 6
5: if_icmplt 01
6: getstatic java/lang/System/out Ljava/io/PrintStream;
7: ldc "11"
8: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
9: iload 1
10: ldc 7
11: if_icmplt 02
12: getstatic java/lang/System/out Ljava/io/PrintStream;
13: ldc "22"
14: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
15: iload 1
16: ldc 8
17: if_icmplt 03
18: getstatic java/lang/System/out Ljava/io/PrintStream;
19: ldc "33"
20: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
21: goto 10003
03: goto 22
22: getstatic java/lang/System/out Ljava/io/PrintStream;
23: ldc "44"
24: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
25: goto 10004
04: goto 26
10004: return
10003: return
10002: return
10001: return
10000: return
26: return
5: return
27: goto 10005
02: goto 28
28: getstatic java/lang/System/out Ljava/io/PrintStream;
29: ldc "55"
30: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
31: goto 10006
06: goto 32
10006: return
10005: return
32: return
7: return
33: goto 10007
01: goto 34
34: getstatic java/lang/System/out Ljava/io/PrintStream;
35: ldc "66"
36: invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
37: goto 10008
08: goto 38
10008: return
10007: return
38: return
9: return
return
.end method
