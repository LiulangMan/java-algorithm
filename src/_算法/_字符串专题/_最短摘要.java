package _算法._字符串专题;


/***
 *
 * 尺取法
 *
 * 给出一段字符串w，再给出一段字符串q，查找包含q的最短w子数组
 *
 * ***/
public class _最短摘要 {
    public static void main(String[] args) {
        String[] word = {"a", "b", "d", "d", "e", "f", "g"};
        String[] keys = {"d", "e","f"};
        find(word, keys);
    }

    private static void find(String[] word, String[] keys) {

        int begin = -1;
        int end = -1;
        int minlen = Integer.MAX_VALUE;
        int p_j = -1;
        int p_i = -1;

        int[] key_remember = new int[keys.length];

        for (int i = 0; i < word.length; i++) {
            //Arrays.fill(key_remember, 0);
            if (p_i != -1){
                key_remember[indexof(keys,word[p_i])] = 0;//更新上次起点
            }
            int index = indexof(keys, word[i]);
            if (index == -1) {
                continue;
            } else {
                key_remember[index] = 1;
                p_i = i;
            }
            int j;
            if (p_j == -1) {
                j = i + 1;
            } else {
                j = p_j;
            }
            for (; j < word.length; j++) {

                int index1 = indexof(keys, word[j]);
                if (index1 == -1) {
                    continue;
                } else {
                    key_remember[index1] = 1;
                    if (sum(key_remember) == key_remember.length) {//查找到了全部关键字
                        int len = j - i + 1;
                        p_j = j;
                        if (len < minlen) {
                            begin = i;
                            end = j;
                            minlen = len;
                        }
                        break;
                    }
                }
            }
        }

        for (int i = begin; i <= end; i++) {
            System.out.print(word[i] + " ");
        }
    }

    private static int sum(int[] key_remember) {
        int s = 0;
        for (int m : key_remember) {
            s += m;
        }
        return s;
    }

    private static int indexof(String[] keys, String s) {
        for (int i = 0; i < keys.length; i++) {
            if (s.equals(keys[i])) {
                return i;
            }
        }
        return -1;
    }
}
