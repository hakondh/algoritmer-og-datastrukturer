import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main(String[] args){
        String tall1 = showInputDialog("Tall 1: ");
        String[] operasjoner = {"Adder", "Subtraher"};
        int valg = showOptionDialog(null, "Addisjon eller subtraksjon?", null, 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);
        String tall2 = showInputDialog("Tall 2: ");

        DobbelLenke dobbelLenke1 = new DobbelLenke();
        DobbelLenke dobbelLenke2 = new DobbelLenke();

        for(int i = 0; i < tall1.length(); i++){
            dobbelLenke1.settInnBakerst(Character.getNumericValue(tall1.charAt(i)));
        }

        for(int i = 0; i < tall2.length(); i++){
            dobbelLenke2.settInnBakerst(Character.getNumericValue(tall2.charAt(i)));
        }

        //Iteratorer for traversering
        Iterator iterator1 = new Iterator(dobbelLenke1);
        Iterator iterator2 = new Iterator(dobbelLenke2);

        //Dobbbellenke for resultatet
        DobbelLenke res = new DobbelLenke();

        if(valg == 0) {
            int verdi;
            int rest = 0;
            while (!iterator1.slutt() || !iterator2.slutt() || rest == 1) {
                verdi = iterator1.finnElement() + iterator2.finnElement() + rest;
                System.out.println(verdi);
                if (verdi > 9) {
                    res.settInnBakerst((verdi % 10));
                    rest = 1;
                } else {
                    res.settInnBakerst(verdi);
                    rest = 0;
                }
                System.out.println(rest);
                iterator1.neste();
                iterator2.neste();
            }
        }
        //Subtraksjon, husk −(a−b) = b−a
        else if (valg == 1) {
            //https://www.mathsisfun.com/numbers/subtraction-by-addition.html
            //Ta komplement av tallet vi subtraherer, legger det til tallet vi subtraherer fra, fjerner ekstra 1 fra venstre
            DobbelLenke komplement = new DobbelLenke();
            boolean funnet = false;
            while(!funnet){
                if(iterator2.finnElement() != 0){
                    komplement.settInnForerst(-(iterator2.finnElement()-10));
                    funnet = true;
                    iterator2.neste();
                }
                else {
                    komplement.settInnForerst(0);
                    iterator2.neste();
                }
            }


            while(!iterator2.slutt()){
                komplement.settInnForerst(-(iterator2.finnElement()-9));
                iterator2.neste();
            }





            //Nå har vi komplement av tallet. Kan nå legge sammen. Må fjerne 1-tallet til venstre
            Iterator kompIter = new Iterator(komplement);
            /*
            int nr = 1;
            while(!kompIter.slutt()){
                System.out.println("nr " + nr + ": " + kompIter.finnElement());
                kompIter.neste();
                nr++;
            }*/

            int verdi;
            int rest = 0;
            while (!kompIter.slutt() || !iterator2.slutt() || rest == 1) {
                verdi = kompIter.finnElement() + iterator2.finnElement() + rest;
                System.out.println(verdi);

                if (verdi > 9) {
                    res.settInnBakerst((verdi % 10));
                    rest = 1;
                } else {
                    res.settInnBakerst(verdi);
                    rest = 0;
                }
                System.out.println(rest);
                kompIter.neste();
                iterator2.neste();
            }



            /*
            boolean lånt = false;
            while(!iterator1.slutt() || !iterator2.slutt()){

                if(lånt){

                }

                verdi = iterator1.finnElement() - iterator2.finnElement();
                if(verdi >= 0){
                    res.settInnBakerst(verdi);
                }
                //Var sifferet fra første tall mindre enn det andre må vi låne
                else {
                    res.settInnBakerst(verdi + 10);
                    lånt = true;
                }
           }
           */
        }


        //Sette sammen nytt tall, må iterere gjennom den nye dobbellenka
        String resultat = "";
        Iterator resIter = new Iterator(res);
        while(!resIter.slutt()){
            resultat += resIter.finnElement();
            resIter.neste();
        }
        System.out.println(resultat);
    }
}
