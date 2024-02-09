class Solution {
    public int find(int[] nums, int u) {
        return nums[u] == u ? u : 0 + (nums[u] = find(nums, nums[u]));
    }
    
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int largestIsland(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int ans = 0;
        int[] nums = new int[n * m];
        int[] sz = new int[n * m];
        for(int i = 0; i < n * m; i++) {
            nums[i] = i; sz[i] = 1;
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 1) {
                    for(int[] p : dir) {
                        int r = i + p[0], c = j + p[1];
                        if(r < 0 || r >= n || c < 0 || c >= m || mat[r][c] == 0) continue;
                        int root1 = find(nums, i * m + j), root2 = find(nums, r * m + c);
                        if(root1 != root2) {
                            nums[root1] = root2;
                            sz[root2] += sz[root1];
                        }
                    }
                    ans = Math.max(ans, sz[find(nums, i * m + j)]);
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int sum = 1;
                    for(int[] p : dir) {
                        int r = i + p[0], c = j + p[1];
                        if(r < 0 || r >= n || c < 0 || c >= m || mat[r][c] == 0) continue;
                        int root = find(nums, r * m + c);
                        if(!set.contains(root)) {
                            set.add(root);
                            sum += sz[root];
                        }
                    }
                    ans = Math.max(ans, sum);
                }
            }
        }
        
        return ans;
    }
}
