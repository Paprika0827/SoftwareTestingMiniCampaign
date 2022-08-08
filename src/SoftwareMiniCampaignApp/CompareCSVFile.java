package SoftwareMiniCampaignApp;

import java.util.ArrayList;

public class CompareCSVFile {
    public static ArrayList<String[]> compareCSVWithCombination(ArrayList<String[]> csvFileLinesSplit1,ArrayList<String[]> csvFileLinesSplit2,String[] combination){
        ArrayList<Integer> comparedItemIndexInLine = new ArrayList<>();// items to be compared
        ArrayList<Integer> referenceItemIndexInLine = new ArrayList<>();// items in the unique combination

        //initializing the two arraylists
        for (int i=0;i<csvFileLinesSplit1.get(0).length;i++){
            boolean flag = false;
            for (int j=0;j<combination.length;j++){
                if (combination[j].equals(csvFileLinesSplit1.get(0)[i])){
                    referenceItemIndexInLine.add(i);
                    flag = true;
                }
            }
            if(!flag){
                comparedItemIndexInLine.add(i);
            }
        }

        ArrayList<String[]> differentItems = new ArrayList<>();
        int standard_length = csvFileLinesSplit1.get(0).length;// all lines in the two csv files are expected to have the same number of items as the first line, which is the column names

        //comparing each line
        try{
        for (int i=0;i<csvFileLinesSplit1.size();i++){
            int compareCount = 0;//if this number is more than the size of the other csv file, it means this line does not have a corresponding line which has the same reference items as it has in the other file.
            boolean addedInThisLoop = false;//if the other file has multiple lines that are exceptions and has the same reference items, this line are only expected to but added into the final output once. This flag is used to identify whether this line has been added or not.
            if (csvFileLinesSplit1.get(i).length!=standard_length){
                differentItems.add(csvFileLinesSplit1.get(i));
                continue;// if the number of items doesn't match with the standard, add this line to output list
            }
            for (int j=0;j<csvFileLinesSplit2.size();j++){
                if(csvFileLinesSplit2.get(j).length!=standard_length){
                    compareCount+=1;
                    continue;// if the number of items doesn't match with the standard, increase the compare count. Handle this line later to avoid adding one line for multiple times.
                }
                boolean flag = true;// flag for identifying the line in the other file with the same reference items
                for (Integer index : referenceItemIndexInLine){
                    if (!csvFileLinesSplit1.get(i)[index].equals(csvFileLinesSplit2.get(j)[index])){
                        flag = false;
                    }
                }
                if(flag){
                    boolean isDifferent = false;
                    for (Integer index: comparedItemIndexInLine){

                        if (!csvFileLinesSplit1.get(i)[index].equals(csvFileLinesSplit2.get(j)[index])){
                            isDifferent = true;
                        }
                    }
                    if(isDifferent){
                        if(!addedInThisLoop){//check if this line has already been added
                            differentItems.add(csvFileLinesSplit1.get(i));
                            addedInThisLoop = true;
                        }
                        differentItems.add(csvFileLinesSplit2.get(j));
                    }
                }
                if(!flag){
                    compareCount+=1;
                }
            }
            if(compareCount==csvFileLinesSplit2.size()){
                differentItems.add(csvFileLinesSplit1.get(i));
            }
        }

        for (int m=0;m<csvFileLinesSplit2.size();m++){
            if (csvFileLinesSplit2.get(m).length!=standard_length){
                differentItems.add(csvFileLinesSplit2.get(m));//Handle the lines in the other file which doesn't have standard number of items
                continue;
            }
            int compareCount = 0;//if this number is more than the size of the other csv file, it means this line does not have a corresponding line which has the same reference items as it has in the other file.
            for (int n=0;n<csvFileLinesSplit1.size();n++){
                boolean flag = true;
                for (Integer index : referenceItemIndexInLine){
                    if (!csvFileLinesSplit1.get(n)[index].equals(csvFileLinesSplit2.get(m)[index])){
                        flag = false;
                    }
                }
                if(!flag){
                    compareCount+=1;
                }
            }
            if(compareCount==csvFileLinesSplit1.size()){
                differentItems.add(csvFileLinesSplit2.get(m));
            }
        }}catch(Exception e){
            System.out.println(e);
        }

        return differentItems;
    }
}
