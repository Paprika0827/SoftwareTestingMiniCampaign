package SoftwareMiniCampaignApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ReadCSVFile {
    public static ArrayList<String[]> readFileLines(String filePath){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String[]> fileLines = new ArrayList<>();//the final output is the array list containing the lists of the items in each line
        try {

            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] lineItems = line.split(cvsSplitBy);//split the items
                fileLines.add(lineItems);

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return fileLines;
    }
}
