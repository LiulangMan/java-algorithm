package _算法._贪心策略和动态规划._动态规划;



import static java.lang.Math.max;

/***
 * 著名问题：
 *
 * 输入一个数组
 * 输出最长递增子序列的长度
 *
 * 如 4 2 3 1 5 6
 * 输出 4   （2 3 5 6）
 *
 *
 * ***/
public class _最长递增子序列 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 3, 1, 4, 2, 5, 3, 6, 4, 2, 5, 4};
        System.out.println(dp1(arr));
        System.out.println(dp2(arr));
    }

    private static int dp1(int[] arr) {
        //O（n^2）
        //dp含义：dp[i]表示以arr[i]为结尾子序列的递增长度
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            //返回i前面的dp最大值，并且结果加1（代表放入其后）
            dp[i] = max_add_1(dp, arr, i);
        }
        //因此，在这儿要扫描dp,返回最大值
        int ans = 1;
        for (int e : dp) {
            ans = max(ans, e);
        }
        return ans;
    }

    private static int dp2(int[] arr) {
        //O(NlogN)
        //dp含义：dp[i]表示长度为i的递增序列的最后一个元素
        int[] dp = new int[arr.length + 1];

        //初始化
        int len = 1;
        dp[len] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (dp[len] <= arr[i]) {
                dp[++len] = arr[i];
            } else {
                //替换第一个比它大的元素，这样对后面的贡献更大,
                // 优化：折半查找
                int index = indexOfFirstBigger(dp, arr[i], 1, len);
                if (index != -1) dp[index] = arr[i];
            }
        }
        return len;
    }

    private static int indexOfFirstBigger(int[] dp, int v, int l, int r) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (dp[mid] > v) {
                r = mid; //保存v的下标，可能v即是第一个Bigger
            } else {
                l = mid + 1;
            }
            if (l == r && dp[l] > v) return l;
        }
        return -1;
    }

    private static int max_add_1(int[] dp, int[] arr, int i) {
        int max = 1;
        for (int j = 0; j < i; j++) {
            //和前面的dp作比较，取dp+1后 最大值
            if (arr[i] > arr[j]) {
                //true?dp[j]+1:1
                max = max(max, dp[j] + 1);
            }
        }
        return max;
    }
}
