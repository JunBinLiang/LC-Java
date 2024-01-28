class Solution {
    final int MOD = 1000000007;
    public int sumSubarrayMins(int[] arr) {
        Stack<int[]> stk = new Stack<>();
        long res = 0;
        //[min, count, sum]
        for(int i = 0; i < arr.length; i++) {
            int count  = 1;
            while(stk.size() > 0 && arr[i] <= stk.peek()[0]) {
                int[] p = stk.pop();
                count += p[1];
            }
            long sum = (arr[i] + 0l) * count;
            if(stk.size() > 0) sum += stk.peek()[2];
            sum %= MOD;
            res += sum;
            stk.push(new int[]{arr[i], count, (int)(sum)});
            
        }
        
        return (int)(res % MOD);
    }
}
