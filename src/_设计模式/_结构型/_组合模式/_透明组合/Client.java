package _设计模式._结构型._组合模式._透明组合;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: algorithm_by_java
 * @author: 一树
 * @data: 2020/12/1 12:41
 */
//类似古代三省六部制度，各组合职责分明，而非继承皇室，臃肿
public class Client {
    public static void main(String[] args) {
        System.out.println("============透明组合模式============");
        Course javaBase = new Course("Java入门课程",8280);
        Course ai = new Course("人工智能", 1500);

        CoursePackage coursePackage = new CoursePackage("Java架构师课程", 2);

        Course design = new Course("Java设计模式", 1500);
        Course source = new Course("源码分析", 1500);
        Course softKill = new Course("软技能", 1500);

        coursePackage.addChild(design);
        coursePackage.addChild(source);
        coursePackage.addChild(softKill);

        CoursePackage catalog = new CoursePackage("课程主目录",1);
        catalog.addChild(javaBase);
        catalog.addChild(ai);
        catalog.addChild(coursePackage);

        catalog.print();
    }
}

abstract class CourseComponent {

    //定义共有操作:不使用接口是因为子类必须实现接口方法（不考虑default）
    public void addChild(CourseComponent component) {
        throw new UnsupportedOperationException("unsupported addChild");
    }

    public void removeChild(CourseComponent component) {
        throw new UnsupportedOperationException("unsupported removeChild");
    }

    public String getName(CourseComponent component) {
        throw new UnsupportedOperationException("unsupported getName");
    }

    public double gerPrice(CourseComponent catalogComponent) {
        throw new UnsupportedOperationException("unsupported gerPrice");
    }

    public void print() {
        throw new UnsupportedOperationException("unsupported print");
    }
}

//A实现
class Course extends CourseComponent {
    private String name;
    private double price;

    public Course(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(CourseComponent component) {
        return this.name;
    }

    @Override
    public double gerPrice(CourseComponent catalogComponent) {
        return this.price;
    }


    @Override
    public void print() {
        System.out.println(name + "(" + price + "元)");
    }
}

//B实现
class CoursePackage extends CourseComponent {
    private List<CourseComponent> items = new ArrayList<>();
    private String name;
    private Integer level;

    public CoursePackage(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String getName(CourseComponent component) {
        return this.name;
    }

    @Override
    public void addChild(CourseComponent component) {
        items.add(component);
    }

    @Override
    public void removeChild(CourseComponent component) {
        items.remove(component);
    }

    @Override
    public void print() {
        System.out.println(this.name);

        for (CourseComponent item : items) {
            if (this.level != null) {
                for (int i = 0; i < this.level; i++) {
                    //打印level级目录
                    System.out.print("  ");
                }

                for (int i = 0; i < this.level; i++) {
                    if (i == 0) {
                        System.out.print("+");
                    }
                    System.out.print("-");
                }

            }
            item.print();
        }
    }
}