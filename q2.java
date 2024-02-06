

import java.util.*;

class RadioWave {
    List<Integer> g[];
    boolean[] visit;
    int ans = 0;

    public RadioWave() {

    }

    public int solve(int n, int[] from, int[] to, int[] frequency) {
        g = new ArrayList[n];
        visit = new boolean[n];
        Arrays.setAll(g, e -> new ArrayList<>());

        for(int i = 0; i < n - 1; i++) {
            int u = from[i] - 1, v = to[i] - 1;
            if(Math.abs(frequency[u] - frequency[v]) != 1) continue;
            g[u].add(v);
            g[v].add(u);
        }

        for(int i = 0; i < n; i++) {
            if(visit[i]) continue;
            dfs(-1, i);
        }
        return ans;
    }

    public int dfs(int pa, int u) {
        visit[u] = true;
        int curMax = 0;
        for(int nxt : g[u]) {
            if(nxt == pa) continue;
            int len = dfs(u, nxt);
            if(curMax != 0) {
                ans = Math.max(ans, len + curMax);
            }
            ans = Math.max(ans, len);
            curMax = Math.max(curMax, len);
        }

        return curMax + 1;

    }


}

class UnequalElements {

    public int solve(int[] nums, int K) {
        int n = nums.length;
        int[][] dp = new int[2100][K + 1]; //以x 为结尾，并且有k个不相同相邻element subsequence 最长度
        int[] maxDp = new int[K + 1];
        Arrays.fill(maxDp, -1);
        for(int[] p : dp) Arrays.fill(p, -1);

        dp[nums[0]][0] = 1;
        maxDp[0] = 1;

        for(int i = 1; i < n; i++ ) {
            int x = nums[i];
            int[] subDp = new int[K + 1];
            for (int k = 0; k <= K; k++) {
                if (dp[x][k] != -1)
                    subDp[k] = Math.max(subDp[k], dp[x][k] + 1); //前面是x, 现在是x
                if (k > 0 && maxDp[k - 1] != -1) {
                    subDp[k] = Math.max(subDp[k], maxDp[k - 1] + 1); //前面 !x, 现在是x
                }
                maxDp[k] = Math.max(maxDp[k], subDp[k]);
            }
            for (int k = 0; k <= K; k++) {
                dp[x][k] = Math.max(dp[x][k], subDp[k]);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            for(int k = 0; k <= K; k++) {
                ans = Math.max(ans, dp[i][k]);
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String args[]) {
        //RadioWave q1 = new RadioWave();
        //System.out.println(q1.solve(4, new int[]{1, 2, 3}, new int[]{2, 3, 4}, new int[]{1, 3, 2, 1}));
        UnequalElements q2 = new UnequalElements();
        System.out.println(q2.solve(new int[]{1, 1, 2, 3, 4, 5, 5, 5, 5}, 4));
    }
}

