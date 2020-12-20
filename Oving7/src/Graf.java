import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Graf {
    static int N, K;
    static Node[] node;
    static Node[] orginal;
    static int komp;

    static BufferedReader leser;

    public static void main (String[] args){

        //Finner fil og legger inn verdiene i ny urettet graf.
        try{

            File fil = new File("C:\\Algoritmer og datastrukturer\\Oving7\\src\\L.txt");
            leser = new BufferedReader(new FileReader(fil));
            ny_ugraf(leser);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Fil ikke funnet.");
        }

        System.out.println("lengde " + node.length);

        //Initialiserer en gang før kjøring
        dfs_init();
        for(int i = N; i-- > 0;){
            orginal[i].d = new Dfs_forgj();
        }
        Dfs_forgj.null_tid();


        System.out.println("---USORTERT, MEN UTFØRT SØK PÅ HVER NODE---");
        //Dybde først søk på hver node
        for(int i = 0; i < node.length; i++){
            dfs(orginal[i]);
            dfs(node[i]);
        }

        for(int i = 0; i < node.length; i++){
            Dfs_forgj df = (Dfs_forgj) node[i].d;
            System.out.println("Node " + i + " ferdig_tid: " + df.ferdig_tid);
        }



        //Sorterer node array med synkende ferdig-tid i forhold til orginal.
        for(int i = 0; i < node.length; i++){
            int hittilStorst = i;
            for (int h = i+1; h < node.length; h++){

                Dfs_forgj nd1 = (Dfs_forgj)orginal[h].d;
                Dfs_forgj nd2 = (Dfs_forgj)orginal[hittilStorst].d;

                if(nd1.ferdig_tid > nd2.ferdig_tid) hittilStorst = h;
            }
            Node hjelp = node[hittilStorst];
            node[hittilStorst] = node[i];
            node[i] = hjelp;
        }

        System.out.println("---SORTERT---");
        for(int i = 0; i < node.length; i++){
            Dfs_forgj df = (Dfs_forgj) node[i].d;
            System.out.println("Node " + i + " ferdig_tid: " + df.ferdig_tid);
        }


        //4. Kjøre DFS på den omvendte grafen.
        for(komp = 0; komp < node.length; komp++){
            dfs(node[komp]);
        }

    }

    public static void ny_ugraf(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        orginal = new Node[N];
        for(int i = 0; i < N; ++i){
            node[i] = new Node();
            orginal[i] = new Node();
        }
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());

            //Orginal
            Kant ka = new Kant(orginal[til], orginal[fra].kant1);
            orginal[fra].kant1 = ka;

            //Omvendt
            Kant k = new Kant(node[fra], node[til].kant1);
            node[til].kant1 = k;



        }
    }

    public static void initforgj(Node s){
        for(int i = N; i-- > 0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }

    public static void dfs_init(){
        for(int i = N; i-- > 0;){
            node[i].d = new Dfs_forgj();
        }
        Dfs_forgj.null_tid();
    }

    public static void df_sok(Node n){
        Dfs_forgj nd = (Dfs_forgj)n.d;
        //System.out.println("les_tid(): " + Dfs_forgj.les_tid());
        nd.funnet_tid = Dfs_forgj.les_tid();
        for(Kant k = n.kant1; k != null; k = k.neste){
            Dfs_forgj md = (Dfs_forgj)k.til.d;
            if(md.funnet_tid == 0){
                System.out.println("Komponent " + komp);
                System.out.println(Arrays.asList(node).indexOf(n));
                md.forgj = n;
                md.dist = nd.dist + 1;
                df_sok(k.til);
            }
        }
        nd.ferdig_tid = Dfs_forgj.les_tid();
    }

    public static void dfs(Node s){
        dfs_init();
        ((Dfs_forgj)s.d).dist = 0;
        df_sok(s);
    }
}
