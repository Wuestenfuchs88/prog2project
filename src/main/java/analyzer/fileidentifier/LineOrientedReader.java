import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Sven Sta on 08.05.2015.
 */
public class LineOrientedReader
{
    public Daten readDaten(String fileName)
    {
        Scanner lineOrientedScanner = null;
        String variableName = null;
        String variableX = null;

        try
        {
            lineOrientedScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found or format is unknown!");
        }
        while (lineOrientedScanner.hasNextLine())
        {
            variableName = lineOrientedScanner.nextLine().split(";") [1];
            variableX = lineOrientedScanner.nextLine().split(";") [1];
        }
        return new Daten(variableName + "\n" + variableX);
    }
}
