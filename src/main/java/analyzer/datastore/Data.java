package analyzer.datastore;

import java.lang.Integer;
import java.util.ArrayList;

public class Data
{
    private int numberOfVariables;
    private ArrayList<String> variableNames;
    private ArrayList<ArrayList<Double>> variableContent;
    private String filename;

    public Data(int numberOfVariables, ArrayList<String> variableNames, ArrayList<ArrayList<Double>> variableContent)
    {
        this.variableNames = variableNames;
        this.numberOfVariables = numberOfVariables;
        this.variableContent = variableContent;
        this.filename = filename;
    }

    //Get the numbers of variables
    public Integer getNumberOfVariables()
    {
        return numberOfVariables;
    }

    // Get the names of the variables
    public ArrayList<String> getVariableNames()
    {
        return variableNames;
    }

    //Get Content
    public ArrayList<ArrayList<Double>> getVariableContent()
    {
        return variableContent;
    }

    /**public String getFilename()
    {
        return filename;
    }*/




       /** for (int i = 0; i < numberOfVariables; i++)
        {
            Variable variable = new Variable(variableNames.get(i));
        }
        */


}

