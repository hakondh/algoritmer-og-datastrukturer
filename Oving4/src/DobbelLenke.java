public class DobbelLenke {
    private Node hode;
    private Node hale;
    private int størrelse;

    public int getStørrelse(){return størrelse;}

    public Node getHode(){return hode;}

    public Node getHale(){return hale;}

    public void settInnBakerst(int siffer) {
        Node ny = new Node(siffer, null, hale);
        if (hale != null) hale.neste = ny;
        else hode = ny;
        hale = ny;
        ++størrelse;
    }

    public void settInnForerst (int siffer){
        hode = new Node(siffer, hode, null);
        if(hale == null) hale = hode;
        else hode.neste.forrige = hode;
        ++størrelse;
    }
}
