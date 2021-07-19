package _算法._位算法;


/**
 * 如果题目变成一个数组里除了一个数字之外，其他数字都出现两次，找到这一个数字，我们很容易就可以实现了
 * <p>
 * 因为两个相同数字异或等于 0，一个数和 0 异或还是它本身，利用这一特性，将数组中所有数字异或，最终出现两次的所有数字异或结果为 0，只有出现一次的数字与 0 异或返回了它本身，于是我们找到了这个只出现了一次的数字
 * <p>
 * 但题目中出现一次的数字是两个不相同的数，所以如果我们仍然将所有数字异或，最终将会得到这两个不相同数字的异或结果，我们是否有办法在异或的结果中将两个数字还原为原来的数字或转化为寻找数组中只出现一次的一个数字呢？
 * <p>
 * 办法是有的，既然两个数字是不同的，那么最终的异或结果一定不为 0，而这个结果数字中，为 1 的位表示两个出现一次的数中，这两位不同
 * <p>
 * 假设异或结果的数字中，第 n 位为 1，则说明两个只出现一次的数字中，一个第 n 位为 1，一个第 n 位为 0，我们可以将原数组划分为两个数组，分别是所有第 n 位为 0 的数组成的数组和所有第 n 位为 1 的数组成的数组，这样
 * 既可以保证所有相同的数都被放入同一个数组，也可以保证两个只出现了一次的数分别被放入两个不同的数组，于是，最终我们将问题转化为找到分别在两个数组找到每个数组中只出现一次的一个数字
 */
public class _数组中找不重复的两个数 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 5, 1};
        f(arr);//不需要额外空间
    }

    private static void f(int[] arr) {
        int sec = 0;
        for (int i = 0; i < arr.length; i++) {
            sec ^= arr[i];
        }

        //找最低位的1
        int n = 0;
        while (sec != 0) {
            if ((sec & 1) == 1) break;
            sec >>>= 1;
        }


        int left = 0;
        int right = arr.length - 1;

        while (left < right) {


            //快排变体找
            while (left < right && ((arr[left] >> n & 1) == 1)) left++;

            //快排变体找
            while (left < right && ((arr[right] >> n & 1) == 0)) right--;


            if (left != right) {
                arr[left] = arr[left] ^ arr[right];
                arr[right] = arr[left] ^ arr[right];
                arr[left] = arr[left] ^ arr[right];

                left++;
                right--;
            }


        }

        //0 ~ left  // left+1 ~arr.length - 1


        int left_c = 0;
        int right_c = 0;
        for (int i = 0; i <= left; i++) {
            left_c ^= arr[i];
        }

        for (int i = left+ 1; i < arr.length; i++) {
            right_c ^= arr[i];
        }

        System.out.println(left_c);
        System.out.println(right_c);

    }
}
