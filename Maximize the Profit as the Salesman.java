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
    
    public int maximizeTheProfit(int n, List<List<Integer>> a) {
        Collections.sort(a, (x, y) -> {
            return x.get(0) - y.get(0);
        });
        
        SegmentTree seg = new SegmentTree(n + 1);
        seg.update(0, 0, 0);
        int[] dp = new int[a.size()];
        for(int i = 0; i < a.size(); i++) {
            int l = a.get(i).get(0) + 1, r = a.get(i).get(1) + 1, w = a.get(i).get(2);
            dp[i] = (int)(seg.query(0, 0, l - 1)) + w;
            seg.update(0, r, dp[i]);
        }
        
        int ans = 0;
        for(int x : dp) ans = Math.max(ans, x);
        return ans;
    }
}
