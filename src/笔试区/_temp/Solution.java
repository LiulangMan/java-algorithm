package 笔试区._temp;


import java.util.*;

import static _算法._递归_DFS_回溯_剪枝._返回所有子集的二进制解法.pow;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode next = null;

    public TreeNode(int val) {
        this.val = val;
    }

}

class TreeLinkNode {
    int val = 0;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    public TreeLinkNode(int val) {
        this.val = val;
    }

}

public class Solution {
    public static void main(String[] args) {

    }

    public int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) map.put(str.charAt(i), 2);
            else map.put(str.charAt(i), 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1)return i;
        }

        return -1;
    }


    public int NumberOf1Between1AndN_Solution(int n) {
        return 0;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Map<ListNode, Integer> map = new HashMap<>();

        while (pHead1 != null) {
            map.put(pHead1, 1);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            if (map.containsKey(pHead2)) return pHead2;
            pHead2 = pHead2.next;
        }

        return null;
    }

    public int GetNumberOfK(int[] array, int k) {
        //统计一个数字在升序数组中出现的次数。
        int index = Arrays.binarySearch(array, k);
        if (index < 0) return 0;
        int cnt = 1;
        for (int i = index + 1; i < array.length; i++) {
            if (array[i] == k) cnt++;
        }
        for (int i = index - 1; i >= 0; i--) {
            if (array[i] == k) cnt++;
        }
        return cnt;
    }

    public String ReverseSentence(String str) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == ' ') {
                stack.push(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(ch);
            }
        }

        stack.push(sb.toString());

        sb = new StringBuilder();

        while (!stack.empty()) {
            sb.append(stack.pop());
            sb.append(' ');
        }

        return sb.substring(0, sb.length() - 1);
    }


    public int InversePairs(int[] array) {
        return f(array, 0, array.length - 1);
    }

    private int f(int[] array, int lo, int hi) {
        if (lo == hi) return 0;

        int mid = (lo + hi) >> 1;

        int cnt1 = f(array, lo, mid);
        int cnt2 = f(array, mid + 1, hi);

        //然后 归并 排序 且统计
        //每个排序块 对另一块 没有影响

        int cnt3 = 0;
        int[] help = new int[hi - lo + 1];
        int index = 0;
        int p1 = lo;
        int p2 = mid + 1;
        while (p1 <= mid || p2 <= hi) {
            if (p1 <= mid && p2 <= hi) {
                int min = Math.min(array[p1], array[p2]);

                if (min == array[p2] && min != array[p1]) {
                    cnt3 += mid - p1 + 1;
                    cnt3 %= 1000000007;
                    p2++;
                } else {
                    p1++;
                }

                help[index++] = min;
            } else if (p1 <= mid) {
                help[index++] = array[p1++];
            } else {
                help[index++] = array[p2++];
            }
        }

        //归并原数组
        index = 0;
        for (int i = lo; i <= hi; i++) {
            array[i] = help[index++];
        }

        return (cnt1 + cnt2 + cnt3) % 1000000007;
    }


    public boolean isContinuous(int[] numbers) {

        if (numbers.length != 5) return false;
        Arrays.sort(numbers);

        int King = 0;

        for (int e : numbers) {
            if (e == 0) King++;
            else break;
        }

        for (int i = King + 1; i < numbers.length; i++) {
            if (numbers[i] - numbers[i - 1] > 1) {

                int need = numbers[i] - numbers[i - 1] - 1;
                if (King >= need) {
                    King -= need;
                } else {
                    return false;
                }
            } else if (numbers[i] == numbers[i - 1]) return false;
        }


        return true;
    }


    public void Tree_to_link(TreeNode pRoot, TreeNode parent) {

        if (pRoot.left != null) Tree_to_link(pRoot.left, pRoot);
        if (pRoot.right != null) Tree_to_link(pRoot.right, pRoot);

        if (parent == null) return;
    }

    public void left_rotate(TreeNode node, TreeNode parent) {
        //左旋
        if (node.right != null) {
            TreeNode left = node.right.left;
            //TreeNode right = node.right.right;

            //左旋
            parent.left = node.right;
            node.right.left = node;
            node.right = left;
        }

    }

    public String LeftRotateString(String str, int n) {
        StringBuilder sb = new StringBuilder();

        if (n == 0) return str;
        if (n / str.length() > 0) n = n % str.length();
        for (int i = n; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }

        for (int i = 0; i < n; i++) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        //平衡树(Balance Tree，BT) 指的是，任意节点的子树的高度差都小于等于1

        return dfs_count(root) != -1;

    }

    public int dfs_count(TreeNode treeNode) {
        if (treeNode == null) return 0;

        int left = 0;
        int right = 0;
        if (treeNode.left != null) {
            left = dfs_count(treeNode.left);
            if (left == -1) return -1;
        }

        if (treeNode.right != null) {
            right = dfs_count(treeNode.right);
            if (right == -1) return -1;
        }
        if (Math.abs(left - right) > 1) return -1;
        int high = Math.max(left, right);

        return high + 1;

    }

    public int GetUglyNumber_Solution(int index) {
        if (index < 0) return 0;
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        while (list.size() < index) {
            int m2 = list.get(p2) * 2;
            int m3 = list.get(p3) * 3;
            int m5 = list.get(p5) * 5;
            int min = Math.min(m3, m2);
            min = Math.min(min, m5);

            if (min == m2) p2++;
            if (min == m3) p3++;
            if (min == m5) p5++;

            list.add(min);
        }


        return list.get(index - 1);
    }


    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> p = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = pRoot;
        TreeNode left = null;
        TreeNode right = null;
        if (root == null) return p;

        queue.offer(root);

        int start = 0;
        int end = 1;
        //采用队列就可以BFS
        while (!queue.isEmpty()) {
            TreeNode r = queue.poll();
            if (r.left != null) queue.offer(r.left);
            if (r.right != null) queue.offer(r.right);
            list.add(r.val);

            start++;

            if (start == end) {
                start = 0;
                end = queue.size();
                p.add(list);
                list = new ArrayList<>();
            }
        }
        return p;

    }

    public boolean match(char[] str, char[] pattern) {

        int P_len = pattern.length;
        int S_len = str.length;
        int p = 0;
        int s = 0;

        boolean h_clock = false;
        while (p < P_len && !h_clock) {
            //情况1：s 不为空
            if (s < S_len) {
                //相等
                if (str[s] == pattern[p] || pattern[p] == '.') {
                    //均移动
                    if (p + 1 < P_len && pattern[p + 1] != '*') {
                        s++;
                        p++;
                        continue;
                    }

                    //pattern(p+1) == '*':进入后缀模式
                    h_clock = true;

                } else {
                    //不相等

                    //考虑‘*’号
                    //此位为 ‘*’
                    if (pattern[p] == '*') {

                        //观察前一位
                        if (p - 1 >= 0 && (pattern[p - 1] == str[s] || pattern[p - 1] == '.')) {
                            //相同
                            s++;
                        }

                        //观察后一位
                        else if (p + 1 < P_len && (pattern[p + 1] == str[s] || pattern[p + 1] == '.')) {

                            p += 2;
                            s++;
                        }
                        //前后位均不同
                        else {
                            return false;
                        }
                    }
                    //此位不为'*'
                    else {
                        //观察下一位
                        //下一位是‘*’
                        if (p + 1 < P_len && pattern[p + 1] == '*') {
                            // * = 0 的情况
                            //s不动， p移动2
                            p += 2;
                            continue;
                        }
                        //下一位不是‘*’
                        else {
                            return false;
                        }
                    }
                }


            } else {
                //s 匹配已经位空

            }

        }


        return true;
    }


    public static int solve(int x) {
        int r = Integer.MAX_VALUE ^ x;
        int length = String.valueOf(Integer.MIN_VALUE).length();//最大长度
        int[] rec = new int[length];

        int i = 0;
        while (r != 0) {
            if ((r & 1) == 1) {
                //
                rec[i] = 1;
            }
            r >>>= 1;//右移一位
            i++;
        }

        //接下来对rec进行组合分析
        //可以枚举
        //也可以找规律，若时间紧张，不想思考，就直接枚举吧
        //a^b = 111001000101  ----> 不用管0，
        //枚举复杂度为 2**n n为int型最大位数，因此枚举不大
        //其实显然 |a-b|最小，肯定是交错的：比如上面第12位，肯定一个是a=2**12,那么另一个要使得差最小，就只能把占第11位
        //即是b = 2^11
        //那么现在差 a-b = 2**12 - 2**11
        //再看后面，到底谁能拿走第10位的1，就看 2**12 - 2**11，如果大于0，就说明后者还可以拿走，即
        //b = 2**11+2**10，否则a会堆积得更大，差值就越大
        //以此类推，到最后一位
        int a = 0;
        int b = 0;
        int cnt = 1;//比如存在一种情况
        for (int j = rec.length - 1; j >= 0; j--) {
            if (rec[j] == 1) {
                int temp = pow(2, j);//写个辅助方法,2**j
                if (a - b > 0) {
                    b += temp;
                } else if (a - b < 0) {
                    a += temp;
                } else {
                    // 如果a-b == 0, 给a 或者 b都可以 这即是要增加情况的条件
                    //在这里记录一下就可以了 假设给a
                    a += temp;
                    cnt++;
                }
            }
        }

        return cnt * 2 * 2;//
    }


    /***
     *
     * a:面粉总数
     * b:馅总数
     * c:无馅的粽子需要多少馅
     * d:无馅的粽子价值
     *
     * ai:i馅的数量
     * bi:包一个i馅需要多少馅
     * ci:包一个i馅要多少面粉
     * di:i馅粽子价值
     *
     *
     *
     * ***/
    //dp[i][j] 表示用了i种馅，用了j个面粉,而组成的最大价值
    //辅助数组：w[i][j],表示用了i种馅时，j馅还有多少可用
    //Return dp[a][b];
    //递推
    //dp[i][j] = max(dp[i-1][j],dp[i][j-b[i]]+d[i])
    //upset: w[i][j] = w[i][j] - b[i],a -= c[i] if condition2
    public static int maxValue() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        //无馅也是一种馅
        int[] ai = new int[b + 1];
        int[] bi = new int[b + 1];
        int[] ci = new int[b + 1];
        int[] di = new int[b + 1];

        //初始化馅
        ai[0] = 1;
        bi[0] = 0;
        ci[0] = c;
        di[0] = d;

        for (int i = 1; i <= b; i++) {
            int am = sc.nextInt();
            int bm = sc.nextInt();
            int cm = sc.nextInt();
            int dm = sc.nextInt();
            ai[i] = am;
            bi[i] = bm;
            ci[i] = cm;
            di[i] = dm;
        }


        int[][] dp = new int[b + 2][a + 1];
        for (int i = 1; i <= b + 1; i++) {
            for (int j = 1; j <= a; j++) {
                //递推：
                if (j < ci[i]) {
                    dp[i][j] = dp[i - 1][j];//拿不下，用上一层
                } else {
                    int v1 = dp[i - 1][j];
                    int v2 = 0;
                    for (int k = 0; k < j / ci[i] && k <= ai[i]; k++) {
                        v2 = Math.max(v2, dp[i][j - k * ci[i]] + k * di[i]);
                    }
                    dp[i][j] = Math.max(v1, v2);
                }
            }
        }

        return dp[b + 1][a];
    }

    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0) return 0;
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            //左侧有序
            if (array[mid] > array[left]) {
                if (mid + 1 < array.length && array[mid + 1] > array[mid]) {
                    return array[mid + 1];
                }
                left = mid + 1;
            } else {
                if (mid - 1 >= 0 && array[mid - 1] > array[mid]) {
                    return array[mid];
                }
                right = mid - 1;
            }
        }

        return 0;
    }
}
