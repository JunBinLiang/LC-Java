class Solution {
    int[] cnt1;
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        cnt1 = new int[26];
        for(char c : letters) {
            cnt1[c - 'a']++;
        }
        
        int n = words.length;
        int ans = 0;
        for(int s = 0; s < (1 << n); s++) { //O(2 ^ n)
            int[] cnt2 = new int[26];
            for(int i = 0; i < n; i++) { //O(n)
                if((s & (1 << i)) > 0) {
                    String word = words[i];
                    for(int j = 0; j < word.length(); j++) { //O(m)
                        char c = word.charAt(j);
                        cnt2[c - 'a']++;
                    }
                    
                }
            }
            ans = Math.max(ans, getScore(cnt2, score));
        }
        return ans;
    }
    
    public int getScore(int[] cnt2, int[] score) {
        int sum = 0;
        for(int i = 0; i < 26; i++) {
            if(cnt2[i] > cnt1[i]) {
                return 0;
            }
            sum += cnt2[i] * score[i];
        }
        return sum;
    }
    
}

//1. generate all subsets of words. 
//2. Check if we can assign enough letters to each subset.
//3. If yes, calculate the score we can earn.
