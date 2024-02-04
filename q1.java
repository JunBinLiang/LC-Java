

import java.util.*;

class StringTransFormer {
    String t;
    String[] words;
    public StringTransFormer(String t, String[] words) {
        this.t = t;
        this.words = words;
    }

    public int solve() {
        //(a, c)
        //(a, 0)  (d, 1), (c, 2)   (a, 0) (e, 1)  (c, 2) (e, 0) (f, 1) (g, 2)
        //(a, 0)  (a, 0) (e, 0),             (d, 1), (f, 1), (e, 1) ,                (c, 2)   (c, 2)  (g, 2)
        List<int[]> list = new ArrayList<>(); //26 * 3000
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            for(int j = 0; j < word.length(); j++) {
                int c = word.charAt(j) - 'a';
                list.add(new int[]{c, j});
            }
        }

        Collections.sort(list, (x, y) -> {
            return x[1] - y[1];
        });

        //[0 2]
        for(int p[] : list) {
            System.out.print(Arrays.toString(p) + "  ");
        }
        System.out.println();

        int[] preDp = new int[t.length()];
        for(int i = 0; i < list.size(); i++) {
            int j = i;
            int[] count = new int[26];
            while(j < list.size() && list.get(j)[1] == list.get(i)[1]) {
                count[list.get(j)[0]]++;
                j++;
            }

            int[] curDp = new int[t.length()];
            for(int x = 0; x < t.length(); x++) {
                int c = t.charAt(x) - 'a';
                if(count[c] > 0) {
                    if(x == 0) {
                        curDp[x] = count[c];
                    } else {
                        curDp[x] += (preDp[x - 1] * count[c]);
                    }
                }
                curDp[x] += preDp[x];
            }
            preDp = curDp;
            System.out.println(i + "  " + (j - 1) + "  " + Arrays.toString(curDp));
            i = j - 1;
        }
        return preDp[t.length() - 1];
    }
}

class ArrayReduction {
    int[] nums;
    TreeSet<Integer> tr = new TreeSet<>();
    public ArrayReduction(int[] nums) {
        this.nums = nums;
        for(int i = 0; i <= nums.length + 10; i++) {
            tr.add(i);
        }
    }
    //1. 后面如果有可补充的MEX, 把那个MEX 给包含
    //2. 没有MEX, 立马截断
    public int getMex() {
        return tr.first();
    }

    public int getMexIndex(Map<Integer, List<Integer>> map, int mex, int R) {
        if(!map.containsKey(mex)) {
            return -1;
        }
        List<Integer> list = map.get(mex);
        int l = 0, r = list.size() - 1;
        int ans = -1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(list.get(mid) > R) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans == -1 ? -1 : list.get(ans);
    }

    public String solve() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        
        String ans = "";
        for(int i = 0; i < n; i++) {
            int L = i, R = i;
            tr.remove(nums[i]);
            //System.out.println(L + " " + R + " ");
            while(true) {
                int mexneed = getMex();
                int mexIndex = getMexIndex(map, mexneed, R);
                if(mexIndex == -1) {
                    break;
                } else {
                    for(int j = R + 1; j <= mexIndex; j++) {
                        tr.remove(nums[j]);
                    }
                    R = mexIndex;
                }
            }
            ans += ("" + getMex());
            //把treeset 补充一下
            for(int j = L; j <= R; j++) tr.add(nums[j]);
            i = R;
        }
        return ans;
    }
}

public class Main {
    public static void main(String args[]) {
        String target = "ac";
        String words[] = new String[]{"adc", "aec", "efg"};
        StringTransFormer q1 = new StringTransFormer(target, words);
        System.out.println(q1.solve());

        ArrayReduction q2 = new ArrayReduction(new int[]{0, 1, 1, 0});
        System.out.println(q2.solve());
    }
}

