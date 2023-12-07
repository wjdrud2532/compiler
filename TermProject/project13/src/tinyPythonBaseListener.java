// Generated from /Users/han/IdeaProjects/project13/tinyPython.g4 by ANTLR 4.13.1


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

import java.util.LinkedList; //import
import java.util.Queue; //import


/**
 * This class provides an empty implementation of {@link tinyPythonListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class tinyPythonBaseListener implements tinyPythonListener {

	static HashMap<String,String> keyAndValue = new HashMap<String,String>();
	static HashMap<String,String> keyAndNum = new HashMap<String,String>();
	static int pushNum = 1;

	public static String ifNotExixtMakeMap(String var) {

		if(keyAndNum.get(var) == null)
			{
				keyAndNum.put(var, Integer.toString(pushNum));
				pushNum ++;
			}

			return keyAndNum.get(var);
	}

	public static boolean isInteger(String strValue) {
		try {	Integer.parseInt(strValue);	return true;	}
		catch (NumberFormatException ex) {	return false;	}	}

	public static String CalcTwoString(String num1, String num2, String op) {

		int tnum1 = 0, tnum2 = 0, sum = 0;

		if(isInteger(num1) == false)	// num1에 대한 map value 값을 찾아야 하는 경우
		{

			printWriter.println("iload " + keyAndNum.get(num1));
		}
		else
		{
			tnum1 = Integer.parseInt(num1);
			printWriter.println("sipush " + num1);
		}


		if(isInteger(num2) == false)	// num2에 대한 map value 값을 찾아야 하는 경우
		{
			tnum2 = Integer.parseInt(keyAndValue.get(num2));
			printWriter.println("iload " + keyAndNum.get(num2));
		}
		else
		{
			tnum2 = Integer.parseInt(num2);
			printWriter.println("sipush " + num2);
		}


		if(op.equals("+"))
		{
			sum = tnum1 + tnum2;
			printWriter.println("iadd");
		}
		else
		{
			sum = tnum1 - tnum2;
			printWriter.println("isub");
		}

		return Integer.toString(sum);
	}


	/*
	저장된 변수들의 메모리 값을 불러오기 위한 map
	a가 istore_1 에 저장되었다면
	key : a
	value : istore_1

	map에 값 추가 || map.put("key", "value");
	map에 들어있는 key 값 찾기 || System.out.println(map.containsKey("key"));   // true
	 */

	Stack<Integer> stackInt = new Stack<>();
	Stack<String> stackForPrint = new Stack<>();

	Queue<String> queueForPrint = new LinkedList<>();
	Queue<String> queueForAlloca = new LinkedList<>();
	
	Queue<String> queueForReturn = new LinkedList<>();
	boolean printStart = false;
	boolean allocaStart = false;
	boolean returnStart = false;
	boolean mainStart = true;
	boolean defStart = false;

	boolean elseStart = false;

	int ifCnt = 1;
	int returnCnt = 10000;
	int whileCnt = 30000;
	Stack<Integer> stackForIf = new Stack<>();
	Stack<Integer> stackForWhile = new Stack<>();
	Stack<Integer> stackForReturn = new Stack<>();

	private static final String FILE_NAME = "Text.j"; // 전역 변수로 파일명 설정
	private static PrintWriter printWriter; // 전역 변수로 PrintWriter 선언

	ParseTreeProperty<String> newTexts = new ParseTreeProperty<>();
	int globalCnt = 20000;	// line 수 체크를 위한 변수
	String globalStr = "";


	public void WriteGlobalNum() {
		System.out.println("print globalCnt ::::: ");
		printWriter.print(globalCnt + " : ");
		printWriter.flush();
//		printWriter.println(globalCnt + " : ");
		globalCnt ++;
	}
	
	@Override public void enterProgram(tinyPythonParser.ProgramContext ctx) {
		System.out.println( " Program 시작 : ");

		// 파일 생성 및 open
		try {
			FileWriter fileWriter = new FileWriter(FILE_NAME);
			printWriter = new PrintWriter(fileWriter);
		} catch (IOException e) {
			System.out.println("파일 open 실패. 오류 메세지:  " + e.getMessage());
		}

		printWriter.println(".class public Test\n" +
				".super java/lang/Object\n" +
				"; strandard initializer\n" +
				".method public <init>()V\n" +
				"aload_0\n" +
				"invokenonvirtual java/lang/Object/<init>()V\n" +
				"return\n" +
				".end method");
	}
	
	@Override public void exitProgram(tinyPythonParser.ProgramContext ctx) {
		System.out.println( " Program 끝 : ");
		printWriter.println("return\n" +
				".end method");
		printWriter.close(); // 파일 닫기
	}
	
	@Override public void enterFile_input(tinyPythonParser.File_inputContext ctx) {
//		System.out.println( " File_input 시작 : ");
	}
	
	@Override public void exitFile_input(tinyPythonParser.File_inputContext ctx) {
//		System.out.println( " File_input 끝 : ");
	}
	
	@Override public void enterDefs(tinyPythonParser.DefsContext ctx) {
		System.out.println( " Defs 시작 : ");
		defStart = true;
	}
	
	@Override public void exitDefs(tinyPythonParser.DefsContext ctx) {
		System.out.println( " Defs 끝 : ");
		defStart = false;
	}
	
	@Override public void enterStmt(tinyPythonParser.StmtContext ctx) {
//		System.out.println( " Stmt 시작 : ");

//		System.out.println(ctx.parent.toString() + "  ffff   ");
//		printWriter.println(ctx.parent.getPayload() + "  ffff   ");
//		printWriter.println(ctx.parent.getRuleContext() + "  ffff   ");
//		printWriter.println(ctx.parent.getClass() + "  ffff   ");
//		printWriter.println(ctx.parent.toString() + "  ffff   ");
//		printWriter.println(ctx.parent.parent.getChild(0) + " ggggg  ") ;
//		printWriter.println(ctx.parent.parent.parent + " ggggg  ") ;

		if(mainStart && !ctx.parent.parent.getChild(0).equals("def") && ctx.parent.parent.parent == null )
		{
			printWriter.println(".method public static main([Ljava/lang/String;)V\n" +
					".limit stack 32\n" +
					".limit locals 32");
			mainStart = false;
		}
	}

	@Override public void exitStmt(tinyPythonParser.StmtContext ctx) {

	}
	
	@Override public void enterSimple_stmt(tinyPythonParser.Simple_stmtContext ctx) {
//		System.out.println( " simple_stmt_시작 : ");


	}
	
	@Override public void exitSimple_stmt(tinyPythonParser.Simple_stmtContext ctx) {
		System.out.println( " simple_stmt_END : ");

//		if(!stackForIf.isEmpty())
//			printWriter.print("0" + stackForIf.pop() + ": ");
//			printWriter.print("0" + (stackForIf.peek()) + ": 222 ");
	}
	
	@Override public void enterSmall_stmt(tinyPythonParser.Small_stmtContext ctx) {
//		System.out.println( " Small_stmt_START : ");
	}
	
	@Override public void exitSmall_stmt(tinyPythonParser.Small_stmtContext ctx) {
//		System.out.println( " Small_stmt_END : ");
	}
	
	@Override public void enterAssignment_stmt(tinyPythonParser.Assignment_stmtContext ctx) {
//		System.out.println( " Assignment_stmt 시작 : ");	// 변수 할당
		allocaStart = true;
	}
	
	@Override public void exitAssignment_stmt(tinyPythonParser.Assignment_stmtContext ctx) {
//		System.out.println( " Assignment_stmt END : ");	// 변수 할당 끝

		String key = "";
		String value = "";



		key = queueForAlloca.poll();
		queueForAlloca.poll();	// remove simbol '='

		if(keyAndNum.containsKey(key) == false)	// 이전에 store 되지 않았다면
		{
			keyAndNum.put(key, Integer.toString(pushNum));
			pushNum ++;
		}

		while(!queueForAlloca.isEmpty())
		{
			String tstr1 = queueForAlloca.poll();

			if(isInteger(tstr1) == true)	// 숫자일 경우
				printWriter.println("sipush " + tstr1);

			else if(!tstr1.equals("+") && !tstr1.equals("-") && !tstr1.equals("(") && !tstr1.equals(")"))	// 변수일 경우
				printWriter.println("iload " + keyAndNum.get(tstr1));

			else	// 연산 기호인 경우 다음 값 확인
			{
				String tstr2 = queueForAlloca.poll();

				if(isInteger(tstr2) == true)	// 숫자일 경우
					printWriter.println("sipush " + tstr2);
				else
					printWriter.println("iload " + keyAndNum.get(tstr2));

				if(tstr1.equals("+"))
					printWriter.println("iadd");
				else
					printWriter.println("isub");
			}
		}

		printWriter.println( "istore " + keyAndNum.get(key));

		allocaStart = false;
	}
	
	@Override public void enterFlow_stmt(tinyPythonParser.Flow_stmtContext ctx) {
		System.out.println( " Flow_stmt START : ");
	}
	
	@Override public void exitFlow_stmt(tinyPythonParser.Flow_stmtContext ctx) {
		System.out.println( " Flow_stmt END : ");
	}
	
	@Override public void enterBreak_stmt(tinyPythonParser.Break_stmtContext ctx) {
		System.out.println( " Break START : ");
	}
	
	@Override public void exitBreak_stmt(tinyPythonParser.Break_stmtContext ctx) {
		System.out.println( " Break END : ");
	}
	
	@Override public void enterContinue_stmt(tinyPythonParser.Continue_stmtContext ctx) {
		System.out.println( " Continue_stmt START : ");
	}
	
	@Override public void exitContinue_stmt(tinyPythonParser.Continue_stmtContext ctx) {
		System.out.println( " Continue_stmt END : ");
	}
	
	@Override public void enterCompound_stmt(tinyPythonParser.Compound_stmtContext ctx) {
		System.out.println( " Compound_stmt_START : ");
	}
	
	@Override public void exitCompound_stmt(tinyPythonParser.Compound_stmtContext ctx) {
		System.out.println( " Compound_stmt_END : ");


	}

	@Override public void enterIf_stmt(tinyPythonParser.If_stmtContext ctx) {
		System.out.println( " IF_START : ");

		stackForReturn.push(returnCnt++);
	}

	@Override public void exitIf_stmt(tinyPythonParser.If_stmtContext ctx) {
		System.out.print( " IF_END : ");
//
//		if(!stackForIf.isEmpty())
//			printWriter.print("0" + stackForIf.pop() + ": fff ");

//		printWriter.println(stackForIf.pop() + ": goto " + globalCnt);
		if(!stackForIf.isEmpty())
			printWriter.println("0" + stackForIf.pop() + ": goto " + stackForReturn.peek());

		printWriter.println(stackForReturn.pop() + ": goto " + globalCnt);
		printWriter.print("" + globalCnt + ": ");
		globalCnt ++;

//		printWriter.println("if_end #####################");
	}
	
	@Override public void enterWhile_stmt(tinyPythonParser.While_stmtContext ctx) {
		System.out.println( " WHILE START : ");

//		printWriter.println("while start");
		stackForWhile.push(whileCnt);
		printWriter.print(whileCnt + ": ");
		whileCnt ++;
//		printWriter.println(whileCnt++ + ": goto " + globalCnt);

//		stackForReturn.push(returnCnt++);
	}
	
	@Override public void exitWhile_stmt(tinyPythonParser.While_stmtContext ctx) {
		System.out.println( " WHILE END : ");
//		printWriter.println("while end");
		printWriter.println("goto " + stackForWhile.pop());
		printWriter.print("0" + stackForIf.pop() + ": ");
//		printWriter.println("goto " + stackForWhile.pop());

	}
	
	@Override public void enterDef_stmt(tinyPythonParser.Def_stmtContext ctx) {
		System.out.println( " Def_stmt START : ");

//		printWriter.println("def_start ######################");

		printWriter.println(".method public static " + ctx.NAME() + "(I)I \n" +
				".limit stack 32\n" +
				".limit locals 32");

	}
	
	@Override public void exitDef_stmt(tinyPythonParser.Def_stmtContext ctx) {
		System.out.println( " Def_stmt END : ");

		printWriter.println(".end method");

//		printWriter.println("def_end @@@@@@@@@@@@@@@@@@@@@@@@@");

	}

	
	@Override public void enterSuite(tinyPythonParser.SuiteContext ctx) {
		System.out.println( " Suite START : ");



	}
	
	@Override public void exitSuite(tinyPythonParser.SuiteContext ctx) {
		System.out.println( " Suite_END : ");

		if(ctx.parent.getChild(0).toString().equals("while"))
		{
//			printWriter.println("goto dddd ");
		}

		if(ctx.parent.getChild(0).toString().equals("if"))
		{
			printWriter.println("goto " + stackForReturn.peek());

			if(ctx.parent.getChild(ctx.parent.getChildCount() - 1) != ctx)
				if(!stackForIf.isEmpty())
					printWriter.print("0" + stackForIf.pop() + ": ");
		}


	}
	
	@Override public void enterArgs(tinyPythonParser.ArgsContext ctx) {
		System.out.println( " Args START : ");
	}
	 
	@Override public void exitArgs(tinyPythonParser.ArgsContext ctx) {
		System.out.println( " Args END : ");
	}
	 
	@Override public void enterReturn_stmt(tinyPythonParser.Return_stmtContext ctx) {
		System.out.println( " Return_stmt START : ");

		returnStart = true;
	}
	 
	@Override public void exitReturn_stmt(tinyPythonParser.Return_stmtContext ctx) {
		System.out.println( " Return_stmt_END : ");

		if(returnStart == true)
		{
			int printnum = 0;

			while(!queueForReturn.peek().equals("return"))
			{
				queueForReturn.remove();
			}
			queueForReturn.remove();	// return 제거

			while(!queueForReturn.isEmpty())
			{
				String tstr1 = queueForReturn.poll();

				if(isInteger(tstr1) == true)	// 숫자일 경우
					printWriter.println("sipush " + tstr1);

				else if(!tstr1.equals("+") && !tstr1.equals("-"))
					printWriter.println("iload " + ifNotExixtMakeMap(tstr1));

				else	// 연산 기호인 경우 다음 값 확인
				{
					String tstr2 = queueForReturn.poll();

					if(isInteger(tstr2) == true)	// 숫자일 경우
						printWriter.println("sipush " + tstr2);
					else
						printWriter.println("iload " + ifNotExixtMakeMap(tstr2));

					if(tstr1.equals("+"))
						printWriter.println("iadd");
					else
						printWriter.println("isub");
				}


			}

			returnStart = false;

			queueForReturn.clear();
			printWriter.println( "ireturn");
		}

	}
	 
	@Override public void enterTest(tinyPythonParser.TestContext ctx) {
		System.out.println( " Test_START : ");



		/*
		무조건 2개 오는 것이 규칙
		1. 각각의 인자에 대해 변수인지 값인지 판별
			1-1. 변수면 map 에서 값 찾아와 load
			1-2. 정수면 sipush 값
		2. 비교문 판별
		3.

		while 의 경우 마지막에 돌아가는 goto 문이 필요하다
		while enter의 if_cnt를 기억해야 한다
		 */

		String[] sign = 		 {">"		 , "<"		  ,	"=="	   , "<="	    , ">="		 , "!="	  };
		String[] compareTwoVar = {"if_icmple", "if_icmpge", "if_icmpne", "if_icmpgt", "if_icmplt", "if_icmpeq"};

		String tstr1 = ctx.expr(0).getText();
		String tstr2 = ctx.expr(1).getText();
		String compOp = ctx.comp_op().getText();

//		if(!stackForIf.isEmpty())
//			printWriter.print("0" + stackForIf.peek() + ": 333 ");

		if(isInteger(tstr1) == false)	// 변수라면
		{
			printWriter.println( "iload " + keyAndNum.get(tstr1));
		}
		else
		{
			printWriter.println( "sipush " + tstr1);
		}

		if(isInteger(tstr2) == false)
		{
			printWriter.println( "iload " + keyAndNum.get(tstr2));
		}
		else
		{
			printWriter.println( "sipush " + tstr2);
		}


		for(int i = 0; i < sign.length; i ++)
		{
			if(sign[i].equals(compOp))
			{
				stackForIf.push(ifCnt);
				printWriter.println("" + compareTwoVar[i] + " 0" + ifCnt + "");
				ifCnt ++;

				break;
			}
		}

//		System.out.println(tstr1 + " " + compOp + " " + tstr2 );


	}
	 
	@Override public void exitTest(tinyPythonParser.TestContext ctx) {
		System.out.println( " Test_END : ");
	}
	 
	@Override public void enterPrint_stmt(tinyPythonParser.Print_stmtContext ctx) {
//		System.out.println( " Print_stmt START : ");


		printWriter.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
		printStart = true;
//		System.out.println("child is : " + ctx);
	}
	 
	@Override public void exitPrint_stmt(tinyPythonParser.Print_stmtContext ctx) {
//		System.out.println( " Print_stmt END : ");

		if(printStart == true && ctx.print_arg().STRING() == null)	// 문자열 출력이 아닌 경우만 실행
		{
			int printnum = 0;

			while(!queueForPrint.peek().equals("print"))
			{
				queueForPrint.remove();
			}
			queueForPrint.remove();	// print 제거

			while(!queueForPrint.isEmpty())
			{
				String tstr1 = queueForPrint.poll();

				if(isInteger(tstr1) == true)	// 숫자일 경우
					printWriter.println("sipush " + tstr1);

				else if(!tstr1.equals("+") && !tstr1.equals("-"))
					printWriter.println("iload " + ifNotExixtMakeMap(tstr1));

				else	// 연산 기호인 경우 다음 값 확인
				{
					String tstr2 = queueForPrint.poll();

					if(isInteger(tstr2) == true)	// 숫자일 경우
						printWriter.println("sipush " + tstr2);
					else
						printWriter.println("iload " + ifNotExixtMakeMap(tstr2));

					if(tstr1.equals("+"))
						printWriter.println("iadd");
					else
						printWriter.println("isub");
				}


			}

			printStart = false;

			queueForPrint.clear();
			printWriter.println( "invokevirtual java/io/PrintStream/println(I)V");
		}

	}
	 
	@Override public void enterPrint_arg(tinyPythonParser.Print_argContext ctx) {
//		System.out.println( " Print_arg START : ");

		if(ctx.STRING() != null)
		{
			printWriter.println( "ldc " + ctx.STRING());
			printWriter.println("invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
			printStart = false;
			queueForPrint.clear();
		}
	}

	@Override public void exitPrint_arg(tinyPythonParser.Print_argContext ctx) {
//		System.out.println( " Print_arg END : ");
	}
	 
	@Override public void enterComp_op(tinyPythonParser.Comp_opContext ctx) {
//		System.out.println( " Comp_op_START : ");
	}
	 
	@Override public void exitComp_op(tinyPythonParser.Comp_opContext ctx) {
//		System.out.println( " Comp_op_END : ");
	}
	 
	@Override public void enterExpr(tinyPythonParser.ExprContext ctx) {
//		System.out.println( " Expr START : ");
//		System.out.println(ctx.NAME());

	}
	 
	@Override public void exitExpr(tinyPythonParser.ExprContext ctx) {
//		System.out.println( " Expr END : ");




	}
	 
	@Override public void enterOpt_paren(tinyPythonParser.Opt_parenContext ctx) {
//		System.out.println( " Opt_paren_START : ");
//		System.out.println(ctx);
//		System.out.println(newTexts.get(ctx));
	}

	@Override public void exitOpt_paren(tinyPythonParser.Opt_parenContext ctx) {
//		System.out.println( " Opt_paren END : ");
	}

	 
	@Override public void enterEveryRule(ParserRuleContext ctx) {
//		System.out.println( " EveryRule START : ");
	}
	 
	@Override public void exitEveryRule(ParserRuleContext ctx) {
//		System.out.println( " EveryRule END : ");
	}
	 
	@Override public void visitTerminal(TerminalNode node) {


//		System.out.println(node);
		if(Objects.equals(node, null))
		{
			System.out.println("node is NULL");
			return ;
		}

		if(printStart == true)
		{
			queueForPrint.offer(node.toString());
		}
		else if(allocaStart == true)
		{
			queueForAlloca.offer(node.toString());
		}
		else if(returnStart == true)
		{
			queueForReturn.offer(node.toString());
		}


//		System.out.println(node.getParent() + "   ||||||   "  + node);
//		newTexts.put(node.getParent(), node.toString());
//		//			key			value(get 했을 때 불려오는 값)
////		node.
//		System.out.print( "visitTerminal : ");
//		System.out.println(newTexts.get(node.getParent()));
////		globalStr = node.toString();


	}
	 
	@Override public void visitErrorNode(ErrorNode node) {
		System.out.println( " visitTerminal : ");
	}
}