class Solution {
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        boolean[] has = new boolean[26];
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        Stack<Character> stk = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while(stk.size() > 0 && c <= stk.peek() && count[stk.peek() - 'a'] > 0 && !has[c - 'a']) has[stk.pop() - 'a'] = false;
            
            if(!has[c - 'a']) {
                has[c - 'a'] = true;
                stk.push(c);
            }
            count[c - 'a']--;
        }
        
        StringBuilder str = new StringBuilder();
        while(stk.size() > 0) str.append(stk.pop());
        return str.reverse().toString();
    }
}
