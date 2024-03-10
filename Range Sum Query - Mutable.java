class NumArray {
    class SegmentTree {
        class Node {
            int l, r;
            long sum;
            public Node(int l, int r) {
                this.l = l;
                this.r = r;
                sum = 0;
            }
        }
        
        Node[] tr;
        public SegmentTree(int n) {
            tr = new Node[n * 4 + 100];
            tr[0] = new Node(0, n);
            build(0);
        }
        
        public void build(int u) {
            int l = tr[u].l, r = tr[u].r;
            if(l == r) return;
            int mid = l + (r - l) / 2;
            int left = u * 2 + 1, right = u * 2 + 2;
            tr[left] = new Node(l, mid);
            tr[right] = new Node(mid + 1, r);
            build(left);
            build(right);
        }
        
        public void pushdown(int u) {
            
        }
        
        public void pushup(int u) {
            if(tr[u].l != tr[u].r) {
                int left = u * 2 + 1, right = u * 2 + 2;
                tr[u].sum = tr[left].sum + tr[right].sum;
            }
        }
        
        public void update(int u, int index, long val) {
            if(tr[u].l == tr[u].r) {
                tr[u].sum = val;
                return;
            }
            int l = tr[u].l, r = tr[u].r;
            int mid = l + (r - l) / 2;
            if(index <= mid) {
                update(u * 2 + 1, index, val);
            } else {
                update(u * 2 + 2, index, val);
            }
            pushup(u);
        }
        
        public long query(int u, int s, int e) {
            int l = tr[u].l, r = tr[u].r;
            if(l == s && r == e){
                return tr[u].sum;
            }
            int mid = l + (r - l) / 2;
            int left = u * 2 + 1, right = u * 2 + 2;
            if(e <= mid){
                return query(left, s, e);
            } else if(s >= mid + 1) {
                return query(right, s, e);
            } else {
                return query(left, s, mid) + query(right, mid + 1, e);
            }
        }
    }
    
    SegmentTree seg;
    public NumArray(int[] nums) {
        int n = nums.length;
        seg = new SegmentTree(n);
        for(int i = 0; i < n; i++) seg.update(0, i, nums[i]);
    }
    
    public void update(int index, int val) {
        seg.update(0, index, val);
    }
    
    public int sumRange(int left, int right) {
        return (int)(seg.query(0, left, right));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
