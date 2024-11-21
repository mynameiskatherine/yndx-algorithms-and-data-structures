package org.kate.Testing;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TableauLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Scanner scanner = new Scanner("4\n" +
                ".##.\n" +
                ".##.\n" +
                ".##.\n" +
                "....\n");
        int size = 0;
        size = Integer.parseInt(scanner.nextLine());
        String[] sign = new String[size];
        for (int i = 0; i < size; i++) {
            sign[i] = scanner.nextLine();
        }
//        String[] sign = {"####","####", "##.#","####"};

        String result = decryptSign(sign);

        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    private static String decryptSign(String[] sign) {
        char[][] reducedSign1 = reduceRaws(sign);
        System.out.println(Arrays.deepToString(reducedSign1));

        String[] truncatedSign = new String[sign.length];
        for (int i = 0; i < sign.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < reducedSign1.length; j++) {
                builder.append(reducedSign1[j][i]);
            }
            truncatedSign[i] = builder.toString();
        }
        System.out.println(Arrays.toString(truncatedSign));

        char[][] reducedSign2 = reduceRaws(truncatedSign);
        System.out.println(Arrays.deepToString(reducedSign2));

        char[][] letterI = {{'#'}};
        char[][] letterO = {{'#','#','#'},{'#','.','#'},{'#','#','#'}};
        char[][] letterC = {{'#','#','#'},{'#','.','#'}};
        char[][] letterL = {{'#','#'},{'.','#'}};
        char[][] letterH = {{'#','#','#'},{'.','#','.'},{'#','#','#'}};
        char[][] letterP = {{'#','#','#','#'},{'#','.','#','.'},{'#','#','#','.'}};

        if (Arrays.deepEquals(reducedSign2, letterI)) {
            return "I";
        } else if (Arrays.deepEquals(reducedSign2, letterO)) {
            return "O";
        } else if (Arrays.deepEquals(reducedSign2, letterC)) {
            return "C";
        } else if (Arrays.deepEquals(reducedSign2, letterL)) {
            return "L";
        } else if (Arrays.deepEquals(reducedSign2, letterH)) {
            return "H";
        } else if (Arrays.deepEquals(reducedSign2, letterP)) {
            return "P";
        } else {
            return "X";
        }
    }

    private static char[][] reduceRaws(String[] sign) {
        String fullEmptyString = "..........";
        String emptyString = fullEmptyString.substring(0, sign[0].length());
        int reducedRaws = 0;
        for (int i = 0; i < sign.length; i++) {
            if (sign[i].equals(emptyString) || ((i < sign.length - 1) && sign[i].equals(sign[i + 1]))) {
                sign[i] = "";
                reducedRaws++;
            }
        }

        char[][] reducedSign = new char[sign.length - reducedRaws][sign.length];
        if (reducedRaws > 0) {
            int counter = 0;
            for (String s : sign) {
                if (!s.isEmpty()) {
                    reducedSign[counter] = s.toCharArray();
                    counter++;
                }
            }
        } else {
            for (int i = 0; i < sign.length; i++) {
                reducedSign[i] = sign[i].toCharArray();
            }
        }
        return reducedSign;
    }
}
