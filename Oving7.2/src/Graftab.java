import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graf {
    static int N;
    static Object node[];
    static Kanttab kant[][];
    static BufferedReader leser;

    public static void main(String[] args){
        //Finner fil og legger inn verdiene i ny urettet graf.
        try{

            File fil = new File("C:\\Algoritmer og datastrukturer\\Oving7\\src\\L.txt");
            leser = new BufferedReader(new FileReader(fil));
            ny_ugraftab(leser);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Fil ikke funnet.");
        }

        System.out.println("Lengde: " + node.length);

    }

    public static void ny_ugraftab(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        kant = new Kanttab[N][N];
        for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) kant[j][j] = new Kanttab();
        int K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            kant[fra][til].fins = 1;
        }
    }
}
