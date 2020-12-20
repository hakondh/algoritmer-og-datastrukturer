public class Node {
    String navn;
    Node neste;

    public Node(String navn, Node neste){
        this.navn = navn;
        this.neste = neste;
    }

    public String finnNavn(){ return navn; }

    public void setSiffer(String ny){navn = ny;}

    public Node finnNeste(){ return neste; }

}

