
import java.time.temporal.Temporal;
import java.util.Stack;

public class InfijoAOtros {
    
    public static String getPostfix(String ep){ 
        String expr = depurar(ep);
        String[] arrayInfix = expr.split(" ");
        //Declaración de las pilas
        Stack <String> E = new Stack <String>(); //Pila entrada
        Stack <String> P = new Stack <String>(); //Pila temporal para operadores
        Stack <String> S = new Stack <String>(); //Pila salida

        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
          E.push(arrayInfix[i]);
        }

        try {
          //Algoritmo Infijo a Postfijo
          while (!E.isEmpty()) {
            switch (pref(E.peek())){
              case 1:
                P.push(E.pop());
                break;
              case 3:
              case 4:
                while(pref(P.peek()) >= pref(E.peek())) {
                  S.push(P.pop());
                }
                P.push(E.pop());
                break; 
              case 2:
                while(!P.peek().equals("(")) {
                  S.push(P.pop());
                }
                P.pop();
                E.pop();
                break; 
              default:
                S.push(E.pop()); 
            } 
          } 
        
        String infix = expr.replace(" ", "");
        String postfix = S.toString().replaceAll("[\\]\\[,]", "");
   
        return postfix;

      }catch(Exception ex){ 
        System.out.println("Error en la expresión algebraica");
        System.err.println(ex);
      }
        return "";
    }
    
    public static String getPrefix(String infijo){
        infijo = '(' + infijo ; // Agregamos al final del infijo un ')'
        int tamaño = infijo.length();
        Stack PilaDefinitiva = new Stack();
        Stack PilaTemp = new Stack();
        PilaTemp.push(')'); // Agregamos a la pila temporal un '('
        for (int i = tamaño-1; i > -1; i--) {
            char caracter = infijo.charAt(i);
            switch (caracter) {
            case ')':
                PilaTemp.push(caracter);
                break;
            case '+':case '-':case '^':case '*':case '/':
                while (Jerarquia(caracter) > Jerarquia((Character)PilaTemp.peek()))
                    PilaDefinitiva.push(PilaTemp.pop());
                PilaTemp.push(caracter);
                break;
            case '(':
                while ((Character)PilaTemp.peek() != ')')
                    PilaDefinitiva.push(PilaTemp.pop());
                PilaTemp.pop();
                break;
            default:
                PilaDefinitiva.push(caracter);
            }
        }
        
        
        String prefijo = "";
        while (!PilaDefinitiva.isEmpty())
            prefijo += PilaDefinitiva.pop()+" ";
        return prefijo;

    }
    
    public static int Jerarquia(char elemento) {
        int res = 0;
        switch (elemento) {
        case ')':
            res = 5; break;
        case '^':
            res = 4; break;
        case '*': case '/':
            res = 3; break;
        case '+': case '-':
            res = 2; break;
        case '(':
            res = 1; break;
        }
        return res;
    }
    
    private static String depurar(String s) {
    s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
    s = "(" + s + ")";
    String simbols = "+-*/()";
    String str = "";
  
    //Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      }else str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  } 
    
    private static int pref(String op) {
    int prf = 99;
    if (op.equals("^")) prf = 5;
    if (op.equals("*") || op.equals("/")) prf = 4;
    if (op.equals("+") || op.equals("-")) prf = 3;
    if (op.equals(")")) prf = 2;
    if (op.equals("(")) prf = 1;
    return prf;
  }
      
    public String getPrefixToInfix(String ep){
        Stack <String> salida = new Stack<String>();
        String[] arrayInfix = ep.split(" ");
        //Declaración de las pilas
        Stack <String> E = new Stack <String>();
        Stack <String> Temp = new Stack <String>();
        
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
          E.push(arrayInfix[i]);
        }
        
        while(!E.isEmpty()){
            if(!isOperant(E.peek())){
                salida.push(E.pop());
            }else{
                if(!salida.isEmpty()){
                    if(!isOperant(salida.peek())){
                        if(!Temp.isEmpty()){
                            salida.push(Temp.pop());
                        }
                    }else{
                        Temp.push(E.pop());
                    }
                }
                
            }
        }
        
        return salida.toString().replaceAll("[\\]\\[,]", "");
    }
            
       
    
   
   
    public static boolean isOperant(String o){
        switch(o){
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
            case "^":
                return true;
            case "(":
                return true;
            case ")":
                return true;
            default:
                return false;
        }
    }
}
