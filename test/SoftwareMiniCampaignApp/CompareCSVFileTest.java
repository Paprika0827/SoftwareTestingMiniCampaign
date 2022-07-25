package SoftwareMiniCampaignApp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompareCSVFileTest {
    String[] test_combination1 = {"\"id1\"","\"id2\""};
    String[] test_combination2 = {"\"id1\""};

    String[] line0 = {"\"id1\"","\"id2\"","\"id3\""};
    String[] line1 = {"\"item1\"","\"item2\"","\"item3\""};
    String[] line2 = {"\"item4\"","\"item5\"","\"item6\""};
    String[] line3 = {"\"item7\"","\"item8\"","\"item9\""};
    String[] line2_different = {"\"item4\"","\"item5\"","\"item6.1\""};
    String[] line3_different = {"\"item7\"","\"item8.1\"","\"item9\""};

    //test1: different combination should give corresponding results
    ArrayList<String[]> testFileLineSet1 = new ArrayList<>(){
        {
            add(line0);
            add(line1);
            add(line2);
            add(line3);
        }
    };
    ArrayList<String[]> testFileLineSet2 = new ArrayList<>(){
        {
            add(line0);
            add(line1);
            add(line2_different);
            add(line3_different);
        }
    };



    ArrayList<String[]> expected_result1 = new ArrayList<>() {
        {
            add(line2);
            add(line2_different);
            add(line3);
            add(line3_different);
        }
    };

    ArrayList<String[]> expected_result2 = new ArrayList<>() {
        {
            add(line2);
            add(line2_different);
            add(line3);
            add(line3_different);
        }
    };




    @Test
    public void compareCSVWithCombinationTest1() {
        assertArrayEquals(expected_result1.toArray(),CompareCSVFile.compareCSVWithCombination(testFileLineSet1,testFileLineSet2,test_combination1).toArray());
        assertArrayEquals(expected_result2.toArray(),CompareCSVFile.compareCSVWithCombination(testFileLineSet1,testFileLineSet2,test_combination2).toArray());
    }

    //test2: one file contains two rows that are different from one row in another file based on the unique combination
    ArrayList<String[]> testFileLineSet3 = new ArrayList<>(){
        {
            add(line0);
            add(line1);
            add(line2_different);
            add(line3_different);
            add(line2_different);
        }
    };

    ArrayList<String[]> expected_result3 = new ArrayList<>() {
        {
            add(line2);
            add(line2_different);
            add(line2_different);
            add(line3);
            add(line3_different);
        }
    };
    @Test
    public void compareCSVWithCombinationTest2() {
        System.out.println(testFileLineSet3.size());
        assertArrayEquals(expected_result3.toArray(),CompareCSVFile.compareCSVWithCombination(testFileLineSet1,testFileLineSet3,test_combination1).toArray());
    }

    //test3: unique combination not in order
    String[] test_combination3 = {"\"id2\"","\"id1\""};
    @Test
    public void compareCSVWithCombinationTest3() {
        assertArrayEquals(expected_result3.toArray(),CompareCSVFile.compareCSVWithCombination(testFileLineSet1,testFileLineSet3,test_combination1).toArray());
    }

    //test4: one contain an entry completely not in the other
    String[] line4 = {"\"item10\"","\"item11\"","\"item12\""};
    ArrayList<String[]> testFileLineSet4 = new ArrayList<>(){
        {
            add(line0);
            add(line1);
            add(line2);
            add(line3);
            add(line4);
        }
    };
    ArrayList<String[]> expected_result4 = new ArrayList<>() {
        {
            add(line4);
        }
    };
    @Test
    public void compareCSVWithCombinationTest4() {
        assertArrayEquals(expected_result4.toArray(),CompareCSVFile.compareCSVWithCombination(testFileLineSet1,testFileLineSet4,test_combination1).toArray());
    }

}