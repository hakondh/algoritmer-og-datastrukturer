import java.util.Date;

public class Opg3 {
    public static void main(String[] args){
        Date start;
        Date slutt;
        int runder;
        double tid;
        double res1;
        double res2;
        double x = 1.001;
        int n = 4500;

        System.out.println("Potensregning m. Math.pow(x,n): ");
        runder = 0;
        start = new Date();
        do{
            res1 = finnPotensverdi3(x,n);
            slutt = new Date();
            runder++;
        } while(slutt.getTime() - start.getTime() < 1000);
        tid = ((slutt.getTime() - start.getTime()) / (double) runder);
        System.out.println(res1);
        System.out.println("Brukt tid: " + tid + " ms");
    }


    public static double finnPotensverdi3(double x, int n){
        return Math.pow(x,n);
    }
}

