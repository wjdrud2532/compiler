// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


//###############################
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;      // 파일 읽기
import java.io.FileWriter;      // 파일 쓰기
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Stack;

public class Main
{
    static String startOutput = "#include <stdio.h>\n\n" +
            "int main() \n{\n" +
            "\t int val[3] = {0, 0, 0}; \n";

    static String endOutput = "return 0\n" +
            "}";

    public static String removeUmm(String str)
    {
        int ummIndex = str.indexOf("엄");

        str = str.substring(ummIndex + 1);

        return str;
    }
    public static String findUmm(String str)
    {
        String allocaValue;
        int cnt = 1;
        int ummIndex = str.indexOf("엄");

        for(int i = ummIndex - 1; i >= 0; i --)
        {
            if(str.charAt(i) == '어')
                cnt ++;
            else
                break;
        }

        allocaValue = "val[" + cnt + "] = ";

        return allocaValue;
    }

    public static int calcComa_forSik(String str)
    {
        Stack<String> stack = new Stack<>();
        int value = 0;

        if(str.length() > 0 && str.charAt(0) == '어')
        {
            return str.length();
        }

        int tempnum = 0;
        for(int i = 0; i < str.length(); i ++)
        {


        }

        return value;
    }
    public static String calcComa(String str)
    {
        Stack<String> stack = new Stack<>();
        String value = "";

        for(int i = 0; i < str.length();)
        {
            if(str.charAt(i) == '어')
            {
                if(i > 0)
                    value += " + ";

                int tempCnt = 0;
                while(i < str.length() && str.charAt(i) == '어')
                {
                    i ++;
                    tempCnt ++;
                }

                value += "val[" + tempCnt + "]";
            }

            else if(str.charAt(i) == '.' || str.charAt(i) == ',')
            {
                if(i > 0)
                    value += " + ";

                int tempcnt = 0;
                char tempchar = str.charAt(i);

                while((tempchar == '.' || tempchar == ',') && i < str.length())
                {
                    tempchar = str.charAt(i);

                    if(tempchar == '.')
                        tempcnt ++;
                    else if(tempchar == ',')
                        tempcnt --;
                    else
                    {
                        tempcnt = tempcnt;
                    }

                    i ++;

                }

                value +=  "(" + tempcnt + ")";
            }

            else    // 공백일 경우 일단 넘어감
            {
                value += " * ";
                i++;
            }
        }

        return value;
    }


    public static String sikInstruction(char sikType, int indexOrAskiNum) // 출력은 변수 또는 아스키코드 두가지로 나눠야함
    {

        String printfInstruction = "\tprintf(\"%";     // printf("%
        if (sikType == '!') {
            printfInstruction += "d\", val[" + indexOrAskiNum + "]); \n";      // printf("%d", val[1]);
        }

        else if (sikType == 'ㅋ')    // 아스키 코드를 출력해야 하는 경우
        {
            printfInstruction += "c”, " + indexOrAskiNum + "); \n";            // printf("%c", 1);
        }

        else if(sikType == '?')   // ? (입력) 일 경우
        {
            printfInstruction = "\tscanf(\"%d\", val[" + indexOrAskiNum + "]); \n";   // scanf("%d", val[1]);
        }

        return printfInstruction;
    }

    public static void main(String[] args) throws IOException
    {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        File file = new File("test/test.umm");
        File output = new File("outputCFile/test.c");

        output.createNewFile();

        FileWriter fw = new FileWriter(output);
        BufferedWriter writer = new BufferedWriter(fw);

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = br.readLine()) != null)
                {

                    String result = ""; // write를 위한 최종 결과물

                    if(line.equals("어떻게"))
                    {
                        result = startOutput;
                    }

                    else if(line.equals("이 사람이름이냐ㅋㅋ"))
                    {
                        result = endOutput;
                    }

                    else
                    {
                        char sikType = 'n';
                        int valueIndex = -1;
                        String value = "";

                        String outputLine = "";

                        outputLine = line;

                        if(outputLine.contains("식") == true)
                        {
                            sikType = outputLine.charAt(line.length() - 1);
                            int sinIndex = outputLine.indexOf("식");
                            outputLine = outputLine.substring(sinIndex + 1, outputLine.length() - 1);   // '삭' 과 끝문자 제외한 나머지 문자열 저장

                            // 남은 outputLine 은 어 또는 ., 곱셈의 반복
                        }

                        if(outputLine.contains("엄") == true)    // '엄' 이 나오는 경우 val[num] = 을 출력
                        {
                            result = findUmm(outputLine);
                            int ummIndex = outputLine.indexOf("엄");
                            outputLine = outputLine.substring(ummIndex + 1);    // (string)엄 까지 모두 자르기  ex) 어어엄어어 -> 어어
                        }

                        // 어 또는 , . 공백 이 나오는 경우 값 게산
                        value = calcComa(outputLine);

                        if(result.length() > 0 && value == "")
                            value = "0";

                        result += value;


                        if(sikType != 'n')  // '식'이 존재할 경우
                        {
                            int sikValue = calcComa_forSik(outputLine);
                            result = sikInstruction(sikType, sikValue);
                        }

                    }
                    writer.write("//" + line + "\n");
                    writer.write(result + "; \n");
                }
            }
        } catch (IOException e) {
            System.out.printf("파일 열리지 않음\n");
        }


        writer.close();

    }
}

/*

    0. 파일 생성 및 기본 include 세팅 필요

        #include <stdio.h>
        int main() {            까지

        "어떻게" 를 기준으로 시작

       자료형은 정수만 -> default = 0
        식의 기본 형식은
        scanner Line 사용

        - 값이 처음 나온 경우 int 로 선언해야함
            -> static으로 그냥 3개 선언
            (배열로 만들어서 엄, 어엄, 어어엄 에 따라 1, 2, 3 배열에 접근하도록

        - 식 (value) ㅋ

        - 식 (value) !

        - 식?

            1. value를 읽는 함수

            2.




    1. . , " "(공백)을 읽는 함수
    2. 어어..엄 -> 엄이 나올 때 까지 어어 개수를 읽는 함수 (
        엄이 없다면 load, 아니라면 store
            (어*) 엄 (어* 또는 .*)
    3. 식?(입력) 식!(출) 판별     (value) [식? 또는 식!] (value) ㅋ
    4.  식의 끝은 세미콜론 대신 \n (공백은 곱셈으로 들어감)





     "이 사람이름이냐ᄏᄏ"
       나올 경우 return 0;



       선언 -> 전역으로 선언해두기 때문에 필요없음
       출력
       입력

       ++, -- 등 사칙연산


        '어' 가 나올 때 뒤에 '엄'이 있다면 선언, 없다면 '어' 개수의 index를 갖는 변수

        - '어'가 나오는 경우, 어 뒤에
                    엄               -> n번째 변수에 뒤의 값 저장  = 연산
                    , .             -> n번쨰 변수에 값 더하거나 빼기 -> += 연산
                    아무것도 없는 경우  -> 곱샘
                    총 3가지 경우

        - '식' 이 나오는 경우 가장 마지막에
                    ㅋ           %c 출력
                    !           %d 출력
                    ?           입력
                    총 3가지 경우


    1. 처음에 전체를 탐색하며 '식' 이 있는지 확인
        1-1. '식'의 끝에 무엇이 오는지 확인

    2. '식'과 끝값을 제외한 나머지의 연산 진행

 */