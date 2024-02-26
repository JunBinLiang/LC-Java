class Solution {
    public int minFallingPathSum(int[][] mat) {
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(i > 0) {
                    int min = mat[i - 1][j];
                    if(j - 1 >= 0) {
                        min = Math.min(min, mat[i - 1][j - 1]);
                    }

                    if(j + 1 < mat.length) {
                        min = Math.min(min, mat[i - 1][j + 1]);
                    }
                    mat[i][j] += min;   
                }
                if(i == mat.length - 1) ans = Math.min(ans, mat[i][j]);
            }
        }
        return ans;
    }
}
