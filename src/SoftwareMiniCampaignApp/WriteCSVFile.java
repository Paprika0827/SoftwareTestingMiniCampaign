package SoftwareMiniCampaignApp;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSVFile {

    public static void writeFile(ArrayList<String[]> differentItems, String filePath){
        File writeFile = new File(filePath);

        try {
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));
            for (String[] line: differentItems){
                String output ="";
                for(int i = 0; i<line.length-1; i++){
                    output += line[i];
                    output += ",";
                }
                output += line[line.length-1];
                writeText.write(output);
                writeText.newLine();
            }
            writeText.flush();
            writeText.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
