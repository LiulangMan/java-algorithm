package _设计模式._行为型._模板模式;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/1 11:42
 */
//模板会有一个或者多个为实现的方法，而且这几个未实现的方法有固定的执行顺序
@SuppressWarnings("all")
public abstract class NetWorkCourse {

    protected final void createCurse() {

        //发布预售资料
        this.postPreResource();

        //制作PPT
        this.createPPT();

        //在线直播
        this.liveVideo();

        //提交课堂作业
        this.postNote();

        //提交源码
        this.postSource();

        //如果布置了作业，需要检查
        if (needHomeWork()) {
            checkHomeWork();
        }
    }

    //不变的部分用final
    final void checkHomeWork() {
        System.out.println("检查作业");
    }

    //钩子方法
    protected boolean needHomeWork() {
        return false;
    }

    final void postSource() {
        System.out.println("提交源码");
    }

    final void postNote() {
        System.out.println("提交笔记");
    }

    final void liveVideo() {
        System.out.println("检查作业");
    }

    final void createPPT() {
        System.out.println("制作PPT");
    }

    final void postPreResource() {
        System.out.println("提交预习资料");
    }
}
