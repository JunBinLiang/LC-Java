class Solution {
    final int INF = 10000;
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}, {0, 0}};
    public int minFlips(int[][] mat) {
        int n = mat.length, m = mat[0].length, size = n * m;
        int ans = INF;
        for(int s = 0; s < (1 << size); s++) {
            int[][] newmat = new int[n][m];
            copy(newmat, mat);
            for(int i = 0; i < size; i++) {
                if((s & (1 << i)) > 0) {
                    int r = i / m, c = i % m;
                    flip(newmat, r, c, s);
                }
            }
            
            if(allzero(newmat)) {
                ans = Math.min(ans, Integer.bitCount(s));
            }
        }
        return ans == INF? -1 : ans;
    }
    
    public void copy(int[][] a, int[][] b) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                a[i][j] = b[i][j];
            }
        }
    }
    
    public void flip(int[][] a, int r, int c, int s) {
        int n = a.length, m = a[0].length;
        for(int i = 0; i < 5; i++) {
            int nr = r + dir[i][0], nc = c + dir[i][1];
            if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                a[nr][nc] ^= 1;
            }
        }
    }
    
    public boolean allzero(int a[][]) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(a[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
