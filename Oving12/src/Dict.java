public class Dict {
    String
    int tilbake;
    char sisteTegn;

    public Dict(int tilbake, char sisteTegn){
        this.tilbake = tilbake;
        this.sisteTegn = sisteTegn;
    }

    public int getTilbake() {
        return tilbake;
    }

    public char getSisteTegn(){
        return sisteTegn;
    }
}
