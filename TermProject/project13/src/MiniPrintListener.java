import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class MiniPrintListener extends tinyPythonBaseListener {

    @Override public void enterDefs(tinyPythonParser.DefsContext ctx)
    {
//        System.out.println( " 프로그램 시작 DEF: ");
//        System.out.println("start : " + ctx.getTokens(1));
//
//        for(int i = 0; i < ctx.getChildCount(); i ++)
//        {
//            System.out.println(ctx.getTokens(i));
//        }
//        System.out.println("end : " + ctx.stop);
////        System.out.println(ctx.getText());
//        System.out.println("getStart : " + ctx.getStart());
    }

    @Override public void exitDefs(tinyPythonParser.DefsContext ctx)
    {
//        System.out.println( " 프로그램 끝 DEF: ");
//        System.out.println(ctx);
    }

    private static final String FILE_NAME = "Text.j"; // 전역 변수로 파일명 설정
    private static PrintWriter printWriter; // 전역 변수로 PrintWriter 선언


    /*
        파일 내보내는 코드 추가해야함

        개행 및 띄어쓰기, 탭 단위로 나눠야함   -> ctx의 get으로 인자 받도록

        class 이름은 TEST 고정

        함수 무조건 public static으로 정의

        if-else, while 일 때 지정된 틀 안에 값만 넣을 수 있도록
            재귀적으로 반복




    만들어야 하는 함수들

    해당 tpy가 어떤 기능을 하는지 판별하는 함수,

    함수 정의
    변수 store
    변수 load
    println 변수
    println 문자열
 */

    ParseTreeProperty<String> newTexts = new ParseTreeProperty<>();

    boolean isBinaryOperation(tinyPythonParser.ExprContext ctx){
        return ctx.getChildCount() == 3 && ctx.getChild(1) != ctx.expr();
    }


    @Override
    public void exitExpr( tinyPythonParser.ExprContext ctx )
    {
        // 파일 생성    ##########################################################
//        try {
//            FileWriter fileWriter = new FileWriter(FILE_NAME);
//            printWriter = new PrintWriter(fileWriter);
//            //printWriter.println("");
//            //printWriter.print("");
//        } catch (IOException e) {
//            System.out.println("파일 open 실패. 오류 메세지:  " + e.getMessage());
//        }

//        System.out.println(ctx.start);
//        System.out.println(ctx.stop);
//        System.out.println(ctx.getText());

//        System.out.print(ctx.getChildCount() + " :::: " );
//
//        for(int i = 0; i < ctx.getChildCount(); i ++)
//        {
////            System.out.print(ctx.expr(i) + " ");
//            System.out.print(ctx.getChild(i) + ", ");
////            System.out.println(ctx.NAME());
//        }
////        System.out.print(ctx.getChild(i) + " ");
//
//        System.out.print(ctx);
//        System.out.print(ctx.parent);
//
//        if(ctx.getChildCount() == 1)
//        {
//            System.out.print(" put 1");
//            newTexts.put(ctx, ctx.getChild(0).toString());

//        }
//
//        if(ctx.getChildCount() == 2)
//        {
//            System.out.print(" put 2");
//            newTexts.put(ctx, ctx.getChild(0).toString());
//        }
//
//        String s1 = null, s2 = null, op = null;
//        if(isBinaryOperation(ctx))
//        {
////            newTexts.put(ctx.expr(0), );
////            newTexts.put(ctx.expr(0), ctx.getChild(0).toString());
//
//
//            s1 = newTexts.get(ctx.expr(0));
//            s2 = newTexts.get(ctx.expr(1));
//            op = ctx.getChild(1).getText();
//            newTexts.put(ctx, s1 + " " + op + " " + s2);
//            System.out.println(ctx.getChildCount());
//            System.out.println(s1);
//            System.out.println(s2);
//            System.out.println(op);
//        }

//        String[] list = ctx.getChild(0).getText().split("(?=\n)");   // 개행 단위로 문자열 나눔

//        String text = ctx.getChild(0).getText();
//        System.out.println(text);
//
//        List<String> lines = new ArrayList<>();
//        int start = 0;
//        for (int i = 0; i < text.length(); i++) {
//            if (text.charAt(i) == '\n') {
//                lines.add(text.substring(start, i));
//                start = i + 1;
//            }
//        }
//        lines.add(text.substring(start)); // 마지막 줄 추가
//
//        String[] list = lines.toArray(new String[0]);
//
//        for(int i = 0; i < list.length; i ++)
//        {
//            System.out.println(list[i]);
//        }

        StringBuffer sb;

        /*
    //기본 main 작성 함수 시작하자마다 넣기
    .class public Test
    .super java/lang/Object
    ; strandard initializer
    .method public <init>()V
    aload_0
    invokenonvirtual java/lang/Object/<init>()V
    return
    .end method
         */


//        String[] strArr = ctx.getChild(0).getText().split("(?=\n)");
//
//        for(int strArrIndex = 0; strArrIndex < strArr.length; strArrIndex ++)
//        {
//            String str = strArr[strArrIndex];
//            sb = new StringBuffer(str);
//
//            System.out.print(sb);
//
////            System.out.println("변경 후 : " + sb + "\n");
////
//        }  // end for(int currentStrIndex = 0; currentStrIndex < str.length(); currentStrIndex ++)∂


        //Test.j로 파일 생성
//        printWriter.close(); // 파일 닫기
//        System.out.println();
//        System.out.println("################### exit Program ###################");
    }

}
