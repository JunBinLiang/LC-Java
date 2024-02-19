class Solution {
    public int find(int[] nums, int u) {
        return nums[u] == u ? u : 0 + (nums[u] = find(nums, nums[u]));
    }
    
    public int latestDayToCross(int n, int m, int[][] cells) {
        int[] dx8 = new int[]{1, -1, 0, 0, -1, 1, -1, 1};
        int[] dy8 = new int[]{0, 0, -1, 1, -1, -1, 1, 1};
        
        int[] nums = new int[n * m + 2];
        for(int i = 0; i < n * m + 2; i++) {
            nums[i] = i;
        }
        
        int p1 = n * m, p2 = n * m + 1;
        for(int i = 0; i < n; i++) {
            int r1 = find(nums, p1), r2 = find(nums, i * m + 0);
            if(r1 != r2) nums[r1] = r2;
        }
        
        for(int i = 0; i < n; i++) {
            int r1 = find(nums, p2), r2 = find(nums, i * m + m - 1);
            if(r1 != r2) nums[r1] = r2;
        }
        
        
        
        int[][] mat = new int[n][m];
        int ans = 0;
        for(int i = 0; i < cells.length; i++) {
            int r = cells[i][0] - 1, c = cells[i][1] - 1;
            mat[r][c] = 1;
            for(int x = 0; x < 8; x++) {
                int rr = dx8[x] + r, cc = dy8[x] + c;
                if(rr < 0 || rr >= n || cc < 0 || cc >= m || mat[rr][cc] == 0) continue;
                int root1 = find(nums, r * m + c); int root2 = find(nums, rr * m + cc);
                if(root1 != root2) {
                    nums[root1] = root2;
                }
            }

            if(find(nums, p1) == find(nums, p2)) { //check left side can reach right side
                break;
            }
            ans = i + 1;
        }
        return ans;
    }
}
