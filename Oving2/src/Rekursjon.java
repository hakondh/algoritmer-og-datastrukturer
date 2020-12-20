import java.util.Date;

public class Rekursjon {
    public static void main(String[] args){
        Date start;
        Date slutt;
        int runder;
        double tid;
        double res1;
        double res2;
        double x = 1.001;
        int n = 4500;

        System.out.println("2.1-1: ");
        runder = 0;
        start = new Date();
        do{
            res1 = finnPotensverdi1(x,n);
            slutt = new Date();
            runder++;
        } while(slutt.getTime() - start.getTime() < 1000);
        tid = ((slutt.getTime() - start.getTime()) / (double) runder);
        System.out.println(res1);
        System.out.println("Brukt tid: " + tid + " ms");

    }

    public static double finnPotensverdi1(double x, int n){
        if(n == 0){
            return 1;
        }
        else{
             return x * finnPotensverdi1(x,n-1);
        }
    }
}
