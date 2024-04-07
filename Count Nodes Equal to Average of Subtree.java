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
    int n;
    Map<TreeNode, Integer> ids;
    Map<Integer, TreeNode> rev;
    
    Map<TreeNode, Integer> sz;
    int id = 0;
    int[] p;
    public int averageOfSubtree(TreeNode root) {
        n = count(root);
        ids = new HashMap<>();
        sz = new HashMap<>();
        rev = new HashMap<>();
        dfs(root);
        
        p = new int[n];
        for(int i = 0; i < n; i++) {
            p[i] = rev.get(i).val;
            if(i > 0) p[i] += p[i - 1];
        }
        
        int ans = 0;
        for(int i = 0; i < n; i++) {
            TreeNode node = rev.get(i);
            int l = ids.get(node), r = l + sz.get(node) - 1;
            int s = p[r] - (l == 0 ? 0 : p[l - 1]);
            int count = r - l + 1;
            if(s / count == node.val) ans++;
        }
        return ans;
    }
    
    public int count(TreeNode root) {
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
    
     public int dfs(TreeNode root) {
        if(root == null) return 0;
        rev.put(id, root);
        ids.put(root, id++);
        int size = 1;
        size += dfs(root.left);
        size += dfs(root.right);
        sz.put(root, size);
        return size;
    }
}
