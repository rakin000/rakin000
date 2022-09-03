import java.util.Scanner;

class QuickFindUF {
    private int[] id;
    public QuickFindUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++) id[i] = i;
    }

    public boolean connected(int p, int q){
        return id[p]==id[q];
    }

    public void union(int p, int q){
        int pid=id[p];
        int qid=id[q];
        int N=id.length;
        if(p>=N || p<0 || q>=N || q<0){
            System.out.println("Error!");
            return ;
        }
        for(int i=0;i<id.length;i++){
            if(id[i]==pid) id[i] = qid;
        }
    }
}

class QuickUnionUF{
    private int id[];
    public QuickUnionUF(int n){
        id=new int[n];
        for(int i=0;i<n;id[i++]=i)
            ;
    }
    private int root(int i){
        while(i != id[i])
            i=id[i] ;
        return i;
    }

    public boolean connected(int p,int q){
        return root(p)==root(q);
    }
    public void union(int p,int q){
        int i=root(p);
        int j=root(q);
        id[i]=j;
    }
}
class WeightedQuickUnion{
    private int id[],sz[];
    public WeightedQuickUnion(int n){
        id=new int[n];
        sz=new int[n];
        for(int i=0;i<n;i++){id[i]=i; sz[i]=1;}
    }
    private int root(int i){
        while(i!=id[i]){ 
            id[i]=id[id[i]];
            i=id[i]; 
        }
        return i;
    }
    public boolean connected(int p,int q){
        return root(p)==root(q);
    }
    public void union(int p,int q){
        int i=root(p);
        int j=root(q);
        if(i==j) return ;
        if(sz[i]>sz[j]){
            id[j]=i; sz[i]+=sz[j];
        }
        else{
            id[i]=j; sz[j]+=sz[i];
        }
    }
}
public class unionfind {
    public static void main(String arg[]){
        Scanner StdIn = new Scanner(System.in);
        int N = StdIn.nextInt();
        QuickFindUF qf = new QuickFindUF(N);
        while(true){
            String c; int p,q;
            c=StdIn.next();
            p=StdIn.nextInt();
            q=StdIn.nextInt();
            if(c.charAt(0)=='u') qf.union(p,q);
            else if(c.charAt(0)=='c') System.out.println(qf.connected(p,q));

        }
    }
}