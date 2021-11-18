public class StringMatching {

    public static void main(String[] args) {
        System.out.println(stringMatching("Just using a naive string matching approach", "string matching"));
    }

    public static Integer stringMatching(String string, String subString) {
        for (int n =  0; n < string.length() - subString.length() + 1; n++) { // n potenzielle Startposition
            boolean match = true;
            for (int k = 0; k < subString.length(); k++) { // k Zeichen, die verglichen werden müssen
                if (string.charAt(n + k) != subString.charAt(k)) {
                    match = false;
                }
                System.out.println("n, k: " + n + ", " + k);
            }
            if (match) {
                return n;
            }
        }
        return null;
    }

}
