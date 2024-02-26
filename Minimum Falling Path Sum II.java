class Solution {
    public int minFallingPathSum(int[][] mat) {
        int ans = Integer.MAX_VALUE;
        int[] left = new int[mat[0].length];
        int[] right = new int[mat[0].length];
        
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(i > 0) {
                    int min = Integer.MAX_VALUE;
                    if(j - 1 >= 0) min = Math.min(min, left[j - 1]);
                    if(j + 1 < mat[0].length) min = Math.min(min, right[j + 1]);
                    mat[i][j] += min;
                }
                
                if(i == mat.length - 1) ans = Math.min(ans, mat[i][j]);
            }
            
            for(int j = 0; j < mat[0].length; j++) {
                left[j] = mat[i][j];
                if(j - 1 >= 0) left[j] = Math.min(left[j], left[j - 1]); 
            }
            
            for(int j = mat[0].length - 1; j >= 0; j--) {
                right[j] = mat[i][j];
                if(j + 1 < mat[0].length) right[j] = Math.min(right[j], right[j + 1]); 
            }
        }
        
        return ans;
    }
}
