import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xjh
 * @create 2021-10-08 11:19
 */
public class Test10_8 {


    /**
     * 350. 两个数组的交集 II
     * 解法一：哈希表
     * 思路：首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，
     * 然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，
     * 则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     * <p>
     * 时间复杂度：O(m+n),m，n分别代表两个数组的长度，需要遍历两个数组来操作哈希表
     * 空间复杂度：O(min(m,n)),两个数组中长度较短的那个
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // 这里先将第一个数组变为长度较短的那个
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 然后遍历第二数组，得到它们的交集并存储到数组中。
        // 题目要求输出元素的个数要与元素在两个数组中出现次数的最小值一致；
        // 对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
        int[] arr = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                arr[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(arr, 0, index);
    }

    /**
     * 121. 买卖股票的最佳时机
     * 解法一：暴力枚举
     * 思路：找出该数组中两个数值的最大差值，最简单的方法就是暴力法求解。
     * 使用双重for循环遍历数组，比较两个数，求出它们之间最大的差值。
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 解法二：一次遍历(贪心法)
     * 思路：假如计划在第 i 天卖出股票，那么最大利润的差值一定是在[0, i-1] 之间选最低点买入；
     * 用一个变量来保存这个最低点；然后遍历数组，依次求每个卖出时机的的最大差值，再从中取最大值。
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            max = Math.max(max, price - min);
        }
        return max;
    }

}
