import java.util.Scanner;

public class doublem{
public  static void readData( Scanner in ) {
    String numStr = null;
    String codeStr= null;
    try {
        codeStr = in.next();
        numStr = Character.toString( codeStr.charAt( 2 ) );
        int num = Integer.parseInt( numStr );
    } catch (NumberFormatException nfe) {
        System.out.println( numStr + " is not a number." );
    }
}
public static void main( String [] args ) {
    Scanner kbd = new Scanner( System.in );
   System.out.println("van nguyen");
    try {
        readData( kbd );
    } catch ( Exception e ) {
        System.out.println( "An Exception occurred" );
    } finally {
        kbd.close();
        System.out.println( "Done." );
    }
}
}