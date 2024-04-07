/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int id = 0, n;
    int[] ids; 
    int[] sz;
    int[] h;
    public int[] treeQueries(TreeNode root, int[] q) {
        n = count(root);
        ids = new int[n + 1];
        sz = new int[n + 1];
        h = new int[n + 1];
        
        dfs(root);
        getH(root, 0);
        
        int[] lmax = new int[n + 1];
        int[] rmax = new int[n + 1];
        for(int i = 0; i < h.length; i++) {
            lmax[i] = h[i];
            if(i > 0) lmax[i] = Math.max(lmax[i], lmax[i - 1]);
        }
        for(int i = h.length - 1; i >= 0; i--) {
            rmax[i] = h[i];
            if(i + 1 < h.length) rmax[i] = Math.max(rmax[i], rmax[i + 1]);
        }
        
        int[] ans = new int[q.length];
        for(int i = 0; i < q.length; i++) {
            int l = ids[q[i]], r = l + sz[q[i]] - 1;
            if(l - 1 >= 0) ans[i] = Math.max(ans[i], lmax[l - 1]);
            if(r + 1 < h.length) ans[i] = Math.max(ans[i], rmax[r + 1]);
        }
        return ans;
    }
    
    public int count(TreeNode root) {
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
    
    public int dfs(TreeNode root) {
        if(root == null) return 0;
        ids[root.val] = id++;
        sz[root.val] = 1;
        sz[root.val] += dfs(root.left);
        sz[root.val] += dfs(root.right);
        return sz[root.val];
    }
    
    public void getH(TreeNode root, int height) {
        if(root == null) return;
        h[ids[root.val]] = height;
        getH(root.left, height + 1);
        getH(root.right, height + 1);
    }
}
