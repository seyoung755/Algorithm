import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word1 = br.readLine();
        String word2 = br.readLine();

        int[][] dp = new int[word1.length()][word2.length()];

        int max = 0;

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {

                boolean isSame = word1.charAt(i) == word2.charAt(j);

                if (isSame) {
                    int sameCount = 1;

                    if (i > 0 && j > 0) {
                        sameCount = dp[i-1][j-1] + 1;
                    }

                    dp[i][j] = sameCount;

                    if (sameCount > max) max = sameCount;
                }
            }
        }

        System.out.println(max);
    }
}