import java.util.Date;
import java.util.Random;
import java.util.HashMap;

public class Hashtabell2 {
    public static void main(String[] args){
        Integer[] ht = new Integer[5125000];
        Integer[] randomTall = new Integer[5000000];
        Random random = new Random();

        for(int i = 0; i < randomTall.length; i++){
            randomTall[i] = random.nextInt(10000000);
        }

        //Tidtakning
        System.out.println("---EGEN HASHFUNKSJON---");
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do{
            for(int i = 0; i < randomTall.length; i++){
                legginn(randomTall[i], ht);
            }
            slutt = new Date();
            ++runder;
        } while(slutt.getTime() - start.getTime() < 1000);
        tid = ((double)(slutt.getTime() - start.getTime()) / (double) runder);
        System.out.println(tid + " ms.");

        System.out.println("---JAVAS HASHFUNKSJON---");
        start = new Date();
        runder = 0;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        do{
            for(int i = 0; i < randomTall.length; i++){
                hmap.put(i, randomTall[i]);
            }
            slutt = new Date();
            ++runder;
        } while(slutt.getTime() - start.getTime() < 1000);
        tid = ((double)(slutt.getTime() - start.getTime()) / (double) runder);
        System.out.println(tid + " ms.");

    }


    //Hasher heltallet
    private static int hashfunk(int k, int m){
        return k % m;
    }

    private static int hashfunk2 (int k, int m){
        return (7 - (k % 7));
    }

    //Gjør om heltall til hashed verdi og legger det inn i hashtabell
    private static int legginn(Integer k, Integer[] ht){
        int m = ht.length;
        int h = hashfunk(k, m);
        int h2;
        for(int i = 0; i < m; ++i){
            if(ht[h] == null) {
                ht[h] = k;
                return h;
            }
            h2 = hashfunk2(k, m);
            h = (h + (i * h2)) % m;
        }
        return -1;
    }

    //Legger hashet verdi en indeks lenger bort om man skulle treffe en indeks som allerede er i bruk
    private static int probe(int h, int i, int m){
        return (h + i) % m;
    }

}
