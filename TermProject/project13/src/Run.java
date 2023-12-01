//  23.10.12 12:09
//
//import java.util.Objects;
//import java.util.Stack;
//import java.io.IOException;
//public class tinyPythonPrintListener extends tinyPythonBaseListener {
//
///*
//System.out.print("");
// */
//
////    https://github.com/CNUCOM/compilers_CNU_2023/issues/12
//
//    @Override
//    public void exitProgram( tinyPythonParser.ProgramContext ctx )
//    {
//        // == 는 = 발견 시 뒤에도 =가 있는지 확인해야함                             // 한 line에 대해
//        char[] needOneSpaceChar = {'=', '+', '-', ',', '<', '>', '!'};                    // 중복 가능 , '<', '>'
//        String[] needOneSpaceStr = {"return", "print", "def", "elif", "if", "else:", "while"};       // 중복 불가능
//
//        String[] needTabPlusStr = {"def", "elif", "else:", "if"};    //, "elif" 는 indexOf의 if로 탐지됨
//        String[] needTabMinusStr = {"elif", "else:", "if"};
//
//        String privousKeyWord = "";
//
//        Stack<String> stack_t = new Stack<>();
//        Stack<String> stack_bracket = new Stack<>();
//
//        StringBuffer sb;
//
//        int tabCnt = 0;
//
//        String[] strArr = ctx.getChild(0).getText().split("\\n");   // 개행 단위로 문자열 나눔
//
//        for(int strArrIndex = 0; strArrIndex < strArr.length; strArrIndex ++)
//        {
//            String str = strArr[strArrIndex];
//            sb = new StringBuffer(str);
//
////            System.out.println(sb);
//
//            for(int i = 0; i < sb.length(); i ++)    // 기호 처리 ##########################
//            {
//                for(int j = 0; j < needOneSpaceChar.length; j ++)
//                {
//                    if(sb.charAt(i) == needOneSpaceChar[j])
//                    {
//                        if(sb.charAt(i) == ',')
//                        {
//                            sb.insert(i + 1, " ");
//                            i ++;
//                        }
//                        else
//                        {
//                            if(i + 1 < sb.length() && sb.charAt(i + 1) == '=') // == 일 경우
//                            {
//                                sb.insert(i, " ");
//                                sb.insert(i + 3, " ");
//                                i += 3;
//                            }
//                            else
//                            {
//                                sb.insert(i, " ");
//                                sb.insert(i + 2, " ");
//                                i += 2;
//                            }
//                        }
//                    }
//                }
//            } // 기호 처리 끝 ##########################
//
//            for(int i = 0; i < needOneSpaceStr.length; i ++)
//            {
//                int startIndex = sb.indexOf(needOneSpaceStr[i]);
//                if(startIndex != -1)
//                {
//                    if(needOneSpaceStr[i].equals("return")) // 탭 줄이는 동작
//                    {
//                        if(strArr[strArrIndex + 1].contains("if") || strArr[strArrIndex + 1].contains("else") || strArr[strArrIndex + 1].contains("elif"))
//                        {
//
//                        }
//                        else
//                        {
//                            tabCnt --;
//                        }
//
//                    }
//                    else    // rethrn을 제외한 나머지 것을 이전 키워드에 저장
//                    {
////                        privousKeyWord = needOneSpaceStr[i];
//                    }
//
//                    sb.insert(startIndex + needOneSpaceStr[i].length(), " ");
//                    i += (startIndex + needOneSpaceStr[i].length());
//                    break;
//                }
//            }
//
//            // 반복문 중첩 확인 동작
//            for(int i = 0; i < needTabMinusStr.length; i ++)        // if, elif, else:
//            {
////                int startIndex = sb.indexOf(needTabMinusStr[i]);    // if elif else 가 포함된 경우
//                if(strArrIndex < strArr.length - 1)
//                {
//                    int startIndex = strArr[strArrIndex + 1].indexOf(needTabMinusStr[i]);    // 다음 줄에 if elif else 가 포함된 경우
//
//                    if(startIndex != -1)
//                    {
//
//                        tabCnt -- ;
//
//                        if(!strArr[strArrIndex].isEmpty() && privousKeyWord.equals("if") && needTabMinusStr[i].equals("if"))
//                        {
//                            tabCnt ++;
//                        }
//
////                    if(needTabMinusStr[i].equals("else:") && (privousKeyWord.equals("elif") || privousKeyWord.equals("if")))
////                    {   // 현재가 else고 이전이 elif일 경우
////                        tabCnt --;
////                    }
////                    else if(needTabMinusStr[i].equals("elif") && privousKeyWord.equals("if"))
////                    {   // 현재가 elif 이고 이전이 if 일 경우
////                        tabCnt --;
////                    }
////                    else if(needTabMinusStr[i].equals("if") && strArrIndex > 0 && strArr[strArrIndex - 1].isEmpty())
////                    {   // 현재가 if이고 이전 line이 빈 line일 경우
////                        tabCnt -- ;
////                    }
//
//                        privousKeyWord = needTabMinusStr[i];
//                        break;
//                    }
//                }
//
//            }
//
//
//
//            if(tabCnt < 0)
//                tabCnt = 0;
//
//            // 탭 추가 동작
//            for(int i = 0; i < needTabPlusStr.length; i ++) {
//                int startIndex = sb.indexOf(needTabPlusStr[i]);
//                if (startIndex != -1) {
//                    tabCnt++;
//                    break;
//                }
//            }
//
//            System.out.println(sb);
//
//            // def, if, else 발견시 needTabCnt ++, return, if문 이후 line 출력시 needTabCnt --
//            for(int i = 0; i < tabCnt; i ++)
//            {
//                System.out.print("\t");
//            }
//
////            System.out.println();
//        }  // end for(int currentStrIndex = 0; currentStrIndex < str.length(); currentStrIndex ++)
//
//
//
//
//        System.out.println("exit Program #### ");
//    }
//
//}


/*

while a<=5:
a=a+1

while a>=5:
a=a+1

while a==5:
a=a+1

while a!=5:
a=a+1
 */