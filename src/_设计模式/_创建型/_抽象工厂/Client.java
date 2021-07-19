package _设计模式._创建型._抽象工厂;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/1 16:35
 */

/**
 * 抽象工厂是指把具体的工厂拆分成各个细节抽象
 *
 * **/

public class Client{
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        IVideo iVideo = javaCourseFactory.createIVideo();
        INote note = javaCourseFactory.createNote();
        iVideo.record();
        note.edit();
    }
}

interface IVideo{
    void record();
}

interface INote{
    void edit();
}
interface CourseFactory {
    IVideo createIVideo();
    INote createNote();
}


/**
 * 产品族
 * */

class JavaIVideo implements IVideo{

    @Override
    public void record() {
        System.out.println("录制JAVA视频");
    }
}


class JavaINote implements INote{

    @Override
    public void edit() {
        System.out.println("编写java笔记");
    }
}

/**
* 产品族具体工厂
* */

class JavaCourseFactory implements CourseFactory{

    @Override
    public IVideo createIVideo() {
        return new JavaIVideo();
    }

    @Override
    public INote createNote() {
        return new JavaINote();
    }
}