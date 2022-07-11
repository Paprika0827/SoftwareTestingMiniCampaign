package SoftwareMiniCampaignApp;

import java.util.ArrayList;

public class CompareCSVFile {
    public static ArrayList<String[]> compareCSVWithCombination(ArrayList<String[]> csvFileLinesSplit1,ArrayList<String[]> csvFileLinesSplit2,String[] combination){
        ArrayList<Integer> comparedItemIndexInLine = new ArrayList<>();
        ArrayList<Integer> referenceItemIndexInLine = new ArrayList<>();
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
        System.out.println(comparedItemIndexInLine);
        ArrayList<String[]> differentItems = new ArrayList<>();
        for (int i=0;i<csvFileLinesSplit1.size();i++){
            for (int j=0;j<csvFileLinesSplit2.size();j++){
                boolean flag = true;
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
                            differentItems.add(csvFileLinesSplit1.get(i));
                            differentItems.add(csvFileLinesSplit2.get(j));
                    }
                }
            }
        }
        return differentItems;
    }
}
