import java.util.ArrayList;

public class CubeSum implements Comparable<CubeSum>{
    private final long c;private final int i,j;
    public CubeSum(int i,int j){ c=i*i*i+j*j*j; this.i=i; this.j=j; } 
    public String toString(){
        return i*i*i+"+"+j*j*j+"="+c;  
    }
    public int compareTo(CubeSum that){
        if(this.c-that.c < 0) return -1;
        if(this.c-that.c > 0) return 1;
        return 0;
    }

    public static void main(String args[]){
        int n=Integer.parseInt(args[0]);
        CubeSum cs[] = new CubeSum[n+1];
        for(int i=0;i<=n;i++){
            cs[i] = new CubeSum(i,i);
        }
        MinPQ<CubeSum> pq = new MinPQ<CubeSum>(cs);
       // pq.show();
        CubeSum last=null;
        ArrayList<CubeSum> d = new ArrayList<CubeSum>();
        int N=0,dupl=0;
        while(!pq.isEmpty()){
            ++N;
            CubeSum mn = pq.delMin();
            if(last != null && mn.compareTo(last) == 0){ ++dupl;// d.add(last); d.add(mn) ;
            }
            last = mn;
           // System.out.println(mn);
            if(mn.j < n){
                pq.insert(new CubeSum(mn.i,mn.j+1));
            }
        }
        System.out.println("Total : "+N);
        System.out.println("Duplicates : "+dupl);
       /* for(CubeSum x: d)
            System.out.println("\t"+x); */
    }
} 

