class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        
        LinkedList<int[]> mono = new LinkedList<>();
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < points.length; i++) {
            int x = points[i][0], y = points[i][1];
            while(mono.size() > 0 && x - mono.getFirst()[1] > k) mono.removeFirst();
            if(mono.size() > 0) {
                ans = Math.max(ans, y + mono.getFirst()[0] + (x - mono.getFirst()[1]));
            }
           
            while(mono.size() > 0) {
                int[] last = mono.getLast();
                if(y - x > last[0] - last[1]) {
                    mono.removeLast();
                } else {
                    break;
                }
                
            }
            mono.add(new int[]{y, x});
        }
        return ans;
    }
}
