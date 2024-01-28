class Node {
    Node[] next;
    public Node() {
        next = new Node[2];
    }
}

class Solution {
    Node root;
    public int findMaximumXOR(int[] nums) {
        root = new Node();
        int maxXor = 0;
        add(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            maxXor = Math.max(maxXor, search(nums[i]));
            add(nums[i]);
        }
        return maxXor;
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
