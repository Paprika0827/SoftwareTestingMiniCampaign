package SoftwareMiniCampaignApp;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReadCSVFileTest {
    String testpath1 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/readFile_test_1.csv";


    String[] line1 = {"\"item1\"","\"item2\"","\"item3\""};
    String[] line2 = {"\"item4\"","\"item5\"","\"item6\""};
    ArrayList<String[]> test1_expected_result = new ArrayList<>() {
        {
            add(line1);
            add(line2);
        }
    };

    ArrayList<String[]> test1_result = ReadCSVFile.readFileLines(testpath1);

    @Test
    public void testReadFileLines() {
        assertArrayEquals(test1_expected_result.toArray(),test1_result.toArray());
    }

}
