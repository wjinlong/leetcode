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
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String string = in.next();

        System.out.println(lengthOfLongestSubstring2(string));
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
                    if (map.containsKey(chars[j]))
                        map.remove(chars[j]);
                }
                map.put(chars[i], i);
            } else {
                ++cur;
                map.put(chars[i], i);
            }
        }
        //可能最后一段的所有的字符都不重复，所以就不会进入if条件语句，max一直为0；
        // return max; //错误
        return cur > max ? cur : max;
    }
}
