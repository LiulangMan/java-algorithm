package _Java_SE._枚举;


import java.util.Scanner;

/**
 * 枚举声明
 */
public enum Planet {

    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+24, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027E7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;
    private final double G = 6.67300e-11;

    //枚举构造函数
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getSurfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }

    public static void main(String[] args) {
        double earthWeight = (new Scanner(System.in)).nextDouble();
        double mass = earthWeight / Planet.EARTH.getSurfaceGravity();

        for (Planet value : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", value, value.surfaceWeight(mass));
        }
    }
}
