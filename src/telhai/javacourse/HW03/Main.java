package telhai.javacourse.HW03;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
//uiuiugiuiugiugiugiug/
public class Main {

    public static void main( String[] args )
    {
        JsonBuilder avraham = null;
        try
        {
            avraham = new JsonBuilder( new File( "C:\\Users\\Itamar\\Documents\\FilesJava\\Avraham.txt"));
            System.out.println( avraham );
            System.out.println( avraham.get( "issue" ).get( "Ketura" ).get( 2 ) );
        }
        catch( JsonSyntaxException | FileNotFoundException | JsonQueryException | ParseException e )
        {
            e.printStackTrace();
        }
    }
}
