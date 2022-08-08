package SoftwareMiniCampaignApp;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVFileFuzzer {
    public static void main(String[] args){
        Random random = new Random();
        String original_file_path = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/sample_file_1.csv";

        String fuzzing_test_file_path_1 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/Fuzzing_CSV_1.csv";
        String fuzzing_test_file_path_2 = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/Fuzzing_CSV_2.csv";

        //write the first file, which is the same as the original file
        ArrayList<String[]> original_content = ReadCSVFile.readFileLines(original_file_path);
        WriteCSVFile.writeFile(original_content,fuzzing_test_file_path_1);

        //generate mutation for the second file
        int mutation_line_number = random.nextInt(original_content.size());// number of lines that the mutation might occur
        ArrayList<String[]> mutated_content = original_content;
        for(int line_number = 1; line_number<=mutation_line_number;line_number++){
            int mutation_item_index = random.nextInt(mutated_content.get(line_number).length);// select a random item to be mutated
            int mutation_method = random.nextInt(3);
            String mutated_item = mutated_content.get(line_number)[mutation_item_index];

            //add random characters
            if (mutation_method==0){
                String random_string = UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
                random_string.replaceAll(",","");
                mutated_content.get(line_number)[mutation_item_index] = mutated_item+random_string;
            }
            //cut random number of characters
            if (mutation_method==1){
                int shorted_string_length = random.nextInt(mutated_item.length());
                String shorted_string = mutated_item.substring(0,shorted_string_length);
                mutated_content.get(line_number)[mutation_item_index]=shorted_string;
            }
            //flip random number of characters
            if (mutation_method==2){
                int flipped_char_index = random.nextInt(mutated_item.length());
                StringBuilder new_item = new StringBuilder(mutated_item);
                char new_char = (char)(random.nextInt(26)+'a');
                while (new_char == mutated_item.charAt(flipped_char_index)){
                    new_char = (char)(random.nextInt(26)+'a');
                }
                String str = ""+new_char;
                new_item.replace(flipped_char_index,flipped_char_index,str);
                mutated_content.get(line_number)[mutation_item_index] = new_item.toString();
            }
        }
        //write the second file
        WriteCSVFile.writeFile(mutated_content,fuzzing_test_file_path_2);
        try{
            ArrayList<String[]> fuzz_content_1 = ReadCSVFile.readFileLines(fuzzing_test_file_path_1);
            ArrayList<String[]> fuzz_content_2 = ReadCSVFile.readFileLines(fuzzing_test_file_path_2);
            String[] combination = fuzz_content_1.get(0);
            ArrayList<String[]> differentItems = CompareCSVFile.compareCSVWithCombination(fuzz_content_1,fuzz_content_2,combination);
            String fuzz_output_path = "C:/Users/Hope/IdeaProjects/SoftwareTestingMiniCampaign/testFiles/fuzz_output.csv";
            WriteCSVFile.writeFile(differentItems,fuzz_output_path);
            //because n lines are mutated, we expect 2n exceptions to be identified
            if (differentItems.size()!=mutation_line_number*2){
                System.out.println("BUG detected");
                System.out.println(mutation_line_number*2+"exceptions expected "+ differentItems.size()+"exceptions actually found");
            }
            else {
                System.out.println(mutation_line_number*2+"exceptions expected "+ differentItems.size()+"exceptions actually found");
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error found with execution");
        }
    }
}
