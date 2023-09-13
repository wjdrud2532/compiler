import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;      // 파일 읽기
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.StreamSupport;

public class Main {

    public static void printResult(boolean isMatch, String str, String type)
    {
        if(isMatch)
            System.out.print("Match : The input string is [" + str + "] Matched Type is [" + type + "]\n");
        else
            System.out.print("Error : The input does not belong to the given string type [" + str + "]\n");
    }

    public static void main(String[] args) {

        //############################################################
        //####################              경로 설정            #######
        File file = new File("./test/input.txt");
        //####################                                 #######
        //############################################################

        String p_PhoneNumber = "\\d{3}-\\d{4}-\\d{4}$"; // 숫자 3개 + 4개 + 4개
        String p_Email = "\\w+@(\\w+\\.\\w+)+";         // 문자열 + @ + .문자열 반복
        String p_PhoneType = "(iPhone|Galaxy)\\s\\d{1,3}";  // 아이폰 또는 갤럭시 + 숫자 1~3 사이
        String p_FileType = "\\w+.(c|java|py|ml)";          // 문자열 + . (파일종류 4가지)

        String[] p_gather = new String[] { p_PhoneNumber, p_Email, p_PhoneType, p_FileType};
        String[] printType = new String[] {"Phone Number", "e-mail", "Phone Type", "Source File"};

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    boolean isPrint = false;
                    for(int i = 0; i < p_gather.length; i ++)
                    {
                        if((isPrint = Pattern.matches(p_gather[i], line)))  // 일치하는 패턴이 존재하는 경우
                        {
                            printResult(true, line, printType[i]);  // 출력 후
                            isPrint = true;
                            break;                                          // 종료
                        }
                    }

                    if(isPrint == false)
                        printResult(false, line, "");
                }
            }
        } catch (IOException e) {
            System.out.printf("파일 열리지 않음\n");
        }

    }
}