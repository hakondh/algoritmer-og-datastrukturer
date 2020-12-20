public class Heapsort extends Sortering{
    private int len;
    private int node[];

    @Override
    public String getNavn(){
        return "Heapsort";
    }

    @Override
    public int[] sorter(int[] arr, int lowIndex, int highIndex) {
        node = arr;
        len = arr.length;

        lag_heap();
        int l = len;
        while(len > 1){
            int x = hent_maks();
            node[len] = x;
        }
        len = l;

        return node;
    }

    private int hent_maks(){
        int maks = node[0];
        node[0] = node[--len];
        fiks_heap(0);
        return maks;
    }

    private void lag_heap(){
        int i = len / 2;
        while(i-- > 0) fiks_heap(i);
    }

    private void fiks_heap(int i){
        int m = venstre(i);
        if(m < len){
            int h = m + 1;
            if(h < len && node[h] > node[m]) m = h;
            if(node[m] > node[i]){
                bytt(node, i, m);
                fiks_heap(m);
            }
        }
    }

    //Posisjonsberegninger
    private int over(int i){
        return (i - 1)>>1;
    }

    private int venstre(int i){
        return (i << 1) +1;
    }

    private int hoyre(int i){
        return (i+1)<<1;
    }

    public static void bytt(int[] t, int i, int j){
        int k = t[j];
        t[j] = t[i];
        t[i] = k;
    }

}
