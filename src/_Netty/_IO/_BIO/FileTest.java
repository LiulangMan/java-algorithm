package _Netty._IO._BIO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/10 22:07
 */
public class FileTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("src/_Netty/resources/example.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        //此处BIO，连续读，阻塞模式
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
