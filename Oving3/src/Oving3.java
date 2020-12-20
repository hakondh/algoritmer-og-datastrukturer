import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Oving3{
    public static String[] arraynavn = {"tilfeldigArray" , "duplikatArray", "sortertArray"};
    public static int antall = 6000;

    public static void main(String[] args){
        Date start;
        Date slutt;
        int runder;
        double tid;

        //TIDTAKNING
        Sortering[] sorteringsmetoder = {new Quicksort(), new DualPivotQuicksort(), new Heapsort()};
        Sortering type;

        for (int k = 0; k < sorteringsmetoder.length; k++){
            //Velge hvilken sorteringsmetode som skal brukes
            type = sorteringsmetoder[k];
            System.out.println("---" + type.getNavn() + "---");

            //Tilfeldig array
            Random random = new Random();
            int[] tilfeldigArray = new int[antall];
            for(int i = 0; i < tilfeldigArray.length; ++i) {
                tilfeldigArray[i] = random.nextInt(100001) - 500000;
            }

            //Array m. duplikater
            int[] duplikatArray = new int[antall];
            for(int i = 0; i < duplikatArray.length; i++){
                if(i % 2 == 0){
                    duplikatArray[i] = 1;
                }
                else {
                    duplikatArray[i] = 0;
                }
            }

            //Sortert array
            int[] sortertArray = new int[antall];
            for(int i = 0; i < sortertArray.length; i++){
                sortertArray[i] = i;
            }

            //Sorterer og tar tid med valgt metode
            for(int i = 0; i < 3; i++){
                int[] array;

                //De ulike arrays som skal sorteres
                if(i == 0){
                    array = tilfeldigArray;
                }
                else if(i == 1){
                    array = duplikatArray;
                }
                else {
                    array = sortertArray;
                }

                //Tidtakning
                runder = 0;
                int [] tabell;
                start = new Date();
                do{
                    int[] kopi = new int[array.length];
                    //Dyp kopiering så vi alltid får en usortert array
                    for(int p = 0; p < array.length; p++){
                        kopi[p] = array[p];
                    }

                    tabell = type.sorter(kopi, 0, kopi.length - 1);
                    slutt = new Date();
                    runder++;
                } while(slutt.getTime() - start.getTime() < 1000);
                tid = ((slutt.getTime() - start.getTime()) / (double) runder);
                System.out.println(type.getNavn() + ", " + arraynavn[i] + ": " + tid + " ms.");

                //Sjekker at tabellen er riktig sortert.
                for (int h = 0; h < tabell.length - 2; h++){
                    if(tabell[h+1] < tabell[h]) {
                        System.out.println("Tabellen har en feil.");
                        break;
                    }
                }
            }
        }
    }
}