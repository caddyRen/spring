package indi.ikun.spring.basejava.difficulty.easy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 1, 2, 3, 4, 5, 9, 6, 7, 7};
        int target = 7;
        twoSum(a, target);

    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        int m = 0;
        //声明一个半长数组，不够后续扩容
        for (int i = nums.length - 1; i > 0; i--) {
            if (target - nums[i] >= 0) {
                List a = new ArrayList<Integer>();
                for (int j = 0; j < i; j++) {
                    if (target - nums[i] == nums[j]) {
                        if (!a.contains(j)) {
                            a.add(i);
                        }
                        if (!a.contains(j)) {
                            a.add(j);
                        }
                        System.err.print(i + " 位置的 " + nums[i] + "+ ");
                        System.err.println(j + " 位置的 " + nums[j] + " = " + target);
                    }
                }
                if (!a.isEmpty()) {
                    map.put(m, a);
                    m++;
                }
            }
        }
        System.err.println(map.toString());
        return null;
    }
}
