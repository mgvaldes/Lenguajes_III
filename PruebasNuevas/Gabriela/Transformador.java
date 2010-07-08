import java.util.Hashtable;

public class Transformador {

    public static void main(String[] args) {

        String frase = "El arreglo de variantes contiene ";

        Hashtable<String, String> mapa = new Hashtable<String, String>();

        mapa.put("A","print 'A'; ");
        mapa.put("B","print 'B'; ");
        mapa.put("C","print 'C'; ");
        mapa.put("D","print 'D'; ");
        mapa.put("E","print 'E'; ");
        mapa.put("F","print 'F'; ");
        mapa.put("G","print 'G'; ");
        mapa.put("H","print 'H'; ");
        mapa.put("I","print 'I'; ");
        mapa.put("J","print 'J'; ");
        mapa.put("K","print 'K'; ");
        mapa.put("L","print 'L'; ");
        mapa.put("M","print 'M'; ");
        mapa.put("N","print 'N'; ");
        mapa.put("Ñ","print 'Ñ'; ");
        mapa.put("O","print 'O'; ");
        mapa.put("P","print 'P'; ");
        mapa.put("Q","print 'Q'; ");
        mapa.put("R","print 'R'; ");
        mapa.put("S","print 'S'; ");
        mapa.put("T","print 'T'; ");
        mapa.put("U","print 'U'; ");
        mapa.put("V","print 'V'; ");
        mapa.put("W","print 'W'; ");
        mapa.put("X","print 'X'; ");
        mapa.put("Y","print 'Y'; ");
        mapa.put("Z","print 'Z'; ");
        mapa.put("a","print 'a'; ");
        mapa.put("b","print 'b'; ");
        mapa.put("c","print 'c'; ");
        mapa.put("d","print 'd'; ");
        mapa.put("e","print 'e'; ");
        mapa.put("f","print 'f'; ");
        mapa.put("g","print 'g'; ");
        mapa.put("h","print 'h'; ");
        mapa.put("i","print 'i'; ");
        mapa.put("j","print 'j'; ");
        mapa.put("k","print 'k'; ");
        mapa.put("l","print 'l'; ");
        mapa.put("m","print 'm'; ");
        mapa.put("n","print 'n'; ");
        mapa.put("ñ","print 'ñ'; ");
        mapa.put("o","print 'o'; ");
        mapa.put("p","print 'p'; ");
        mapa.put("q","print 'q'; ");
        mapa.put("r","print 'r'; ");
        mapa.put("s","print 's'; ");
        mapa.put("t","print 't'; ");
        mapa.put("u","print 'u'; ");
        mapa.put("v","print 'v'; ");
        mapa.put("w","print 'w'; ");
        mapa.put("x","print 'x'; ");
        mapa.put("y","print 'y'; ");
        mapa.put("z","print 'z'; ");
        mapa.put("0","print '0'; ");
        mapa.put("1","print '1'; ");
        mapa.put("2","print '2'; ");
        mapa.put("3","print '3'; ");
        mapa.put("4","print '4'; ");
        mapa.put("5","print '5'; ");
        mapa.put("6","print '6'; ");
        mapa.put("7","print '7'; ");
        mapa.put("8","print '8'; ");
        mapa.put("9","print '9'; ");
        mapa.put(" ","print ' '; ");
        mapa.put(":","print ':'; ");
        mapa.put(".","print '.'; ");
        mapa.put(",","print ','; ");
        mapa.put(";","print ';'; ");
        mapa.put("(","print '('; ");
        mapa.put(")","print ')'; ");
        mapa.put("[","print ']'; ");
        mapa.put("%","print '%'; ");
        mapa.put("\n","print '\n'; ");

        for(int i = 0; i < frase.length(); i++){
           String result = mapa.get(frase.substring(i, i+1));
           if(frase.substring(i, i+1).equals(" ")){
               System.out.println("");
               System.out.print(result);
               System.out.println("");
           } 
           else
               System.out.print(result);

        }

    }
}
