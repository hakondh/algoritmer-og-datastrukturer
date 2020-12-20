import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Innpakking {
    BufferedReader leser;
    int kapasitet = 127;
    ArrayList<Dict> dict = new ArrayList<Dict>();
    int teller = 0;

    private void les () {
        try {

            File fil = new File("C:\\Algoritmer og datastrukturer\\Oving12\\src\\tekst.txt");
            leser = new BufferedReader(new FileReader(fil));
            ny_vgraf(leser);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fil ikke funnet.");
        }
    }

    private String komprimer (String input){
        char tegn;
        String s = ""; //Komprimerbar del av input for øyeblikket.
        String referanse = ""; //Referanse som brukes til komprimering.
        String output = ""; //Output, som skal returneres til slutt.

        //Går gjennom filen til input.length = 0, altså vi har nådd slutten av filen:
        while(input.length() > 0){
            tegn = input.charAt(0); //Neste tegn i input
            input = input.substring(1); //Fjerner tegnet vi behandler fra input, så den ikke er med i neste iterasjon.

            //Hvis det bare er et tegn skal vi ikke komprimere det. Legges bare til i s.
            if((s + tegn).length() < 2) s += tegn;

            //Hvis det er mer enn ett tegn gjør vi noen sjekker:
            else{
                //Ser om vi finner en referanse til (s + det nye tegnet). (Mulig komprimering)
                referanse = finnReferanse(s + tegn);
                /*
                Er referanse != null har vi funnet en referanse for (s + tegn).
                Kan da legge tegnet til i s, for å sjekke om vi kan komprimere ytterligere ved neste iterasjon.
                */
                if(referanse != null){
                    s += tegn;
                }
                /*
                Finner vi ikke en referanse til (s + tegn), så har vi komprimert s så mye vi kan => s må legges i
                output.
                Tegnet vi prøvde å legge til er fremdeles ikke komprimert => Setter s til tegn.
                */
                else {
                    output += s;
                    s = tegn + "";
                }
            }
        }
        return output;
    }

    private String finnReferanse(String del){

        String referanse = null;
        int i = 0;
        /*
            Så lenge det er mer av filen OG vi ikke har gått lenger tilbake enn vi kan OG vi ikke har sjekket del
            fullstendig OG vi ikke har funnet , så sjekker vi om det finnes en referanse for del.
         */
        while(referanse == null && i < kapasitet){

        }
    }



}
