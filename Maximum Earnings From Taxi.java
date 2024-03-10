class Solution {
    class SegmentTree {
        class Node {
            int l, r;
            long max;
            public Node(int l, int r) {
                this.l = l;
                this.r = r;
                max = -1;
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
                tr[u].max = Math.max(tr[left].max, tr[right].max);
            }
        }
        
        public void update(int u, int index, long val) {
            if(tr[u].l == tr[u].r) {
                tr[u].max = Math.max(tr[u].max, val);
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
                return tr[u].max;
            }
            int mid = l + (r - l) / 2;
            int left = u * 2 + 1, right = u * 2 + 2;
            if(e <= mid){
                return query(left, s, e);
            } else if(s >= mid + 1) {
                return query(right, s, e);
            } else {
                return Math.max(query(left, s, mid), query(right, mid + 1, e));
            }
        }
    }
    
    public long maxTaxiEarnings(int n, int[][] a) {
        Arrays.sort(a, (x, y) -> {
            return x[0] - y[0];
        });
        
        SegmentTree seg = new SegmentTree(n + 1);
        seg.update(0, 0, 0);
        long[] dp = new long[a.length];
        for(int i = 0; i < a.length; i++) {
            int w = a[i][1] - a[i][0] + a[i][2];
            dp[i] = seg.query(0, 0, a[i][0]) + w;
            seg.update(0, a[i][1], dp[i]);
        }
        
        long ans = 0;
        for(long x : dp) ans = Math.max(ans, x);
        return ans;
    }
    
}
