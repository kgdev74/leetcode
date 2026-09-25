import java.util.*;

public class solution1096 {

    static int index;

    // expression = term { , term }
    static Set<String> parseExpression(String s) {

        Set<String> result = new TreeSet<>();

        result.addAll(parseTerm(s));

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip comma
            result.addAll(parseTerm(s));
        }

        return result;
    }

    // term = factor factor factor ...
    static Set<String> parseTerm(String s) {

        Set<String> result = new TreeSet<>();

        // Empty string is required for concatenation
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> current = parseFactor(s);

            Set<String> temp = new TreeSet<>();

            // Concatenate every string
            // from result with every string from current
            for (String a : result) {
                for (String b : current) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // factor = character OR { expression }
    static Set<String> parseFactor(String s) {

        Set<String> result = new TreeSet<>();

        if (s.charAt(index) == '{') {

            index++; // skip {

            result = parseExpression(s);

            index++; // skip }

        } else {

            result.add(String.valueOf(s.charAt(index)));

            index++;
        }

        return result;
    }

    public static List<String> braceExpansionII(String expression) {

        index = 0;

        Set<String> result = parseExpression(expression);

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter expression: ");

        String expression = sc.nextLine();

        List<String> answer = braceExpansionII(expression);

        System.out.println("Output: " + answer);

        sc.close();
    }
}
