class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        LinkedList<int[]> mono = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            while(mono.size() > 0 && nums[i] >= mono.getLast()[0]) {
                mono.removeLast();
            }
            mono.add(new int[]{nums[i], i});
            if(i + 1 >= k) {
                while(mono.size() > 0 && i - mono.getFirst()[1] + 1 > k) mono.removeFirst();
                ans[i - k + 1] = mono.getFirst()[0];
            }
        }
        return ans;
    }
}
