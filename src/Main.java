import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String observation = """
            Utilize espaços entre números e operadores, e pontos
            no lugar de virgulas para números decimais.
            """;

    public static void main(String[] args) {
        System.out.println(observation);
        System.out.print("Digite a expressão matemática desejada: ");

        String expression = scanner.nextLine();

        ArrayList<String> lexemes = Lexer.stringSplitter(expression);
        Lexer.lexer(lexemes);
    }
}