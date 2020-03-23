package com.kpit.diagnostic;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileService
{
    private Context context;

    public CSVFileService(Context context)
    {
        this.context = context;
    }

    public void readFromCSVFile()
    {

    }

    public void writeToCSVFile()
    {

    }

    InputStream in;

    public List<String[]> read()
    {
        List<String[]> results = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");
                System.out.println(Arrays.toString(row));
                results.add(row);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error reading CSV File " + e);
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException("Error closing inputstream " + e);
            }
        }

        return results;
    }


    File file;

    public void save(List<String[]> list)
    {
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                throw new RuntimeException("Unable to create File " + e);
            }
        }

        try
        {
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < list.size(); i++)
            {
                String[] row = list.get(i);
                for(int j = 0; j < row.length; j++)
                {
                    writer.write(row[j]);
                    if(j != (row.length - 1)){
                        writer.write(',');
                    }
                    else{
                        writer.write('\n');
                    }
                }
            }

            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Unable to write to File " + e);
        }
    }
}
