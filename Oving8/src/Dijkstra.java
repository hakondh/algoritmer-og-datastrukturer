import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {
    static int N, K;
    static Node[] node;
    static BufferedReader leser;
    static int len;
    static Node[] pri;
    static int nylen;

    public static void main (String[] args){
        //Finne fil, opprette ny vgraf.
        try{

            File fil = new File("C:\\Algoritmer og datastrukturer\\Oving8\\src\\vg1.txt");
            leser = new BufferedReader(new FileReader(fil));
            ny_vgraf(leser);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Fil ikke funnet.");
        }

        len = node.length;

        for(int i = 0; i < node.length; i++){
            System.out.println(node[i]);
        }

        dijkstra(node[1]);

        System.out.println("Node | Forgjenger | Distanse");
        for(int i = 0; i < node.length; i++){
            Forgj f = (Forgj) node[i].d;
            System.out.println(i + "     " + f + "   " + f.dist);
        }

    }

    public static void ny_vgraf(BufferedReader br) throws IOException{
        //Legge inn verdiene fra fil i ny vgraf.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i < N; ++i) {
            node[i] = new Node();
            node[i].navn = String.valueOf(i);
        }
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; ++i){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            int vekt = Integer.parseInt(st.nextToken());
            VKant k = new VKant(node[til], (VKant)node[fra].kant1, vekt);
            node[fra].kant1 = k;
        }
    }

    public static void dijkstra(Node s) {
        initforgj(s);
        pri = new Node[N];
        lag_heap();
        for (int i = N; i > 1; --i) {
            Node n = hent_min(i, pri);
            for (VKant k = (VKant) n.kant1; k != null; k = (VKant) k.neste) forkort(n, k);
        }
    }

    public static void forkort(Node n, VKant k) {
        Forgj nd = (Forgj) n.d, md = (Forgj) k.til.d;
        if (md.dist > nd.dist + k.vekt) {
            md.dist = nd.dist + k.vekt;
            md.forgj = n;
        }
    }



    public static Node hent_min(int i, Node[] pri){
        Node min = node[0];
        node[0] = node[--len];
        fiks_heap(0);
        return min;
    }

    private static void lag_heap(){
        int i = len / 2;
        while(i-- > 0) fiks_heap(i);


        System.out.println();
    }

    private static void fiks_heap(int i){
        int m = venstre(i);
        if(m < len){
            int h = m + 1;

            /*
            VKant hvk = (VKant) node[h].kant1;
            VKant mvk = (VKant) node[m].kant1;
            VKant ivk = (VKant) node[i].kant1;
            */

            if(h < len && h < m) m = h;
            if(m > i){
                bytt(node, i, m);
                fiks_heap(m);
            }
        }
    }

    private static int venstre(int i){
        return (i << 1) +1;
    }

    public static void bytt(Node[] t, int i, int j){
        Node k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

    public static void initforgj(Node s){
        for(int i = N; i-- > 0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }



}
