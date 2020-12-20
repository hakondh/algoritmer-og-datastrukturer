public class IteratorForfra {
    private Node plass;

    public IteratorForfra(DobbelLenke l){
        plass = l.getHode();
    }

    public boolean slutt(){
        return plass == null;
    }

    public int finnElement(){
        if(!slutt()){
            return plass.finnSiffer();
        }
        else return 0;
    }

    public void neste(){
        if(!slutt()) plass = plass.finnNeste();
    }
}
