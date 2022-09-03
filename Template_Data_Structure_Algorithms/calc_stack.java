import java.util.Scanner;


public class calc_stack {
    class Token{
        static final int NUMBER = 0;
        static final int ADD = 1;
        static final int SUB = 2;
        static final int MUL = 3;
        static final int DIV = 4;
        static final int LEFT_BRACE = 5;
        static final int RIGHT_BRACE = 6;

        public double value;
        public int type;
        
        Token(double value,int type){
            this.value = value;
            this.type = type;
        }

        public String toString(){
            if( type == NUMBER )    return Double.toString(value);
            else if( type == ADD )  return "+";
            else if( type == SUB )  return "-";
            else if( type == MUL )  return "*";
            else if( type == DIV)   return "/";
            else if( type == LEFT_BRACE) return "(";
            else if( type == RIGHT_BRACE) return ")";
            return "";
        }
    }

    stack<Double> value;
    stack<Character> op;
    array_list<Token> tokens;
    
    double result;
    boolean valid = true;
    StringBuffer validityLog = new StringBuffer(); 
    

    public calc_stack(String s){
        tokens = new array_list<Token>();
        tokenize(s);    
        valid = true;
        checkValidity();

        if( valid ){
            System.out.println("Valid expression"); 
            value = new stack<>();
            op = new stack<>();
            calculate();
            System.out.println(result);  
        }
        else System.out.println("Invalid expression");
        System.out.print(validityLog);
    }   

    private double toNumber(String s){
        double num = 0.0;
        int i = 0;
        double mul = 1.0;
        
        if( s.charAt(i) == '-'){
            mul = -1.0;
            i++;
        }
        while( i < s.length() && Character.isDigit(s.charAt(i)))
            num = num*10.0 + s.charAt(i++)-'0' ;

        if( i < s.length() && s.charAt(i) == '.' )
            i++;
        
        double div = 1.0;

        while( i < s.length() && Character.isDigit(s.charAt(i))){
            num = num*10.0 + s.charAt(i++)-'0' ;
            div *= 10.0;
        }        
        num = mul*num/div;
        //System.out.println(num);
        return num;
    }


