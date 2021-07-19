package _Java_SE._枚举;

import java.util.Scanner;

interface Operation{
    //定义一个共有方法，在每个enum中实现
    double apply(double x, double y);
}

enum ExtendedOperation implements Operation{

    EXP("^"){
        @Override
        public double apply(double x, double y) {
            return Math.pow(x,y);
        }
    },
    REMAINDER("%"){
        @Override
        public double apply(double x, double y) {
            return x%y;
        }
    };


    private String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

public enum BaseOperation implements Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };


    private final String symbol;

    BaseOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x = in.nextDouble();
        double y = in.nextDouble();

        for (BaseOperation value : BaseOperation.values()) {
            System.out.printf("%f %s %f = %f%n", x, value, y, value.apply(x, y));
        }
        for (ExtendedOperation value : ExtendedOperation.values()) {
            System.out.printf("%f %s %f = %f%n", x, value, y, value.apply(x, y));
        }
    }
}
