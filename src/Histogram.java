import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;
/**
 * Title: Histogram
 * Author: Nima Mahanloo
 * Date: March 14, 2023
 * Abstract: This program reads user text files containing characters with newline separations.
 * Count and sort the read characters based on the number of their occurrence in the user file.
 * Finally, it displays the data,
 * including each character occurrence count and the sorted characters in a designed style and format.
 */
public class Histogram {
    private static String filename;
    private static char[] letter;
    private static int[] letterCount;
    private static final int A = 65;
    private static final int L = 76;
    private Histogram() {
        filename = null;
        letter = new char[200];
        letterCount = new int[11];
    }
    public static String getFileName() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input filename : ");
        filename = scan.nextLine();;
        return filename;
    }
    public static void read(char[] letter, int[] letterCount, String filename) {
        Scanner scan = null;
        File file = new File(filename);
        String input = null;
        int inputCharAscii = -1;
        int index = 0;
        int charIndex = 0;
        for (index = 0; index < 11; index++) {
            letterCount[index] = 0;
        }
        try {
            if (file.exists()) {
            }
            scan = new Scanner(file);
        } catch (IOException e) {
            System.out.println("File " + filename + " not exists!");
            exit(0); // It terminates the program without any error.
        }
        while (scan != null && scan.hasNext()) {
            input = scan.nextLine();
            letter[charIndex] = input.charAt(0);
            /* I love to work with Ascii codes in cases like this one.
             * I have had similar experiences in this regard since my previous academic projects, in which I usually coded in C++.
             * Therefore, I have used my previous and recent knowledge and styles in coding this program.
             */
            inputCharAscii = (int)letter[charIndex];
            index = 0;
            for (int ascii = A; ascii < L; ascii++) {
                if (ascii == inputCharAscii) {
                    letterCount[index] += 1;
                    ascii = L;
                }
                index++;
            }
            charIndex++;
        }
    }
    public static void sort(char[] letter, int[] letterCount) {
        int charIndex = 0;
        int index = 0;
        int occurrence = 0;
        int maxOccurrence = 0;
        /* The disabled following code sorts the char array alphabetically ascending.
         * for (int index = 0; index < 11; index++) {
         *     if (letterCount[index] > 0) {
         *        for (int count = letterCount[index]; count > 0; count--) {
         *            letter[charIndex] = (char)(ascii+index);
         *            charIndex++;
         *        }
         *     }
         * }
         */
        // The following code sorts the char array based on the occurrence of each character and alphabetically ascending.
        for (index = 0; index < 11; index++) {
            if (letterCount[index] > maxOccurrence) {
                maxOccurrence = letterCount[index];
            }
        }
        for (occurrence = 1; occurrence <= maxOccurrence; occurrence++) {
            for (index = 0; index < 11; index++) {
                if (letterCount[index] == occurrence) {
                    for (int count = occurrence; count > 0; count--) {
                        letter[charIndex] = (char)(A+index);
                        charIndex++;
                    }
                }
            }
        }
    }
    public static void display(char[] letter, int[] letterCount) {
        int index;
        int maxOccurrence = 0;
        System.out.println("Char occurrence");
        for (index = 0; index < 11; index++) {
            if (letterCount[index] > 0) {
                System.out.println(((char)(A+index)) + "\s" + letterCount[index]);
            }
            if (letterCount[index] > maxOccurrence) {
                maxOccurrence = letterCount[index];
            }
        }
        System.out.print("\n" + "=============================");
        for (int occurrence = maxOccurrence; occurrence > 0; occurrence--) {
            Boolean firstSpace = false;
            for (index = 0; index < 11; index++) {
                if (letterCount[index] == occurrence) {
                    if (firstSpace == false) {
                        firstSpace = true;
                        int totalOccurrence = 0;
                        for (int countIndex = 0; countIndex < 11; countIndex++) {
                            if (letterCount[countIndex] == occurrence) {
                                totalOccurrence++;
                            }
                        }
                        System.out.print("\n|\t\s" + occurrence + "|");
                        for (int space = 0; space < ((11-totalOccurrence)*2); space++) {
                            System.out.print("\s");
                        }
                    }
                    System.out.print(((char)(A+index)) + "\s");
                }
            }
        }
        System.out.println("\n-----------------------------");
        System.out.print("\t\s\s\s");
        for (index = 0; index < 11; index++) {
            if (letterCount[index] == 0) {
                System.out.print(((char)(A+index)) + "\s");
            }
        }
        for (index = 0; ((index < letter.length) && (letter[index] != 0)); index++) {
            if ((index > 0) && (letter[index] != letter[index-1])) {
                System.out.print(letter[index] + "\s");
            }
            else if (index == 0) {
                System.out.print(letter[index] + "\s");
            }
        }
    }
    public static void main(String[] args) {
        Histogram histogram = new Histogram();
        histogram.getFileName();
        histogram.read(letter, letterCount, filename);
        histogram.sort(letter, letterCount);
        histogram.display(letter, letterCount);
    }
}
