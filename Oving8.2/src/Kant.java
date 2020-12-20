public class Kant{
    //Neste kant fra denne noden
    Kant neste;

    //Noden denne kanten går til
    Node til;

    public Kant(Node n, Kant nst){
        til = n;
        neste = nst;
    }
}