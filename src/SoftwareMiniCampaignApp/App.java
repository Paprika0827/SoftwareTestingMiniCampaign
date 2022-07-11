package SoftwareMiniCampaignApp;


import java.util.ArrayList;

public class App{
    public static void main(String[] args) {
        String filepath1 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/sample_file_1.csv";
        String filepath2 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/sample_file_3.csv";
        ArrayList<String[]> csvFileLines1 = ReadCSVFile.readFileLines(filepath1);
        ArrayList<String[]> csvFileLines2 = ReadCSVFile.readFileLines(filepath2);
        String[] combination = {"\"Customer ID#\"","\"Type\"","\"Account No.\""};
        ArrayList<String[]> differentItems = CompareCSVFile.compareCSVWithCombination(csvFileLines1,csvFileLines2,combination);
        WriteCSVFile.writeFile(differentItems,"C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/output.csv");
    }
}
