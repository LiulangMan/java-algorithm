package _设计模式._创建型._简单工厂;


public class Client{
    public static void main(String[] args) {
        ICourse python = CourseFactory.Creat(PythonCourse.class);
        ICourse java = CourseFactory.Creat(JavaCourse.class);
        python.record();
        java.record();
    }
}
interface ICourse {
    void record();//不变的部分
}

class JavaCourse implements ICourse{
    @Override
    public void record() {
        System.out.println("JAVA");
    }
}

class EmptyCourse implements ICourse{

    @Override
    public void record() {
        System.out.println("");
    }
}

class PythonCourse implements ICourse{
    @Override
    public void record() {
        System.out.println("PYTHON");
    }
}

class CourseFactory {
    public static ICourse Creat(Class<? extends ICourse> clazz){
        try{
            if (clazz != null){
                return clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new EmptyCourse();
    }

}
