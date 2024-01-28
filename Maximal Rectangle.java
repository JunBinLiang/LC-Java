class Solution {
    public int maximalRectangle(char[][] mat) {
        int n = mat.length, m = mat[0].length;
        int res = 0;
        int[] p = new int[m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == '0') p[j] = 0;
                else p[j]++;
            }
            res = Math.max(res, largestRectangleArea(p));
        }
        return res;
    }
    
    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stk = new Stack<>(); //[min, count]
        int res = 0;
        for(int i = 0; i < heights.length; i++) {
            int count = 0;
            while(stk.size() > 0 && heights[i] <= stk.peek()[0]) {
                int[] p = stk.pop();
                count += p[1];
                res = Math.max(res, count * p[0]);
            }
            stk.push(new int[]{heights[i], count + 1});
        }
        
        int count = 0;
        while(stk.size() > 0) {
            int[] p = stk.pop();
            count += p[1];
            res = Math.max(res, count * p[0]);
        }
        return res;
    }
}
