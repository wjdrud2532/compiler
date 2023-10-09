

import java.util.Stack;
import java.io.IOException;
public class tinyPythonPrintListener extends tinyPythonBaseListener {

/*
System.out.print("");
 */

    https://github.com/CNUCOM/compilers_CNU_2023/issues/12

    @Override
    public void exitProgram( tinyPythonParser.ProgramContext ctx )
    {
        // == 는 = 발견 시 뒤에도 =가 있는지 확인해야함                             // 한 line에 대해
        char[] needOneSpaceChar = {'=', '+', '-', ','};                    // 중복 가능 , '<', '>'
        String[] needOneSpaceStr = {"return", "print", "def", "if", "else:"};       // 중복 불가능

        String[] needTabPlusStr = {"def", "if", "else:" };
        String[] needTabMinusStr = {"return"};

        String privousKeyWord = "";

        Stack<String> stack_t = new Stack<>();
        Stack<String> stack_bracket = new Stack<>();

        int tabCnt = 0;

        String[] strArr = ctx.getChild(0).getText().split("\\n");   // 개행 단위로 문자열 나눔

        for(int strArrIndex = 0; strArrIndex < strArr.length; strArrIndex ++)
        {
            String str = strArr[strArrIndex];
            StringBuffer sb = new StringBuffer(str);

//            System.out.println(sb);

            // def, if, else 발견시 needTabCnt ++, return, if문 이후 line 출력시 needTabCnt --
            for(int i = 0; i < tabCnt; i ++)
            {
                System.out.print("\t");
            }


            for(int i = 0; i < sb.length(); i ++)                   // 기호 처리
            {
                for(int j = 0; j < needOneSpaceChar.length; j ++)
                {
                    if(sb.charAt(i) == needOneSpaceChar[j])
                    {
                        if(sb.charAt(i) == ',')
                        {
                            sb.insert(i + 1, " ");
                            i ++;
                        }
                        else
                        {
                            if(sb.charAt(i + 1) == needOneSpaceChar[j]) // == 일 경우
                            {
                                sb.insert(i, " ");
                                sb.insert(i + 3, " ");
                                i += 3;
                            }
                            else
                            {
                                sb.insert(i, " ");
                                sb.insert(i + 2, " ");
                                i += 2;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < needOneSpaceStr.length; i ++)
            {
                int startIndex = sb.indexOf(needOneSpaceStr[i]);
                if(startIndex != -1)
                {
                    for(int j = 0; j < needTabPlusStr.length; j ++)
                    {
                        if(needOneSpaceStr[i].equals(needTabPlusStr[j]))
                        {
                            tabCnt ++;
                        }
                    }

                    for(int j = 0; j < needTabMinusStr.length; j ++)
                    {
                        if(needOneSpaceStr[i].equals(needTabMinusStr[j]))
                        {
                            tabCnt --;
                        }
                    }

//                    if(privousKeyWord.equals("if") || privousKeyWord.equals("else:"))
                    if(privousKeyWord.equals("else:"))
                        tabCnt --;

                    privousKeyWord = needOneSpaceStr[i];

                    sb.insert(startIndex + needOneSpaceStr[i].length(), " ");
                    i += (startIndex + needOneSpaceStr[i].length());
                    break;
                }
            }

            System.out.println(sb);
//            System.out.println();
        }  // end for(int currentStrIndex = 0; currentStrIndex < str.length(); currentStrIndex ++)




        System.out.println("exit Program #### ");
    }

}
