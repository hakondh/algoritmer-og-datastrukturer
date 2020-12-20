public class Node {
    int siffer;
    Node neste;
    Node forrige;

    public Node(int siffer, Node neste, Node forrige){
        this.siffer = siffer;
        this.neste = neste;
        this.forrige = forrige;
    }

    public int finnSiffer(){ return siffer; }

    public void setSiffer(int ny){siffer = ny;}

    public Node finnNeste(){ return neste; }

    public Node finnForrige(){ return forrige; }


}

