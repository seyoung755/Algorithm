package 아더;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Arrays;

class Pro_72411_메뉴리뉴얼 {
    static HashMap<String, Integer> map;
    static int m;

    public static void main(String[] args) {
        Pro_72411_메뉴리뉴얼 pro = new Pro_72411_메뉴리뉴얼();

        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        String[] orders3 = {"XYZ", "XWY", "WXA"};

        int[] course1 = {2, 3, 4};
        int[] course2 = {2, 3, 5};
        int[] course3 = {2, 3, 4};

        System.out.println(Arrays.toString(pro.solution(orders1, course1)));
        System.out.println(Arrays.toString(pro.solution(orders2, course2)));
        System.out.println(Arrays.toString(pro.solution(orders3, course3)));
    }

    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            m = 0;
            for (int j = 0; j < orders.length; j++) {
                find(0, "", course[i], 0, orders[j]);
            }
            for (String s : map.keySet()) {
                if (map.get(s) == m && m > 1) {
                    pq.offer(s);
                }
            }
        }
        String ans[] = new String[pq.size()];
        int k = 0;
        while (!pq.isEmpty()) {
            ans[k++] = pq.poll();
        }
        return ans;
    }

    static void find(int cnt, String str, int targetNum, int idx, String word) {
        if (cnt == targetNum) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String temps = "";
            for (int i = 0; i < c.length; i++) temps += c[i];
            map.put(temps, map.getOrDefault(temps, 0) + 1);
            m = Math.max(m, map.get(temps));
            return;
        }
        for (int i = idx; i < word.length(); i++) {
            char now = word.charAt(i);
            find(cnt + 1, str + now, targetNum, i + 1, word);
        }
    }
}
