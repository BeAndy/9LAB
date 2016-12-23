import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) {
        Printer first = new Printer("First");
        Printer second = new Printer("Second");
        List<String> currentMsg;
        currentMsg = readListOfStrings(INPUT_FILE);
        List<String> prevMsg=currentMsg;
        List <String>uniqueMsg;
        first.start();
        second.start();
        while (true) {
            if (currentMsg.size() != 0) {
                for (int i = 0; i < currentMsg.size(); i++) {
                    boolean notDoneYet = true;
                    while (notDoneYet) {
                        String currentTask = currentMsg.get(i);
                        if (first.isReady()) {
                            first.setTask(currentTask);
                            notDoneYet = false;
                        } else {
                            if (second.isReady()) {
                                second.setTask(currentTask);
                                notDoneYet = false;
                            }
                        }
                    }
                }
            }
           // uniqueMsg = formNewList(prevMsg, readListOfStrings(INPUT_FILE));
          //  currentMsg=uniqueMsg;
          /*  if(!uniqueMsg.isEmpty()) {
                prevMsg = currentMsg;
                for (String s : uniqueMsg) {
                    prevMsg.add(s);
                }
                currentMsg = uniqueMsg;
            }
            else
                currentMsg = uniqueMsg;*/

        }
    }


    private static List<String> readListOfStrings(String path) {
        List<String> listOfStrings = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String tempString = br.readLine();
            while (tempString != null) {
                listOfStrings.add(tempString);
                tempString = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfStrings;
    }

    private static List<String> formNewList(List<String> oldList, List<String> newList) {
        List<String> uniqueList = new ArrayList<>();
        for (int i = 0; i < newList.size(); i++) {
            boolean isUnique = true;
           for (int j = 0; j < oldList.size(); j++)
                if (newList.get(i).compareTo(oldList.get(j)) == 0) {
                    j = newList.size();
                    isUnique = false;
                }
            if (isUnique) {
                uniqueList.add(newList.get(i));
            }
        }
        //uniqueList.forEach(System.out::println);
        return uniqueList;
    }
}
