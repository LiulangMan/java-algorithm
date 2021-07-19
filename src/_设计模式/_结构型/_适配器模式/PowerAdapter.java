package _设计模式._结构型._适配器模式;

/**
 * @program: suanfa_by_java
 * @author: 一树
 * @data: 2020/11/1 11:57
 */

interface DC5{
    int outputDC5();
}

class AC220 {
    public int outputAC220V(){
        int output = 220;
        System.out.println("输出电压:"+output+"v");
        return output;
    }
}

public class PowerAdapter implements DC5{

    private AC220 ac220;

    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC5() {
        int adapterInput = ac220.outputAC220V();
        //变压器...
        int adapterOutput = adapterInput/44;
        System.out.println("输出电压:"+adapterOutput+"v");
        return adapterOutput;
    }

    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        System.out.println(dc5.outputDC5());
    }
}
