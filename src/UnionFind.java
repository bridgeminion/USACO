public class UnionFind {

    int num_nodes;
    public int[] par;
    public int[] size;
    public int num_components;

    public UnionFind (int nodes) {
        num_nodes = nodes;
        par = new int[num_nodes+1];
        size = new int[num_nodes+1];
        for (int i=1; i <= nodes; i++){
            par[i] = i;
            size[i] = 1;
        }
        num_components = num_nodes;
    }


    public int root (int i) {
        while (par[i] != i){
            par[i] = par[par[i]];
            i = par[i];
        }
        return i;
    }

    public void union (int a, int b){
        int root_a = root (a);
        int root_b = root (b);
        if (root_a != root_b){
            if (size[root_a] > size[root_b]){
                par[root_b] = par[root_a];
                size[root_a] += size[root_b];
            }
            else {
                par[root_a] = par[root_b];
                size[root_b] += size[root_a];
            }
            num_components--;
        }
    }

    public boolean find (int a, int b){
        return root (a) == root (b);
    }
}
