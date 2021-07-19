package _算法._贪心策略和动态规划._动态规划;

import static java.lang.Math.max;

/***
 * 著名问题：最长公共子序列
 *
 *子序列中的字符与子字符串中的字符不同，它们不需要是连续的。例如，ACE 是 ABCDE 的子序列，但不是它的子字符串。请看下面两个 DNA 序列：
 *
 * S1 = DE>GCCCTAGCGDE>
 * S2 = DE>GCGCAATGDE>
 * 这两个序列的 LCS 是 CCAGDE。（请注意，这仅是一个 LCS，而不是唯一的 LCS，因为可能存在其他长度相同的公共子序列。这种最优化问题和其他最优化问题的解可能不止一个。）
 *
 * LCS 算法
 *
 * 首先，考虑如何递归地计算 LCS。令：
 *
 * C1 是 S1 最右侧的字符
 * C2 是 S2 最右侧的字符
 * S1' 是 S1 中 “切掉” C1 的部分
 * S2' 是 S2 中 “切掉” C2 的部分
 * 有三个递归子问题：
 *
 * L1 = LCS(S1', S2)
 * L2 = LCS(S1, S2')
 * L3 = LCS(S1', S2')
 * 结果表明（而且很容易使人相信）原始问题的解就是下面三个子序列中最长的一个：
 *
 * L1
 * L2
 * 如果 C1 等于 C2，则为 L3 后端加上 C1 ，如果 C1 不等于 C2，则为 L3。
 *
 *
 * ***/
public class _LCS {
    public static void main(String[] args) {
        String s1 = "CCCTAGCGDE";
        String s2 = "GCGCAATGDE";
        System.out.println(Lcs_dp(s1, s2));
    }

    private static String Lcs_dp(String s1, String s2) {
        //dp[i][j]表示s1.sub(i) 与 s2.sub[j]的lcs
        int[][] dp = new int[s1.length()][s2.length()];

        //初始化第一行
        for (int i = 0; i < s2.length(); i++) {
            if (i - 1 >= 0 && dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            } else {
                if (s1.charAt(0) == s2.charAt(i)) {
                    dp[0][i] = 1;
                }
            }
        }

        //初始化第一列
        for (int i = 0; i < s1.length(); i++) {
            if (i - 1 >= 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            } else {
                if (s1.charAt(i) == s2.charAt(0)) {
                    dp[i][0] = 1;
                }
            }
        }

        //建立dp表
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                //如果s1[i]和s2[j]不相同，与上前（上）一位相同
                if (s1.charAt(i) == s2.charAt(j)) {
                    //类推
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //保持列或行的最大值
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        //然后根据得到的dp表解析最长子序列
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 && j >= 0; ) {
            int current = dp[i][j];
            int left = j - 1 >= 0 ? dp[i][j - 1] : -1;
            int up = i - 1 >= 0 ? dp[i - 1][j] : -1;

            if (current == left) {
                j--;
            } else if (current == up) {
                i--;
            } else {
                sb.append(s1.charAt(i));
                i--;
                j--;
            }
        }
        return sb.reverse().toString();
    }
}
