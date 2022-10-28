import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader("C:\\Users\\tahir\\CSC8012 Formative Coursework 1\\data.txt"));
        PrintWriter outFile = new PrintWriter("C:\\Users\\tahir\\CSC8012 Formative Coursework 1\\results.txt");

        // Other declarations
        ArrayList<String> wordsOnly = new ArrayList<>();
        ArrayList<String> formatted = new ArrayList<>();
        ArrayList<String> deDupedSorted;

        // Reading and processing the input data
        // Taking the scanner and reading the file.
        while (inFile.hasNext()) {
            String line = inFile.nextLine();
            String partsOfInput[] = line.split(" ");
            //populating an arraylist which only contains words, no numbers
            for (int i = 0; i < partsOfInput.length; i++) {
                if (partsOfInput[i].matches("[a-zA-Z]+[.,!?:;]?")) {     // only adding to arraylist if all letters or ends in punctuation
                    wordsOnly.add(partsOfInput[i]);
                }
            }
        }

        //populating an arraylist which removes punctuation and makes everything lower case
        for (int i = 0; i < wordsOnly.size(); i++) {
            String temporary = wordsOnly.get(i);
            if (temporary.matches("[a-zA-Z]+[.,!?:;]")) {
                String temp1 = wordsOnly.get(i);
                String temp2 = temp1.substring(0, temp1.length() - 1);
                formatted.add(temp2);
            } else {
                String processedString = (String) wordsOnly.get(i);
                formatted.add(processedString.toLowerCase());
            }
        }

        //populating an arraylist which removes duplicates and sorts the list
        deDupedSorted = (ArrayList) formatted.stream().distinct().sorted().collect(Collectors.toList());

        //creating a counter for each word
        for (String item : deDupedSorted) {
            int counter = 0;
            int wordLength = 0;
            for (String words : formatted) {
                if (words.equals(item)) {
                    counter++;
                }
            }
            int numberOfDigitsInCounter = String.valueOf(counter).length();
            int numberOfSpaces = 30 - item.length() - numberOfDigitsInCounter;
            String spaces = String.format("%1$"+numberOfSpaces+"s", "");  //https://stackoverflow.com/questions/2635076/convert-integer-to-equivalent-number-of-blank-spaces
            System.out.print(item);
            System.out.print(spaces);
            System.out.print(counter + "\n");
        }

        // test to see what's written to input
        for (String item : wordsOnly) {
            System.out.println("input " + item);
        }
        // test to see what's written to formatted
        for (String item : formatted) {
            System.out.println("formatted " + item);
        }
        // test to see what's written to deDupedSorted
        for (String item : deDupedSorted) {
            System.out.println("deDupedSorted " + item);
        }

// Printing out the results
        for(Object str: deDupedSorted) {
            outFile.write(str + System.lineSeparator());
        }
        outFile.close();
    }
}

