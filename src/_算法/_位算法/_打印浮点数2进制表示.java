package _算法._位算法;


//整数部分的逆运算，乘2取整
//注意double和float的差别
import java.util.Scanner;

public class _打印浮点数2进制表示 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder b = new StringBuilder("0.");
        double n = sc.nextDouble();
        while (n!=0){

            n = n*2;
            if (n>=1){
                b.append("1");
                n = n-1;
            }
            else {
                b.append("0");
            }
            if (b.length()>=34){
                System.out.println("ERROR!");
                return;
            }

        }
        System.out.println(b.toString());
    }
}
