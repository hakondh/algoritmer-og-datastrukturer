public class Iterator {
    private Node plass;

    public Iterator(DobbelLenke l){
        plass = l.getHale();
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
        if(!slutt()) plass = plass.finnForrige();
    }

    public void trekkFraForrige(){
        plass.finnForrige().setSiffer(plass.finnForrige().siffer - 1);
    }
}
