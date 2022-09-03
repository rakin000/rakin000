import java.util.Random;

class sorters{
    public static void swap(Comparable[] a,int i,int j){
        Comparable tmp=a[i];
        a[i]=a[j];
        a[j]=tmp;
    }
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
}
    
class Insertion extends sorters{
    public static void sort(Comparable[] arr,int l,int r){
        for(int i=l;i<=r;i++)
            for(int j=i;j>0 && less(arr[j],arr[j-1]);j--)
                swap(arr,j,j-1);
    }
    public static void sort(Comparable [] arr){
        sort(arr,0,arr.length-1);
    }

    public static void shuffle(Comparable[] arr){
        Random rand=new Random();
        for(int i=arr.length-1;i>0;i--){
            int j=rand.nextInt(i+1);
            swap(arr,i,j);
        }
    }
}
   
class Selection extends sorters{
        static void sort(Comparable[] arr){
            for(int i=0;i<arr.length;i++){
                int id=i;
                Comparable mx=arr[i];
                for(int j=i+1;j<arr.length;j++){
                    if(less(arr[j],mx)){
                        mx=arr[j];
                        id=j;
                    }
                }
                swap(arr,id,i);
            }
        }
    }

class Merge extends sorters{
    private static int thresh=3;
    static void merge(Comparable[] arr,Comparable[] aux,int l,int mid,int r){
        for(int k=l;k<=r;k++)
            aux[k]=arr[k];
        int i=l,j=mid+1;
        for(int k=l;k<=r;k++){
            if( j > r ) arr[k] = aux[i++] ;
            else if( i > mid ) arr[k] = aux[j++] ;
            else if(less(aux[i],aux[j]) ) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }
    
    static void sort(Comparable[] arr,Comparable aux[],int l,int r){
        if(r-l+1<=thresh){
            Insertion.sort(arr,l,r);
            return ; 
        }
        int mid=l+(r-l)/2;            
        sort(arr,aux,l,mid);
        sort(arr,aux,mid+1,r);   
        if(mid < r && less(arr[mid],arr[mid+1])){
            return;
        }
        merge(arr,aux,l,mid,r);
        return ;
    }

    static void sort(Comparable a[]){
        Comparable aux[]=new Comparable[a.length];
        sort(a,aux,0,a.length-1);
    }

}

class Quick extends sorters{
    private static int CUTOFF=4;
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static int partition(Comparable arr[],int l,int r){
        int lo=l,i=l,j=r;
        while(true){
            while( i<r && less(arr[i],arr[lo]))
                ++i;
            while( j>l && !less(arr[j],arr[lo]))
                --j;
            if(i>=j) break;
            swap(arr,i,j);
        } 
        swap(arr,j,lo);
        return j;
    }

    public static void sort3way(Comparable arr[],int l,int r){
        if(l>=r) return ;
        int lt=l,gt=r,i=l;
        Comparable v = arr[l];
        while(i<=gt){
            int cmp = arr[i].compareTo(v);
            if(cmp < 0)  swap(arr,i++,lt++);
            else if (cmp > 0) swap(arr, i, gt--);
            else i++ ;
        }

        sort3way(arr,l,lt-1);
        sort3way(arr,gt+1,r);
        return ;
    }

    public static Comparable select(Comparable arr[],int k){
        Insertion.shuffle(arr);
        int l=0,r=arr.length-1,j;
        while(l<r){
            j = partition(arr,l,r);
            if(j > k) r=j-1;
            else if(j < k) l=j+1;
            else return arr[k];
        }
        return arr[l];
    }
    public static void sort_(Comparable[] arr,int l,int r){
        if(l>=r) return ;
        if(r-l+1<=CUTOFF){
            Insertion.sort(arr,l,r);
            return ;
        }
        int m=median3(arr,l,l+(r-l)/2,r); //optimized;
        swap(arr,m,l);
        int lo=partition(arr,l,r);
        sort_(arr,l,lo-1);
        sort_(arr,lo+1,r);
        return;
    }

    public static void sort(Comparable[] arr){
        sort(arr,0,arr.length-1);
    }

    public static void sort(Comparable[] arr,int l,int r){
        Insertion.shuffle(arr);
        sort_(arr,l,r);
    }
}

class Heap{
    public static void sort(Comparable pq[]){
        int N = pq.length-1;
        for(int k=N/2;k>=0;k--)
            sink(pq,k,N);
        
        while(N > 0){
            swap(pq,0,N--);
            sink(pq,0,N);
        }
    }
    private static void swap(Comparable arr[],int i,int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static boolean less(Comparable arr[],int i,int j){
        return arr[i].compareTo(arr[j]) < 0;
    }
    private static void sink(Comparable pq[],int k,int N){
        while(2*k+1 <= N){
            int j = 2*k+1;
            if(j < N && less(pq,j,j+1) ) j++;
            if(!less(pq,k,j)) break;
            swap(pq,k,j);
            k=j;
        }
    }
}

public class sorting_algos{
    public static void main(String args[]){
       // Scanner StdIn = new Scanner(System.in);
        
        Integer arr[] = {1,2,24,52,245,251,1414,1,21,34};
 /*       Insertion.sort(arr);
        for(int x: arr)
            System.out.print(x+" ");
        System.out.println();
        Merge.sort(arr);
        for(int x: arr)
            System.out.print(x+" ");
        System.out.println();
        Insertion.shuffle(arr);
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
        Quick.sort(arr,0,arr.length-1);
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
*/
        Heap.sort(arr);
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
        Integer a[] = {13,52,423,214,155,124,5};
        System.out.println(Quick.select(a,14132));
    }
}

