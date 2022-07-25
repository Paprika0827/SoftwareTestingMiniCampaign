package SoftwareMiniCampaignApp;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WriteCSVFileTest {
    String[] line1 = {"\"item1\"","\"item2\"","\"item3\""};
    String[] line2 = {"\"item4\"","\"item5\"","\"item6\""};

    ArrayList<String[]> test_writeData = new ArrayList<>(){
        {
            add(line1);
            add(line2);
        }
    };
    String test_writeFilePath = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/writeCSVFileTest.csv";

    @Test
    public void writeFile() {
        WriteCSVFile.writeFile(test_writeData,test_writeFilePath);
        File file = new File(test_writeFilePath);
        assertTrue(file.exists());

        ArrayList<String[]> write_result = ReadCSVFile.readFileLines(test_writeFilePath);
        assertArrayEquals(test_writeData.toArray(),write_result.toArray());
    }
}