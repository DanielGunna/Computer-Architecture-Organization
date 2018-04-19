import java.io.*;

/*
   Daniel Gunna
   Felipe Coelho
   Leonardo Palis
*/


public class Prova {

   //Multiplicacao
   public static String Multiplicar ( String multiplicador, String multiplicando ){
   
      String produto= "0000000000000000"; //16bits
      String metade1="";
      String metade2="";
      
      int x = 0;
   
      while ( x < 8 ){
         
         metade1 = "";
         metade2 = "";
         
         if ( multiplicador.charAt(multiplicador.length()-1) == '1' ){
            
            //SomaMultiplicandoA metade esquerda
            for ( int i=0; i < 8; i++ ){
               metade1 += produto.charAt(i);
               metade2 += produto.charAt(i+8);
            }
            
            metade1 = ALU (metade1,multiplicando,3);
            produto = metade1 + metade2;
         }
         
         //DeslocarProduto, direita
         
         produto = deslocarDireita ( produto );
         
         //DeslocarMultiplicador, direita
         multiplicador = deslocarDireita ( multiplicador );
          
      
         x++;  
      }
      
      return produto; 
   }
   
  //desloca para direita
   public static String deslocarDireita ( String numero ){
   
      String nova = "0";
      int x = numero.length()-1;
      
      for ( int i =0; i < x; i ++ ){
         
         nova += numero.charAt(i);
      
      }
      
      
      return nova;
   }


   //Converte para uma String binaria
   public static String toBinario ( int a ){
      
      String bin = "";
      String resposta="";
      while ( a > 0 ) {
         bin = ((a % 2 == 0)?"0":"1") + bin;
         a /= 2;
      }
      
      
      int dif = 8 - bin.length();
      
      while ( dif > 0 ){
      
         resposta += "0";
      
         dif--;
      }
      
      resposta += bin;
         
      
      
      return resposta;
      
   }
   
   
    /*
    * a - elemento (multiplicando)
    * b - elemento (produto)
    * operacao - controlar
    */
  
    
   public static String ALU ( String a, String b, int operacao )
   {
      String retorno = "";
      boolean aux1;
      boolean aux2;
      
        // a and b
      if ( operacao == 0 ){
          
         for ( int i = 0; i < 8; i++ ){
          
            aux1 = ( a.charAt(i) == '0')? false:true;
            aux2 = ( b.charAt(i) == '0')? false:true;
            retorno += (aux1 && aux2) ? "1": "0";
            
         }
          
      }
        
        // a or b
      if ( operacao == 1 ){
      
         for ( int i = 0; i < 8; i++ ){
          
            aux1 = ( a.charAt(i) == '0')? false:true;
            aux2 = ( b.charAt(i) == '0')? false:true;
            retorno += (aux1 || aux2) ? "1": "0";
            
         }
        
      }
        
        // a not
      if ( operacao == 2 ){
           
         for ( int i = 0; i < a.length(); i++ ){
          
            aux1 = ( a.charAt(i) == '0')? true:false;
            retorno += (aux1) ? "1": "0";
            
         }
           
      }
        
        // soma (a+b)
      if ( operacao == 3 ){
        
         int k1 = Integer.parseInt(a,2);
         int k2 = Integer.parseInt(b,2);
        
         int ret = k1 + k2;
         retorno = toBinario(ret);
            
      }
        
      return retorno;
   }

  

   //Unidade de Controle
   public static void main ( String [] args ) throws Exception
   {
      System.out.println("0- a AND b\n1- a OR b\n2- not B\n3-Somar (a+b)\n4-Multiplicar A x B");
      BufferedReader in = new BufferedReader ( new InputStreamReader ( System.in ) );
      int op = Integer.parseInt(in.readLine());
   
        //Pegando primeiro elemento
      System.out.println("Digite A");
      in = new BufferedReader ( new InputStreamReader ( System.in ) );
      String entrada = in.readLine();
      int multiplicando = Integer.parseInt( entrada );
        
        //Pegando segundo elemento
      System.out.println("Digite B");
      entrada = in.readLine();
      int multiplicador = Integer.parseInt ( entrada );
        String printar = "";
        
        
        
        //Transformando para Binario
      String bin1 = toBinario ( multiplicador ) ;
      String bin2 = toBinario ( multiplicando ) ;
      
       if ( op == 4 ){
         printar = Multiplicar(bin1,bin2);
       }else{
         printar = ALU (bin1,bin2,op);
       }
        
        
        
        
      System.out.println( printar );
      
   }
   
}