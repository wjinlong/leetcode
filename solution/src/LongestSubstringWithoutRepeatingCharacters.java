import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author : King
 * @date : 2017/4/18 0018 20:46
 * Created by
 * IntelliJ IDEA 2016.2.5
 * Build #IU-162.2228.15, built on October 14, 2016
 * JRE: 1.8.0_91-b14 amd64
 * JVM: Java HotSpot(TM) 64-Bit Server VM by Oracle Corporation
 */

/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String string = in.next();

        System.out.println(lengthOfLongestSubstring3(string));
    }

    /*
    暴力算法，直接遍历所有的子串，记录最长的字串长度。时间复杂度O(n^2)
    时间复杂度太高，直接TLE
     */
    public static int lengthOfLongestSubstring1(String s) {
        int max = 0;
        int cur = 0;
        HashSet<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    max = cur > max ? cur : max;
                    cur = 0;
                    set.clear();
                } else {
                    cur++;
                    set.add(chars[j]);
                }
            }
            max = cur > max ? cur : max;
            cur = 0;
            set.clear();
        }
        //可能最后一段的所有的字符都不重复，所以就不会进入if条件语句，max一直为0；
        // return max; //错误
        return cur > max ? cur : max;
    }

    /*
    自己想的第二个版本，使用HashMap记录字符及出现的位置，如果出现重复的字符就吧上一次字符出现的位置之前的所有字符（位置之前的）移除，可以实现
    但是时间复杂度还是没有降下来，O(n^2)，即使在不出现重复字符的情况下不需要进入第2重循环，还是超时了。TLE
     */
    public static int lengthOfLongestSubstring2(String s) {
        int max = 0;
        int cur = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                max = cur > max ? cur : max;
                int index = map.get(chars[i]);
                cur = i - index;
                for (int j = 0; j < index; j++) {
                    if (map.containsValue(j))
                        map.remove(chars[j]);
                }
                map.put(chars[i], i);
            } else {
                ++cur;
                map.put(chars[i], i);
            }
        }
        return cur > max ? cur : max;
    }

    /*
    leetcode大神精简版，O(n)
    用一个HashMap存储字符及位置，两个指针指向是当前最长的字数组，首先移动右边的指针遍历字符串，同时更新HashMap。
    当出现重复字符的时候，需要把左边的指针指向当前字符的位置，
     */

    /**
     * Solution (DP, O(n)):
     * <p>
     * Assume L[i] = s[m...i], denotes the longest substring without repeating
     * characters that ends up at s[i], and we keep a hashmap for every
     * characters between m ... i, while storing <character, index> in the
     * hashmap.
     * We know that each character will appear only once.
     * Then to find s[i+1]:
     * 1) if s[i+1] does not appear in hashmap
     * we can just add s[i+1] to hash map. and L[i+1] = s[m...i+1]
     * 2) if s[i+1] exists in hashmap, and the hashmap value (the index) is k
     * let m = max(m, k), then L[i+1] = s[m...i+1], we also need to update
     * entry in hashmap to mark the latest occurency of s[i+1].
     * <p>
     * Since we scan the string for only once, and the 'm' will also move from
     * beginning to end for at most once. Overall complexity is O(n).
     * <p>
     * If characters are all in ASCII, we could use array to mimic hashmap.
     */

    public static int lengthOfLongestSubstring3(String s) {
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                //j=map.get(s.charAt(i))+1; //abba
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
