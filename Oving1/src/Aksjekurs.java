import java.lang.Math.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Random.*;


public class Aksjekurs {
    static int[] verdier = null;
    static int dager[] = new int[2];

        private static int[] finnBestLønning(int verdi, int[] kursforandring){
            //Finne verdier ut i fra kursendringer
            int lengde = kursforandring.length;
            verdier = new int[lengde];

            for (int i = 0; i < lengde; i++) {      //1 + 2n
                verdi += kursforandring[i];         //2n
                verdier[i] = verdi;                 //2n
            }

            //Finne størst differanse
            int diff;
            int storstDiff = 0;

            for(int i = 0; i < lengde; i++){            //
                for(int j = i + 1; j < lengde; j++){    //n^2
                    if(verdier[i] < verdier[i+1]){
                        diff = verdier[j] - verdier[i];
                        if(diff > storstDiff){
                            dager[0] = i + 1;
                            dager[1] = j + 1;
                            //(Kan ikke ha dag 0, derfor +1
                            storstDiff = diff;
                        }
                    }
                }
            }
            return dager;
        }


        public static void main(String[] args){
            Random random = new Random();
            //Variabler
            int verdi = 0;
            int[] kursforandring = new int[1000];

            for(int i = 0; i < kursforandring.length; i++){
                kursforandring[i] = random.nextInt(51) - 25;
            }

            //Kjør hundre ganger først? Varme opp vir. maskin
            Date start = new Date();
            int runder = 0;
            double tid;
            Date slutt;

            int[] dager;

            do{
                dager = finnBestLønning(verdi, kursforandring);
                slutt = new Date();
                runder++;
            }
            while(slutt.getTime() - start.getTime() < 1000);
            tid = (double) (slutt.getTime() - start.getTime())/runder;

            System.out.println("Brukt tid: " + tid + " millisekund." + "\n");
            System.out.println("Verdier" + Arrays.toString(verdier) + "\n");
            System.out.println("Gunstige dager:" + Arrays.toString(dager));
        }
}
