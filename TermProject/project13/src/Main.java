import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//        System.out.println("Hello world!");

        tinyPythonLexer lexer = new tinyPythonLexer(CharStreams.fromFileName("Test.tpy"));
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        tinyPythonParser parser = new tinyPythonParser( tokens );
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new MiniPrintListener(), tree);   // 출력
    }
}

/*

- 수정함 : 함수 안의 변수는 load 0으로 통일, 인자 하나뿐이니 다른 것을 고려할 필요 없음
- print나 +, -  연산 시 함수 return으로 계산하는 것 추가
- 괄호 연산 확인(alloca, print, return의 queue 를 탐색할 때,
    임시 큐륾 만들어 (, ) 를 전부 삭제한 큐를 만든다. (덧셈과 뺄셈에서 괄호 의미 없으니)

- break, continue

 */