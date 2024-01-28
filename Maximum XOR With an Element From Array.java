class Node {
    Node[] next;
    public Node() {
        next = new Node[2];
    }
}


class Solution {
    Node root;
    public int[] maximizeXor(int[] nums, int[][] q) {
        root = new Node();
        Arrays.sort(nums);
        int[] ans = new int[q.length];
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < q.length; i++) {
            list.add(new int[]{q[i][0], q[i][1], i});
        }
        
        Collections.sort(list, (x, y) -> {
            return x[1] - y[1];
        });
        
        int j = 0;
        for(int i = 0; i < list.size(); i++) {
            int[] p = list.get(i);
            int x = p[0], m = p[1], index = p[2];
            while(j < nums.length && nums[j] <= m) {
                add(nums[j]);
                j++;
            }
            
            if(j == 0) { //trie has nothing
                ans[index] = -1;
            } else {
                ans[index] = search(x);
            }
        }
        return ans;
    }
    
    public void add(int x) {
        Node u = root;
        for(int i = 31; i >= 0; i--) {
            int bit = 0;
            if((x & (1 << i)) > 0) { //bit is 1
                bit = 1;
            }
            if(u.next[bit] == null) {
                u.next[bit] = new Node();
            }
            u = u.next[bit];
        }
    }
    
    public int search(int x) {
        // maximize x ^ (nums[0] - nums[i - 1])
        Node u = root;
        int res = 0;
        for(int i = 31; i >= 0; i--) {
            int bit = 0;
            if((x & (1 << i)) > 0) { //bit is 1
                bit = 1;
            }
            
            if(bit == 1) { //find 0
                if(u.next[0] != null) {
                    res += (1 << i);
                    u = u.next[0];
                } else {
                    u = u.next[1];
                }
            } else { // find 1
                if(u.next[1] != null) {
                    res += (1 << i);
                    u = u.next[1];
                } else {
                    u = u.next[0];
                }
            }
        }
        return res;
    }
}
