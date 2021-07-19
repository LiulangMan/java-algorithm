package 笔试区._temp_diction_8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] map = new char[n][m];
            for (int j = 0; j < n; j++) {
                map[j] = sc.next().toCharArray();
            }

            f(ans, map);
            //Arrays.fill(visited,false);
        }

        for (String a : ans) {
            System.out.println(a);
        }
    }

    private static void f(ArrayList<String> ans, char[][] map) {
        int x = -1, y = -1;
        visited = new boolean[map.length][map[0].length];//标记是否走过
        //找到王者坐标
        for (int i = 0; i < map.length && x == -1; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'S') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        if (find(map, x, y)) {
            ans.add("YES");
        } else {
            ans.add("NO");
        }
    }

    private static boolean find(char[][] map, int x, int y) {
        if (map[x][y] == 'E') return true;

        visited[x][y] = true;//标记来过

        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        if (x - 1 >= 0 && map[x - 1][y] != '#' && !visited[x - 1][y])
            left = find(map, x - 1, y);

        if (x + 1 < map[0].length && map[x + 1][y] != '#' && !visited[x + 1][y])
            right = find(map, x + 1, y);

        if (y - 1 >= 0 && map[x][y - 1] != '#' && !visited[x][y - 1])
            up = find(map, x, y - 1);

        if (y + 1 < map[0].length && map[x][y + 1] != '#' && !visited[x][y + 1])
            down = find(map, x, y + 1);

        return left || right || up || down;
    }
}
