import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Sven Sta on 07.05.2015.
 */
public class TabDelimitedReader
{
    public Daten readDaten(String fileName)
    {
        String variableName = null;
        double variableX = 0;
        Scanner tabDelimitedScanner = null;
        try
        {
            tabDelimitedScanner = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found or format is unknown!");
        }
        while (tabDelimitedScanner.hasNextLine())
        {
            variableName = tabDelimitedScanner.nextLine();
            variableX = tabDelimitedScanner.nextDouble();
            tabDelimitedScanner.nextLine();

        }
        return new Daten(variableName + "\n" + variableX);

    }
}
