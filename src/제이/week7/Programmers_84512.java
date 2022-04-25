class Programmers_84512 {
    public int solution(String word) {
        int answer = 0;
        int[] arr = new int[] { 781, 156, 31, 6, 1};
        String str = "AEIOU";

        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            answer += arr[i] * str.indexOf(c) + 1;
        }

        return answer;
    }
}