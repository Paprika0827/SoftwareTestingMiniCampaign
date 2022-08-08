package SoftwareMiniCampaignApp;


import java.util.ArrayList;

public class App{
    public static void main(String[] args) {
        //Edit the two comparing file path to the file path on your local computer
        //Edit under this line
        String filepath1 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/sample_file_1.csv";
        String filepath2 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/sample_file_3.csv";
        String[] combination = {"\"Customer ID#\"","\"Type\"","\"Account No.\"","\"Balance\"","\"Currency\""};
        //Edit before this line

        //DON'T edit under this line
        ArrayList<String[]> csvFileLines1 = ReadCSVFile.readFileLines(filepath1);
        ArrayList<String[]> csvFileLines2 = ReadCSVFile.readFileLines(filepath2);
        ArrayList<String[]> differentItems = CompareCSVFile.compareCSVWithCombination(csvFileLines1,csvFileLines2,combination);
        //DON't edit before this line

        //Edit the output file path to the file path on your local computer
        //Edit after this line
        WriteCSVFile.writeFile(differentItems,"C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/output.csv");
        //Edit before this line
        //DON't edit after this line
    }
}
