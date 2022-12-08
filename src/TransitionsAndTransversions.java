import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransitionsAndTransversions {
    public static void main(String[] args) {
        String filename = "src/rosalind_tran.txt";
        ArrayList<String> sequences = parseDNAFASTA(filename);
        System.out.println(calculateTTRatio(sequences)); // 2.44
    }

    /**
     * @param DNAs ArrayList of 2 DNA strings of equal length
     * @return (double) of the ratio of transitions to transversions
     */
    public static double calculateTTRatio(ArrayList<String> DNAs) {
        // Get the two DNA strings
        String DNA1 = DNAs.get(0), DNA2 = DNAs.get(1);
        // Calculate the number of transitions and transversions
        int transitions = 0, transversions = 0;
        // Iterate through the two strings
        for (int i = 0; i < DNA1.length(); i++) {
            // If they're the same, skip
            if (DNA1.charAt(i) == DNA2.charAt(i)) {
                continue;
            } else if ((DNA1.charAt(i) == 'A' && DNA2.charAt(i) == 'G') || (DNA1.charAt(i) == 'G' && DNA2.charAt(i) == 'A') || (DNA1.charAt(i) == 'C' && DNA2.charAt(i) == 'T') || (DNA1.charAt(i) == 'T' && DNA2.charAt(i) == 'C')) {
                // If they're a transition, increment transitions
                // Transition is when an A is swapped with a G or vice versa.
                transitions++;
            } else if ((DNA1.charAt(i) == 'A' && DNA2.charAt(i) == 'C') || (DNA1.charAt(i) == 'C' && DNA2.charAt(i) == 'A') || (DNA1.charAt(i) == 'A' && DNA2.charAt(i) == 'T') || (DNA1.charAt(i) == 'T' && DNA2.charAt(i) == 'A') || (DNA1.charAt(i) == 'G' && DNA2.charAt(i) == 'T') || (DNA1.charAt(i) == 'T' && DNA2.charAt(i) == 'G') || (DNA1.charAt(i) == 'G' && DNA2.charAt(i) == 'C') || (DNA1.charAt(i) == 'C' && DNA2.charAt(i) == 'G')) {
                // If they're a transversion, increment transversions
                // Transversion is when an C is swapped with a T or vice versa.
                transversions++;
            }
        }
        System.out.println("Transitions: " + transitions);
        System.out.println("Transversions: " + transversions);
        return (double) transitions / transversions;
    }
    /**
     * Parse the FASTA file into an ArrayList of Strings with the two strings being the two DNA sequences
     *
     * @param filename the name of the file to parse
     * @return an ArrayList of Strings
     */
    public static ArrayList<String> parseDNAFASTA(String filename) {
        ArrayList<String> DNAList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder DNAtoAdd = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // If it's a header, add the DNA to the list and start a new DNA string
                if (line.startsWith(">")) {
                    if (DNAtoAdd.length() > 0) {
                        DNAList.add(DNAtoAdd.toString());
                        DNAtoAdd = new StringBuilder();
                    }
                } else {
                    DNAtoAdd.append(line);
                }
            }
            DNAList.add(DNAtoAdd.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DNAList;
    }
}
