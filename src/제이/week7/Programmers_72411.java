import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        Set<String> courseSet = new HashSet<>();

        // 제일 긴 메뉴가 두개 있을 수도 있음...
        // 그러면 제일 긴 메뉴들 확인해서 반복문 돌아야 하는지..?
        String longestOrder = Arrays.stream(orders)
                .sorted((a, b) -> b.length() - a.length())
                .findFirst()
                .get();

        for (int c : course) {
            Map<String, Integer> orderedCounts = new HashMap<>();
            List<String> combinations = new ArrayList<>();
            int n = longestOrder.length();
            boolean[] visited = new boolean[n];

            generateCombinations(combinations, longestOrder.toCharArray(), visited, 0, n, c);

            int maxCount = 0;

            for (String combination : combinations) {
                for (int i = 0; i < combination.length(); i++) {
                    String menu = combination.substring(i, i+1);
                    
                    for (String order : orders) {
                        if (order.contains(menu)) {
                            int count = orderedCounts.getOrDefault(combination, 0) + 1;
                            orderedCounts.put(combination, count);

                            if (maxCount < count) maxCount = count;
                        }
                    }
                }
            }

            for (String key : orderedCounts.keySet()) {
                if (orderedCounts.get(key) == maxCount) courseSet.add(key);
            }
        }

        answer = new String[courseSet.size()];

        int i=0;
        for (String s : courseSet) {
            answer[i] = s;
            i++;
        }

        Arrays.sort(answer);

        return answer;
    }

    private static void generateCombinations(List<String> combinations, char[] menuArr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<visited.length; i++) {
                if (visited[i]) {
                    sb.append(menuArr[i]);
                }
            }
            combinations.add(sb.toString());
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            generateCombinations(combinations, menuArr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}