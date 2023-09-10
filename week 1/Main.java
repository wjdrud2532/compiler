// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


//###############################
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;      // 파일 읽기
import java.io.FileWriter;      // 파일 쓰기
import java.io.BufferedWriter;
import java.io.IOException;

// ######################################################
// ###########      파일 경로 설정은 130번 줄에        ########
// ######################################################

public class Main
{
    static String startOutput = "#include <stdio.h>\n\n" +
            "int main() \n{\n" +
            "\t int val[3] = {0, 0, 0}; \n";

    static String endOutput = "return 0;\n" +
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
        int cnt = 0;
        int ummIndex = str.indexOf("엄");

        for(int i = ummIndex - 1; i >= 0; i --)
        {
            if(str.charAt(i) == '어') cnt ++;
            else                     break;
        }

        allocaValue = "val[" + cnt + "] = ";

        return allocaValue;
    }

    public static String calcComa(String str)   // 어 . , 계산 값 반환
    {
        char lastChar = 'n';
        String value = "";

        for(int i = 0; i < str.length();    )
        {
            if(i > 0 && str.charAt(i - 1) != str.charAt(i)) // 새로운 연산이 필요한 경우
            {
                if(str.charAt(i) == ' ')        // * 추가 후 i ++로 ' ' 넘어감
                {
                    value += " * ";
                    i ++;
                }
                else
                    value += " + ";
            }

            if(str.charAt(i) == '어')
            {
                int tempCnt = -1;       // 변수 index 이므로 0부터 시작하기 위해 -1로 초기화
                while(true)
                {
                    if(str.charAt(i) == '어') tempCnt ++;
                    else                      break;

                    i ++;
                    if(i >= str.length())   break;
                }

                value += "val[" + tempCnt + "]";
            }

            else if(str.charAt(i) == ',' || str.charAt(i) == '.')
            {
                int tempCnt = 0;                // 직접 대입이므로 0으로 초기화

                while(true)
                {
                    if(str.charAt(i) == '.')      tempCnt ++;
                    else if(str.charAt(i) == ',') tempCnt --;

                    i ++;

                    if(i >= str.length())    break;
                    if(str.charAt(i) == ' ') break;
                }

                value +=  "(" + tempCnt + ")";
            }
        }
        return value;
    }

    public static String sikInstruction(char sikType, String indexOrAskiNum) // 출력은 변수 또는 아스키코드 두가지로 나눠야함
    {
        String printfInstruction = "\tprintf(\"%";     // printf("%
        if (sikType == '!')
            printfInstruction += "d\", " + indexOrAskiNum + "); \n";      // printf("%d", val[1]);

        else if (sikType == 'ᄏ')    // 아스키 코드를 출력해야 하는 경우
            printfInstruction += "c\", " + indexOrAskiNum + "); \n";            // printf("%c", 1);

        else if(sikType == '?')   // ? (입력) 일 경우
        {
            if(indexOrAskiNum.equals(""))            indexOrAskiNum = "val[0]";
            else if(indexOrAskiNum.equals("val[0]")) indexOrAskiNum = "val[1]";
            else                                     indexOrAskiNum = "val[2]";

            printfInstruction = "\tscanf(\"%d\", &" + indexOrAskiNum + "); \n";   // scanf("%d", val[1]);
        }

        return printfInstruction;
    }

    public static void main(String[] args) throws IOException
    {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        // ######################################################
        // ###########      파일 경로 설정                  ########
        File file = new File("test/test.umm");
        File output = new File("outputCFile/test.c");
        // ######################################################
        // ######################################################

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

                    // 시작 include 선언
                    if(line.equals("어떻게"))                  result = startOutput;

                    // 종료 return
                    else if(line.equals("이 사람이름이냐ᄏᄏ"))   result = endOutput;

                    else
                    {
                        char sikType = 'n';     // '식' 이 존재할 경우 ! , ㅋ 의 타입 저장
                        int valueIndex = -1;    // 변수의 index
                        String value = "";      //

                        String outputLine = "";

                        outputLine = line;

                        if(outputLine.contains("식") == true)    // '식' 등장시
                        {
                            sikType = outputLine.charAt(line.length() - 1); // ! , ㅋ 저장
                            int sinIndex = outputLine.indexOf("식");        // '식' 의 위치
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

                        // '식'이 존재할 경우
                        if(sikType != 'n')  result = sikInstruction(sikType, value);
                    }

                    writer.write("\t"+ result + "; \n");
                }
            }
        } catch (IOException e) {
            System.out.printf("파일 열리지 않음\n");
        }

        writer.close();

    }
}