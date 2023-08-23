import java.util.ArrayList;

public class Lexer {
    public static void lexer(ArrayList<String> lexemes) {
        for (String l : lexemes) {
            String TYPE = "LEXEME: %s\tTYPE: %s\n";
            boolean isNumber = l.matches("^[0-9.]*$");

            if (l.matches("^[a-zA-Z]*$")) {
                System.out.printf("ERRO: CARACTERE INVÁLIDO: %s\n", l);
                continue;
            }

            if (isNumber) {
                if (checkIfInt(l)) {
                    System.out.printf(TYPE, l, "NATURAL");
                } else if (checkIfDouble(l)) {
                    System.out.printf(TYPE, l, "REAL");
                }
            } else {
                switch (l) {
                    case "+" -> System.out.printf(TYPE, l, "OPERADOR SOMA");
                    case "-" -> System.out.printf(TYPE, l, "OPERADOR SUBTRAÇÃO");
                    case "*" -> System.out.printf(TYPE, l, "OPERADOR MULTIPLICAÇÃO");
                    case "/" -> System.out.printf(TYPE, l, "OPERADOR DIVISÃO");
                    case "(" -> System.out.printf(TYPE, l, "ABRE PARÊNTESES");
                    case ")" -> System.out.printf(TYPE, l, "FECHA PARÊNTESES");
                    default -> System.out.printf("ERRO: FALTAM ESPAÇOS ENTRE OPERANDO E OPERADOR: %s\n", l);
                }
            }
        }
    }

    public static ArrayList<String> stringSplitter(String expression) {
        ArrayList<String> lexemes = new ArrayList<>();
        StringBuilder lexeme = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') {
                lexeme = addToLexemes(
                        lexeme,
                        lexemes
                );

            } else if (expression.charAt(i) == '(') {
                lexeme.append(expression.charAt(i));
                lexeme = addToLexemes(
                        lexeme,
                        lexemes
                );

            } else if (i + 1 < expression.length() && expression.charAt(i + 1) == ')') {
                lexeme.append(expression.charAt(i));
                lexeme = addToLexemes(
                        lexeme,
                        lexemes
                );

            } else {
                lexeme.append(expression.charAt(i));
            }
        }
        lexemes.add(lexeme.toString());

        return lexemes;
    }

    public static StringBuilder addToLexemes(StringBuilder lexeme, ArrayList<String> lexemes) {
        lexemes.add(lexeme.toString());
        return new StringBuilder();
    }

    public static boolean checkIfInt(String lexeme) {
        try {
            Integer.parseInt(lexeme);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkIfDouble(String lexeme) {
        try {
            Double.parseDouble(lexeme);
            return true;
        } catch (NumberFormatException e) {
            System.out.printf("ERRO: NÚMERO REAL INVÁLIDO: %s\n", lexeme);
            return false;
        }
    }
}
