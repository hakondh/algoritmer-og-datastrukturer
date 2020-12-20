import javax.swing.*;
import java.util.Arrays;

public class Hashtabell {
    static String[] navn = {"Almås Annabelle Solem", "Andersson William", "Andersson Vegard", "Andresen Sebastian Martin", "Aune Jostein Johansen", "Axell Christian", "Daniel Larsen",
            "Bakhmadov Magomed Khatujevitsj", "Braathen Mathias", "Bui Aria Thuy Dung", "Bø Halvor Fladsrud", "Christiansen Herman", "Dahl Magnus", "Dalseth Christian Mathias", "Debik Karol Jerzy",
            "Elvemo Sebastian Aanesland", "Fossland Ole-Henrik", "Frantzen Odd-Erik", "Fridheim Marius", "Fylling Johan-Henrik", "Garmann Ingelin", "Gram Hans-Jeiger", "Habeeb Khaled Mohammad",
            "Halvarsson Kevin Mentzoni", "Haugum Terje", "Helgeland Kevin Andre", "Hestnes Håkon Dalen", "Hjelle Sara", "Holt Eyvind Nikolai", "Hynne Sigurd", "Iversen Anders Hallem",
            "Jacobsen Sigurd Lø¸ite", "Jacobsen William Chakroun", "Johansen Aleksander", "Johansen Kristaver Birkeland", "Johansen Eivind Alfsvåg", "Kampenhå y Kristian", "Knutsen Yair Day",
            "Knutsen Mathias", "Koteng Markus Thomassen", "Kristoffersen Jonas", "Larsen Knut Johan", "Larsen Albert Ohrem", "Larsson, Øivind Haugerø", "Lervik Eivind Hestnes",
            "Li Jingyi", "Lilleeng Simon", "Martinsen Magnus Revheim", "Martinsen Herman Ryen", "Mediå Fredrik", "Mellemseter Michael", "Midttun Jonathan", "Moan Martin Andreas", "Monsen Fredrik",
            "Nicolausson Sander", "Nordseth Morten Nyang", "Nygård Marius", "Nystuen Ådne", "Opsahl Elisabeth Marie", "Paulshus Sindre Haugland", "Rad Saadat Ilia", "Ramberg Håvard Hammer", "Roll Erling",
            "Rondestvedt Trond Jacob", "Rø stgård Kim Richard", "Sandnes Martin", "Siiri Anette Olli", "Skaug Oscar Tideman Borgerud", "Stavseng Ådne Eide", "Strand Snorre Kristoffer", "Strø mhylden Ben Oscar",
            "Sundø y Erlend", "Søther Ane", "Sørlie Lars", "Teiler-Johnsen Mari", "Tengs Simen Sølevik", "Thomassen Sindre", "Thorkildsen Patrick", "Timdal Eivind Rui", "Tokvam Balder",
            "Tran Quan Nguyen", "Tverfjell Julie Isabelle Malmedal", "Ullah Mona", "Valen Ruben Solvang", "Valstadsve Øyvind", "Vatle Jan-Marius", "Vedøy Rune", "Vesterlid Ørjan Bostad",
            "Wangensteen Kim Anners", "Wichstrøm Brage", "Worren Magne", "Østtveit Bjørnar", "Øverland Sveinung", "Øvsthus Vebjørn Hansen", "Ådnanes Stian", "Aasvestad Jørgen"};


    static int antKollisjoner = 0;
    static String currentNavn = "";

    public static void main (String[] args){
        int lengde = (int) (navn.length * 1.5);
        Node[] ht = new Node[lengde];

        //Legger inn hashede heltall i hashtabellen
        for(int i = 0; i < navn.length; i++){
            currentNavn = navn[i];
            int verdi = legginn(navn[i], ht);

            if(verdi == -1){
                System.out.println(navn[i] + " kunne ikke legges inn.");
            }
        }

        System.out.println("---Hashtabell---");
        for (int i = 0; i < ht.length; i++) {
            if (ht[i] != null) {
                Node start = ht[i];
                while (start != null) {
                    System.out.println(start.finnNavn());
                    start = start.neste;
                }
            }
        }
        System.out.println("---Info---");
        System.out.println("ANTALL NAVN: " + navn.length);
        System.out.println("TOTALT ANTALL KOLLISJONER: " + antKollisjoner);
        System.out.println("GJENNOMSNITTLIG ANTALL KOLLISJONER PER PERSON: " + (double)antKollisjoner/(double)navn.length);
        System.out.println("LASTFAKTOR: " + ((double)navn.length/(double)ht.length));

        String navn = JOptionPane.showInputDialog(null, "Søk etter navn: ");
        System.out.println(navn + " ligger på indeks " +  finnPos(navn, ht));


    }

    //Konverterer navnet (String) til et heltall
    private static int konverterNavn(String navnet){
        int heltall = 0;
        for(int i = 0; i < navnet.length(); i++){
            heltall += (int) navnet.charAt(i) * (i + 1);
        }
        return heltall;
    }

    //Hasher heltallet
    private static int hashfunk(int k, int m){
        return k % m;
    }

    //Gjør om heltall til hashed verdi og legger det inn i hashtabell
    private static int legginn(String navn, Node[] ht){
        int m = ht.length;
        int h = hashfunk(konverterNavn(navn), m);
        Node n = new Node(navn, null);
        if(ht[h] == null) {
            ht[h] = n;
        }
        else {
            System.out.println("Kollisjon mellom " + ht[h].finnNavn() + " (ligger i tabellen) og " + currentNavn);
            n.neste = ht[h];
            ht[h] = n;
            antKollisjoner++;
        }
        return h;
    }

    private static int finnPos(String navn, Node[] ht){
        int m = ht.length;
        int indeks = hashfunk(konverterNavn(navn), m);
        return indeks;
    }

    //Legger hashet verdi en indeks lenger bort om man skulle treffe en indeks som allerede er i bruk
    private static int probe(int h, int i, int m){
        return (h + i) % m;
    }

}
