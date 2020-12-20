/*
public class DFS {
    public void dfs_init(){
        for(int i = N; i-- > 0;){
            node[i].d = new Dfs_forgj();
        }
        Dfs_forgj.null_tid();
    }

    public void df_sok(Node n){
        Dfs_forgj nd = (Dfs_forgj)n.d;
        nd.funnet_tid = Dfs_forgj.les_tid();
        for(Kant k = n.kant1; k != null; k = k.neste){
            Dfs_forgj md = (Dfs_forgj)k.til.d;
            if(md.funnet_tid == 0){
                md.forgj = n;
                md.dist = nd.dist + 1;
                df_sok(k.til);
            }
        }
        nd.ferdig_tid = Dfs_forgj.les_tid();
    }

    public void dfs(Node s){
        dfs.init();
        ((Dfs_forgj)s.d).dist = 0;
        df_sok(s);
    }
}
*/