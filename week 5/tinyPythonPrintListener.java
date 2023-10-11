

import java.util.Objects;
import java.util.Stack;
import java.io.IOException;

import java.util.regex.Pattern;

public class tinyPythonPrintListener extends tinyPythonBaseListener {


//    https://github.com/CNUCOM/compilers_CNU_2023/issues/12

    @Override
    public void exitProgram( tinyPythonParser.ProgramContext ctx )
    {
        // == 는 = 발견 시 뒤에도 =가 있는지 확인해야함                             // 한 line에 대해
        char[] needOneSpaceChar = {'=', '+', '-', ',', '<', '>', '!'};                    // 중복 가능 , '<', '>'
        String[] needOneSpaceStr = {"return", "print", "def", "elif", "if", "else:", "while"};       // 중복 불가능

        String[] needTabPlusStr = {"def", "elif", "else:", "if"};    //, "elif" 는 indexOf의 if로 탐지됨
        String[] needTabMinusStr = {"elif", "else:", "if"};

        String privousKeyWord = "";

        Stack<String> stack_if = new Stack<>();
        Stack<String> stack_else = new Stack<>();
        Stack<String> stack_bracket = new Stack<>();

        StringBuffer sb;

        int tabCnt = 0;

        String[] strArr = ctx.getChild(0).getText().split("\\n");   // 개행 단위로 문자열 나눔

        for(int strArrIndex = 0; strArrIndex < strArr.length; strArrIndex ++)
        {
            String str = strArr[strArrIndex];
            sb = new StringBuffer(str);

//            System.out.println(sb);

            // 기호 처리 ##########################
            for(int i = 0; i < sb.length(); i ++)
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
                            if(i + 1 < sb.length() && sb.charAt(i + 1) == '=') // == 일 경우
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
            } // 기호 처리 끝 ##########################

            // 중복 안되는 키워드 띄어쓰기 처리 ################
            for(int i = 0; i < needOneSpaceStr.length; i ++)
            {
                int startIndex = sb.indexOf(needOneSpaceStr[i]);
                if(startIndex != -1)
                {
                    sb.insert(startIndex + needOneSpaceStr[i].length(), " ");
                    i += (startIndex + needOneSpaceStr[i].length());
                    break;
                }
            } // 중복 안되는 키워드 띄어쓰기 처리 끝 ################


            if(str.matches("if(.*)"))
            {
                stack_if.push("if");
            }

            if(str.matches("def(.*)"))
            {
                stack_if.push("def");
            }

            if(str.matches("while(.*)"))
            {
                stack_if.push("while");
            }



            if ((str.matches("(.*)else:(.*)") && !stack_else.empty() && stack_else.peek().equals("else:"))
                    || (str.matches("(.*)elif(.*)") && !stack_else.empty() && stack_else.peek().equals("else:"))
                    || (sb.isEmpty() && !stack_if.empty() && !stack_else.empty() && stack_else.peek().equals("if"))
                    || (sb.isEmpty() && !stack_if.empty() && !stack_else.empty() && stack_else.peek().equals("elif"))

            )
            {
                stack_if.pop();
                stack_else.pop();
            }

            if(sb.isEmpty() && !stack_if.empty() && stack_if.peek().equals("if")

            )
            {
                while(!stack_if.empty() && stack_if.peek().equals("if"))
                {
                    stack_if.pop();
                }
            }
            else if(sb.isEmpty() && !stack_if.empty() && stack_if.peek().equals("while"))
            {
                while(!stack_if.empty() && stack_if.peek().equals("while"))
                {
                    stack_if.pop();
                }
            }


            int tempnum = stack_if.size();

            if(sb.indexOf(":") != -1)
            {
                if(sb.indexOf("else:") != -1)
                {
                    stack_else.push("else:");
                }
                else if(str.matches("if(.*)"))
                {
                    stack_else.push("if");
                }
                else if(str.matches("elif(.*)"))
                {
                    stack_else.push("elif");
                }
                else if(str.matches("while(.*)"))
                {
                    stack_else.push("while");
                }

                tempnum --;
            }

            for(int i = 0; i < tempnum; i ++)
            {
                System.out.print("\t");
            }

            if(!stack_if.empty() && sb.indexOf("return") != -1)
            {
                if(strArr[strArrIndex + 1].isEmpty() && !stack_if.empty() && !stack_else.empty() && stack_else.peek().equals("else:") && stack_if.peek().equals("if"))
                {
                    stack_if.pop();
                }

                if(stack_if.peek().equals("def") && stack_if.size() == 1)
                {
                    stack_if.pop();
                }
                else
                {

                }
            }

            System.out.println(sb);

        }  // end for(int currentStrIndex = 0; currentStrIndex < str.length(); currentStrIndex ++)




        System.out.println("################### exit Program ###################");
    }

}
