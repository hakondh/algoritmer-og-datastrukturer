class RelationProperties {
    /*
     * Assuming that a two column array containing the relation and a one column          * array containing the set the relation is on is given in each method.
     * No checks are performed.
     */

    public static boolean isReflexive(char[][] relation, char [] set){
        char t;
        int teller = 0;
        for(int i = 0; i < set.length; i++){
            t = set[i];
            //Går gjennom alle element i settet, sjekker så om dette elementet har en refleksiv relalasjon. Hvis man finner denne relasjonen plusser man på telleren.
            for(int h = 0; h < relation.length; h++){
                if(relation[h][0] == (t) && relation[h][1] == t) {
                    teller++;
                    continue;
                }
            }
        }
        //Hvis man har funnet like mange refleksive relasjoner som det er elementer er den refleksiv.
        if(teller == set.length) return true;
        return false;
    }

    public static boolean isSymmetric(char[][] relation, char [] set){
        char t1;
        char t2;
        boolean funnet;
        for(int i = 0; i < relation.length; i++){
            t1 = relation[i][0];
            t2 = relation[i][1];

            funnet = false;
            //Går gjennom alle relasjonene og ser om man finner en relasjon hvor t1 og t2 står motsatt, altså man har en relasjon beggee veier. funnet = true om man finner denne relasjonen.
            for(int h = 0; h < relation.length; h++){
                if(relation[h][0] == t2 && relation[h][1] == t1) {
                    funnet = true;
                    break;
                }
            }
            if(!funnet) return false;
        }
        return true;
    }

    public static boolean isTransitive(char[][] relation, char [] set){
        char t1;
        char t2;
        char t3;
        for(int i = 0; i < relation.length; i++){
            t1 = relation[i][0];
            t2 = relation[i][1];
            //Hvis denne relasjonen IKKE er refleksiv sjekker vi for transitivitet (tallene i relasjonen er ikke like)
            if(t1 != t2){
                for(int h = 0; h < relation.length; h++){

                    //Hvis t2 har en relasjon til et annet tall som ikke er t1 eller seg selv, så må t1 også ha en relasjon til dette tallet, om relasjonen skal være transitiv. Sjekker om t2 har en annen relasjon:
                    if(relation[h][0] == t2 && relation[h][1] != t2 && relation[h][1] != t1) {
                        //Hvis det t2 har en annen relasjon sjekker vi om t1 også har relasjon til dette tallet t3:
                        boolean funnet = findTransitiveRelation(relation, t1, relation[h][1]);
                        if(!funnet) return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean findTransitiveRelation(char[][] relation, char t1, char t3){
        for(int i = 0; i < relation.length; i++){
            if(relation[i][0] == t1 && relation[i][1] == t3) return true;
        }
        return false;
    }

    public static boolean isAntiSymmetric(char[][] relation, char [] set){
        char t1;
        char t2;
        for(int i = 0; i < relation.length; i++){
            t1 = relation[i][0];
            t2 = relation[i][1];

            //Sjekker om relasjonen har en relasjon (t2, t1), hvor t1 != t2
            for(int h = 0; h < relation.length; h++){
                if(relation[h][0] == t2 && relation[h][1] == t1 && t2 != t1) return false;
            }
        }
        return true;
    }

    public static boolean isEquivalenceRelation(char[][] relation, char [] set){
        if(isReflexive(relation, set) && isSymmetric(relation, set) && isTransitive(relation, set)) return true;
        return false;
    }

    public static boolean isPartialOrder(char[][] relation, char [] set){
        //Må være refleksiv, antisymmmetrisk og transitiv
        if(isReflexive(relation, set) && isAntiSymmetric(relation, set) && isTransitive(relation, set)) return true;
        return false;
    }

    public static void main(String[] args) {
        char[] setA = {'a','x','r','m','2','0'};

        char[][] rel1 = {{'a','a'},{'r','a'},{'a','2'},{'x','x'},{'r','2'},{'r','r'},{'m','m'},{'2','r'},{'0','0'},{'a','r'},{'2','2'},{'2','a'}};

        char[][] rel2 = {{'a','x'},{'r','2'},{'0','0'},{'m','2'}};

        System.out.println("Rel1 is reflexive: " + isReflexive(rel1, setA));
        System.out.println("Rel2 is reflexive: " + isReflexive(rel2, setA));
        System.out.println("Rel1 is symmetric: " + isSymmetric(rel1, setA));
        System.out.println("Rel2 is symmetric: " + isSymmetric(rel2, setA));
        System.out.println("Rel1 is transitive: " + isTransitive(rel1, setA));
        System.out.println("Rel2 is transitive: " + isTransitive(rel2, setA));
        System.out.println("Rel1 is antisymmetric: " + isAntiSymmetric(rel1, setA));
        System.out.println("Rel2 is antisymmetric: " + isAntiSymmetric(rel2, setA));
        System.out.println("Rel1 is an equivalence relation: " + isEquivalenceRelation(rel1, setA));
        System.out.println("Rel2 is an equivalence relation: " + isEquivalenceRelation(rel2, setA));
        System.out.println("Rel1 is a partial order: " + isPartialOrder(rel1, setA));
        System.out.println("Rel2 is a partial order: " + isPartialOrder(rel2, setA));

	/* skal gi følgende utskrift:
	   Rel1 is reflexive: true
	   Rel2 is reflexive: false
	   Rel1 is symmetric: true
	   Rel2 is symmetric: false
	   Rel1 is transitive: true
	   Rel2 is transitive: true
	   Rel1 is antisymmetric: false
	   Rel2 is antisymmetric: true
	   Rel1 is an equivalence relation: true
	   Rel2 is an equivalence relation: false
	   Rel1 is a partial order: false
	   Rel2 is a partial order: false
	 */
    }


}