import java.util.Date;

public class Rekursjon2 {
    public static void main(String[] args){
        Date start;
        Date slutt;
        int runder;
        double tid;
        double res1;
        double res2;
        double x = 1.001;
        int n = 4500;

        System.out.println("2.2-3: ");
        runder = 0;
        start = new Date();
        do{
            res2 = finnPotensverdi2(x,n);
            slutt = new Date();
            runder++;
        } while(slutt.getTime() - start.getTime() < 1000);
        tid = ((slutt.getTime() - start.getTime()) / (double) runder);
        System.out.println(res2);
        System.out.println("Brukt tid: " + tid + " ms");

    }

    public static double finnPotensverdi2(double x, int n){
        if(n == 0){
            return 1;
        }
        else if(n%2 != 0){
            return x * finnPotensverdi2(x*x, (n-1)/2);
        }
        else {
            return finnPotensverdi2(x*x, n/2);
        }
    }
}
