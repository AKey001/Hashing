

public class RabinKarp {
    public final static int b = 256;

    static void search(String pattern, String text, int q) {
        int k = pattern.length();
        int n = text.length();
        int i, j; // counting vars
        int patternHash = 0; // hash value pattern
        int textHash = 0; // hash value for text
        int h = 1; // "char weighting" inside rolling hash

        // vergleichbar mit h = b^k-1
        for (i = 0; i < k - 1; i++) {
            h = (h * b) % q;
        }

        // Calculate hash value of pattern
        // and first substring of text
        for (i = 0; i < k; i++) {
            patternHash = (b * patternHash + pattern.charAt(i)) % q;
            textHash = (b * textHash + text.charAt(i)) % q;
        }

        // Iterate through the text
        for (i = 0; i < n - k + 1; i++) {

            // Check the hash values of current substring of text
            // and pattern.
            // If the hash values match, check for chars one by one
            if (patternHash == textHash) {
                /* Check for chars one by one */
                for (j = 0; j < k; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }

                // if patternHash == textHash and pattern[0...k-1] = txt[i, i+1, ...i+k-1]
                if (j == k) System.out.println("Pattern found at index " + i);
            }

            // Calculate hash value for next window of text:
            // Remove first digit, add new last digit
            if (i < n - k) {
                textHash = ((textHash - text.charAt(i) * h) * b + text.charAt(i + k)) % q;

                // We might get negative value of textHash,
                // converting to positive
                if (textHash < 0)
                    textHash = (textHash + q);
            }
        }
    }

    public static void main(String[] args) {
        String txt = "Lorem ipsum dolor sit amet, ipsum sed diam nonumy eirmod";
        String pat = "ipsum";
        int q = 113; // A prime number
        search(pat, txt, q);
    }
}


