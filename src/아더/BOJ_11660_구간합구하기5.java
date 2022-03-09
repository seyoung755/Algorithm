package 아더;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11660_구간합구하기5 {

    static int N, M;
    static long[][] DP;
    static Queue<Integer> target;

    public static void main(String[] args) {
        try {
            input();
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solve() {
        // 정답의 최대치는?
        // 최대 1024 X 1024 배열, 각 요소는 1_000까지 가능
        // 100_000번의 연산으로 (1_000 X 1_024) X 100_000 = 102_400_000_000 -> Integer 불가능 -> Long 사용
        StringBuilder sb = new StringBuilder();

        while (!target.isEmpty()) {
            int x1 = target.poll();
            int y1 = target.poll();
            int x2 = target.poll();
            int y2 = target.poll();

            sb.append(DP[x2][y2] - DP[x1 - 1][y2] - DP[x2][y1 - 1] + DP[x1 - 1][y1 - 1] + "\n");
        }

        System.out.println(sb);
    }

    private static void input() throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\k2j38\\OneDrive\\Desktop\\Park\\99.ETC\\input.txt")));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        DP = new long[N + 1][N + 1];
        target = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            split = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                DP[i][j] = DP[i - 1][j] + DP[i][j - 1] - DP[i - 1][j - 1] + Long.parseLong(split[j - 1]);
            }
        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                target.add(Integer.parseInt(split[j]));
            }
        }
    }
}
