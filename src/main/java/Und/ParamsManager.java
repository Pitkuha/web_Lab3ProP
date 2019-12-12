package Und;

public class ParamsManager {
    public static boolean isInArea(double x, double y, double r){
        return  (x <= 0 && y <= 0 && (x*x)+(y*y) <= r*r)||
                (x >=0 && y >= 0 && x <= r && y <= r/2)||
                (x <= 0 && y >=0 && (y - 2*x <= r));
    }

    public static double round(double n){
        n=n*1000;
        int result = (int)Math.round(n);
        double result2 = (double) result / 1000;
        return result2;
    }
}