
public class Solution {
    public static String solution(String s) {
        String braille[]  = new String[28] ;
        braille['a'-'a'] = "100000";
        braille['b'-'a'] = "110000";
        braille['c'-'a'] = "100100" ;
        braille['d'-'a'] = "100110" ;
        braille['e'-'a'] = "100010" ;
        braille['f'-'a'] = "110100" ;
        braille['g'-'a'] = "110110" ;
        braille['h'-'a'] = "110010" ;
        braille['i'-'a'] = "010100" ;
        braille['j'-'a'] = "010110" ;
        braille['k'-'a'] = "101000";
        braille['l'-'a'] = "111000";
        braille['m'-'a'] = "101100" ;
        braille['n'-'a'] = "101110" ;
        braille['o'-'a'] = "101010" ;
        braille['p'-'a'] = "111100" ;
        braille['q'-'a'] = "111110" ;
        braille['r'-'a'] = "111010" ;
        braille['s'-'a'] = "011100" ;
        braille['t'-'a'] = "011110" ;
        braille['u'-'a'] = "101001";
        braille['v'-'a'] = "111001";
        braille['w'-'a'] = "010111" ;
        braille['x'-'a'] = "101101" ;
        braille['y'-'a'] = "101111" ;
        braille['z'-'a'] = "101011" ;
        braille[26] = "000000" ;
        braille[27] = "000001" ;
        StringBuilder sol = new StringBuilder();
        for(char c: s.toCharArray() ){
            if( Character.isUpperCase(c) ){
                sol.append(braille[27]);
                sol.append(braille[c-'A']);
            }
            else if( Character.isLowerCase(c) )
                sol.append(braille[c-'a']);
            else if( Character.isWhitespace(c) )
                sol.append(braille[26]) ;
        }
        
        return sol.toString();
    }

 
    public static int[] solution(int[] pegs) {
        int sol[] = new int[2];
        int lsum = 0; int sign = 1;
        for(int i=0;i<pegs.length-1;i++) {
            lsum += sign*(pegs[i+1]-pegs[i] );
            sign = (-1)*sign;
        }
        
        sol[0] = lsum*2;
        
        if( pegs.length%2 == 0 ){
            if( sol[0]%3 == 0){
                sol[0] /= 3;
                sol[1] = 1;
            }
            else sol[1] = 3;
        }
        else sol[1] = 1;
        
        
        int i,a=sol[0],b=sol[1];
        for(i=0;i<pegs.length-1;i++) {
            int len = pegs[i+1]-pegs[i];
            if(a >= b && a < b*len){}
            else break;            
            a = len*b-a;
        }
        if( i != pegs.length-1) sol[0] = sol[1] = -1;
        return sol;
    }

    private static int solve(int x){
        int exp = (int)(Math.log(x+1.0)/Math.log(2.0) ) ;
        int nx = x - (1<<exp)+1;

        if( nx == 0 ) return (1<<exp) ;
        if( x == 2*nx ) return 1;
        return solve(nx); 
    }
    public static int[] solution(int h,int[] q){
        int p[] = new int[q.length];

        for(int i=0;i<q.length;i++){
            p[i] = q[i]+solve(q[i]) ;
            p[i] = (p[i] >= (1<<h) ) ? -1 : p[i]; 
        }

        return p;
    } 
    public static void main(String[] args){
        int h = Integer.parseInt(args[0]);
        int arr[] = new int[args.length-1];
        for(int i=1;i<args.length;i++){
            arr[i-1] = Integer.parseInt(args[i]);
        }

        for(int x: solution(h,arr))
            System.out.print(x+" ");
    }
}