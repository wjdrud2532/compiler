
import java.io.IOException;

public class test {

    public static int returnA(int A) {
        return A;
    }
    
    public static void main(String[] args) throws IOException {

        int num1 = 5;
        int num2 = 6;

        num1 = 50 + (10 - 20);

        // System.out.println(returnA(num1));
        num2 = returnA(num1) + 999;
        

/*
iload 2
invokestatic Test/sum111(I)I
invokevirtual java/io/PrintStream/println(I)V
*/

        // int num3 = 5;
        // int num4 = 50;
        // int num5 = 500;
        // int num6 = 5000;
        // int num7 = 50000;
        // int num8 = 500000;
        // int num9 = 5000000;
        // int num10 = 5000000;


    }


}


/*

    if와 while의 비교는
    정수와 정수 
    변수와 정수
    변수와 변수 
            두 가지로 나눌 수 있다 

    if 등장시

        iload 번호
        iconst_5
        if_icmple

        > if_icmple

        < if_icmpge 

        == icmpne

        <= icmpgt

        >= icmplt

        !- icmpeq

*/