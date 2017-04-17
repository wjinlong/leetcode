import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : King
 * @date : 2017/4/17 0017 15:28
 * Created by
 * IntelliJ IDEA 2016.2.5
 * Build #IU-162.2228.15, built on October 14, 2016
 * JRE: 1.8.0_91-b14 amd64
 * JVM: Java HotSpot(TM) 64-Bit Server VM by Oracle Corporation
 */

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].



 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 13;

        System.out.println(Arrays.toString(twoSum3(nums, target)));
    }

    /*
    穷举，时间复杂度O(n^2)

     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result = new int[]{i, j};
                    return result;
                }
            }
        }
        return result;
    }

    /*
    利用HashMap，时间复杂度可以降低到O(n)
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = {-1, -1};

        HashMap<Integer, Integer> map = new HashMap();
        //先将所有的数字加入map中
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        //遍历一遍数组
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
        }
        return result;
    }

    /*
    对twoSum2的优化，O(n)
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] result = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            //判断是否第一个数字是否在map中，一次遍历就可以
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
