package 笔试区._ACM;

import java.util.*;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/14 10:27
 */
class People {
    int power;
    boolean isLight;


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public People() {
    }
}

public class _偷袭 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            List<People> peoples = new LinkedList<>();
            boolean[] light = new boolean[n];
            for (int j = 0; j < n; j++) {
                People people = new People();
                people.setPower(sc.nextInt());
                peoples.add(people);
            }
            for (int j = 0; j < n; j++) {
                if (sc.nextInt() == 1) {
                    peoples.get(j).setLight(true);
                }
            }
            int[] fighter = new int[m];
            for (int j = 0; j < m; j++) {
                fighter[j] = sc.nextInt();
            }

            peoples.sort(new Comparator<People>() {
                @Override
                public int compare(People o1, People o2) {
                    if (o1.isLight && o2.isLight) {
                        return o1.getPower() - o2.getPower();
                    }
                    if (!o1.isLight && !o2.isLight) {
                        return 0;
                    }
                    if (o1.isLight) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            for (int j = 0; j < m; j++) {
                int w = fighter[j];
                int index = mysearch(peoples, w);
                list.add(index + 1);
            }

        }

        for (int a : list) {
            System.out.println(a);
        }
    }

    private static int mysearch(List<People> peoples, int w) {
        //二分查找
        int len = peoples.size();
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (!peoples.get(mid).isLight) {
                right = mid;
            } else if (mid + 1 > right) {
                return right;
            } else if (peoples.get(mid).getPower() < w && !peoples.get(mid + 1).isLight) {
                return mid;
            } else if (peoples.get(mid).getPower() < w && peoples.get(mid + 1).getPower() >= w) {
                return mid;
            } else if (peoples.get(mid).getPower() < w && peoples.get(mid + 1).getPower() < w) {
                left = mid;
            } else if (peoples.get(mid).getPower() >= w && peoples.get(mid + 1).getPower() >= w) {
                right = mid;
            }
        }
        return -1;
    }
}