    private void tokenize(String s){
        int i = 0;
        StringBuffer num;// = new StringBuffer();
        while( i < s.length() ){
            if( s.charAt(i) == ' ' ) {i++; continue; }

            
            if( s.charAt(i) == '(' || 
                s.charAt(i) == ')' || 
                s.charAt(i) == '+' || 
                s.charAt(i) == '-' ||
                s.charAt(i) == '*' ||
                s.charAt(i) == '/'      )
                tokens.add( new Token(s.charAt(i++)+"",) ;   
            else if(    Character.isDigit( s.charAt(i) ) ){
                int mul = 1;

                while( !tokens.empty() && (tokens.getLast().charAt(0) == '+' || tokens.getLast().charAt(0) == '-') ){ // gobbles up strings like '-+++--+-++--+'
                    if( tokens.pop_back().charAt(0) == '-') 
                        mul *= -1;
                }

                if( !tokens.empty() && (Character.isDigit(tokens.getLast().charAt(0)) || tokens.getLast().charAt(0) == ')') )
                    tokens.add('+'+""); // no minus signs in the tokens;

                num = new StringBuffer();
                if( mul == -1) num.append('-');
                while( i < s.length() && Character.isDigit( s.charAt(i) ) )
                    num.append(s.charAt(i++));
                
                if( i < s.length() && s.charAt(i) == '.' )
                    num.append(s.charAt(i++));
                
                while( i < s.length() && Character.isDigit( s.charAt(i) ) )
                    num.append(s.charAt(i++) );
                tokens.add(num.toString());
               // toNumber(num.toString());


            }
            else { 
                valid = false; 
                validityLog.append("Invalid Operator: "+s.charAt(i)+"\n");
                i++; 
            }

          //  i++;
        }

        for(int j=0;j<tokens.length();j++)
            System.out.print(tokens.get(j)+" ");
        System.out.println();
    }

    private boolean checkValidity(){
        if( valid == false ) return valid;

        int bracket = 0;
       // && valid == true
        for(int i=0;i<tokens.length();i++){
            if( tokens.get(i).charAt(0) == '(' ) 
                bracket++;
            else if( tokens.get(i).charAt(0) == ')' )
                bracket--;
            if( bracket < 0 ){ // ))(, )(), ()()) etc;
                valid = false; 
                validityLog.append("Invalid bracket sequence: No opening brace for a closing brace\n"); 
            } 
            
            if( tokens.get(i).charAt(0) == '(' && i+1<tokens.length() && tokens.get(i+1).charAt(0) == ')' ){  // () is not valid
                valid = false; 
                validityLog.append("Invalid bracket sequence: ()\n") ;
            }
            else if( tokens.get(i).charAt(0) == ')' && i+1<tokens.length() && tokens.get(i+1).charAt(0) == '(' ){  // )( is not valid, why??? please explain.. could sub with * 
                valid = false; 
                validityLog.append("Invalid bracket sequence: )(\n") ;
            }
            else if(     tokens.get(i).charAt(0) == '+' ||
                    tokens.get(i).charAt(0) == '*' ||
                    tokens.get(i).charAt(0) == '/' ||
                    tokens.get(i).charAt(0) == '-' )  // right and left operand should be numbers or right = '(' and left = ')' 
            {
                if( (i-1>=0 && (Character.isDigit(tokens.get(i-1).charAt(0)) || tokens.get(i-1).charAt(0) == ')')) &&
                    (i+1<tokens.length() && (Character.isDigit(tokens.get(i+1).charAt(0)) || tokens.get(i+1).charAt(0) == '(')) )    
                {}
                else{ 
                    valid = false;
                    validityLog.append("Binary operater: "+tokens.get(i).charAt(0)+", invalid operands\n") ;
                }

                if( tokens.get(i).charAt(0) == '/' &&
                    i+1 < tokens.length() && 
                    Character.isDigit(tokens.get(i+1).charAt(0)) && 
                    toNumber(tokens.get(i+1)) == 0.0 
                ){ // division by 0 
                    valid = false; validityLog.append("divison by zero\n") ;
                }
            } /*
            else if( tokens.get(i).charAt(0) == '-' ){
                if( (i-1>=0 && (Character.isDigit(tokens.get(i-1).charAt(0)) || tokens.get(i-1).charAt(0) == ')')) &&
                    (i+1<tokens.length() && (Character.isDigit(tokens.get(i+1).charAt(0)) || tokens.get(i+1).charAt(0) == '(')) )    
                {}
                else if(    (i-1>=0 && tokens.get(i-1).charAt(0) == '(') && 
                            (i+1<tokens.length() && Character.isDigit(tokens.get(i+1).charAt(0)))  && 
                            (i+2 < tokens.length() && tokens.get(i+2).charAt(0) == ')') )  // special case, i dunno why (-4) is valid.. but why not -4 ???
                {}
                else{ 
                    valid = false; 
                    validityLog.append("Binary operater: "+tokens.get(i).charAt(0)+", invalid operands\n"); 
                }
            } */
            else if( Character.isDigit(tokens.get(i).charAt(0)) && (i+1<tokens.length() && Character.isDigit(tokens.get(i+1).charAt(0)))  ){
                valid = false;
                validityLog.append("Two consecutive numbers without operator\n");
            }
        }

        if( bracket != 0 ){ 
            valid = false; 
            validityLog.append("Invalid bracket sequence: number of opening brace != number of closing brace\n"); 
        }

        return valid;
    }

    private void calculate(){
        int i = 0;

        while( i < tokens.length() ){

            if( Character.isDigit(tokens.get(i).charAt(0)) ){
                value.push(toNumber(tokens.get(i))) ;
            }
            else if( tokens.get(i).charAt(0) == '(' )
                op.push('(');
            else if( tokens.get(i).charAt(0) == ')' ){
                Character operator ;
                double v1,v2;
                while( !op.empty() && op.top() != '(' ){
                    operator = op.pop();
                    
                    if( operator == '-' ){
                        v1 = value.pop();
                        v1 = -v1;
                        value.push(v1);
                        op.push('+');
                    }
                }
            }

            else {

            }
        }
    }


    public static void main(String[] args){

        String s ;//= "12*(-1)";// = "()+1342.23434.2434+234234.4+()4" ;

        calc_stack cs ;//= new calc_stack(s);

        Scanner scn = new Scanner(System.in);

        while( scn.hasNextLine() ){
            s = scn.nextLine();
            System.out.println(s);
            cs = new calc_stack(s);
        }
    }
}

/**
12*(-1) = -12
12*123/((-5)+2) = -492
((80-(19))) = 61
(1-2)+(((-4))) = -5
1+1 = 2
2/2+3*4.75--6 = invalid
12*(-123) = -1476
2/(2+3)*4.33-(-6.792) = 8.524000000000001
((2.33/(2.9+3.5)*4)-(-6.34)) = 7.79625
123.45*(678.90/((-2.5)+11.5)-(80-19)*33.25)/20+11 = -12042.760875
(123.45*(678.90/((-2.5)+11.5)-(((80-(19)))*33.25))/20)-(123.45*(678.90/(-2.5+11.5)-(((80-(19)))*33.25))/20)+(13-2)/(-11) = Not valid
(123.45*(678.90/((-2.5)+11.5)-(((80-(19)))*33.25))/20)-(123.45*(678.90/((-2.5)+11.5)-(((80-(19)))*33.25))/20)+(13-2)/(-11) = -1
"12*(-123) = -1476"    Not valid (= is not allowed)
123.45*(678.90/(-2.5+11.5)-(80-19)*33.25)/0+11   = invalid   // divided by 0
123 = 123
-123 = not valid
(-123)    =   -123
(((1-5)+( 3.93* 2.34)* 10)+(10+10+19-8*7/6+10)) =  127.62866666666665
(9*3-(7*8+((-4/2))) = invalid
(-1)-(-3) = 2
((-1)-(-3)) = 2
 */