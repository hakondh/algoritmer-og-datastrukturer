import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Dijkstra {
    static int N, K;
    static Node[] node;
    static BufferedReader leser;

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

        //Startnode
        int start = 0;
        dijkstra(node[1]);

        System.out.println("Node | Forgjenger | Distanse");
        for(int i = 0; i < node.length; i++){
            String fp;
            Forgj f = (Forgj) node[i].d;
            Node fn = f.finn_forgj();
            String forgjenger;

            if(f.dist == 1000000000) {
                fp = "Nåes ikke.";
            }
            else{
                fp = String.valueOf(f.dist);
            }

            //Sjekk om noden er en start-node
            if(i == start){
                forgjenger = "Start";
            }
            //Sjekk om noden ikke har forgjenger
            else if(i != start && fn == null){
                forgjenger = "";
            }
            //Kommer man hit henter man forgjengeren
            else {
                forgjenger = String.valueOf(f.finn_forgj().indeks);
            }

            //Utskrift
            System.out.println(i + "      " + forgjenger + "            " + fp);
        }

        System.out.println("Korteste vei fra node 2 til node 1:");
        int lengde = 0;
        int index;
        Forgj fg = (Forgj) node[2].d;
        while(fg.dist != 0){
            index= fg.finn_forgj().indeks;
            System.out.println(index);
            fg = (Forgj) node[index].d;
        }
    }

    static void ny_vgraf(BufferedReader br) throws IOException{
        //Legge inn verdiene fra fil i ny vgraf.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i < N; ++i) {
            node[i] = new Node();
            node[i].indeks = i;
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

    static void dijkstra(Node s) {
        initforgj(s);
        Node[] pri = new Node[N];
        lag_priko(pri);
        for (int i = N; i > 1; --i) {
            Node n = hent_min(i, pri);
            for (VKant k = (VKant) n.kant1; k != null; k = (VKant) k.neste) forkort(n, k);
        }
    }

    //Lager en prioritetskø ved å kopiere alle noder over i en prioritetstabell p
    static void lag_priko(Node[] p) {
        for (int i = 0; i < N; ++i) {
            p[i] = node[i];
        }
    }

    static Node hent_min(int gjenstående, Node[] p) {
        int hittilMinste = 0;
        //Gå gjennom alle gjenstående noder og finne den som har minst distanse til startnode. Starter på største indeks
        for (int i = gjenstående - 1; i > 0; i--) {
            if (((Forgj)p[i].d).dist < ((Forgj)p[hittilMinste].d).dist) hittilMinste = i;
        }
        //Henter ut den minste, og legger inn prioritetkøens siste node på den ledige plassen så vi ikke får noe hull i prioritetskø
        Node minste = p[hittilMinste];
        p[hittilMinste] = p[gjenstående - 1];
        return minste;
    }

    static void forkort(Node n, VKant k) {
        //Forgjengeren til min-noden
        Forgj nd = (Forgj) n.d;

        //Forgjenger av kant-noden til min-noden
        Forgj md = (Forgj) k.til.d;

        //Hvis det er raskere å gå veien via kanten (n, m), enn veien allerede funnet, så endrer vi distanse og forgjenger
        //så vi går denne veien.
        if (md.dist > nd.dist + k.vekt) {
            md.dist = nd.dist + k.vekt;
            md.forgj = n;
            md.forgj.indeks = n.indeks;
        }
    }


    static void initforgj(Node s){
        for(int i = N; i-- > 0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }
}
