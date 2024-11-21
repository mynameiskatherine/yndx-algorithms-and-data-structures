package org.kate.PrefixSumsTwoPointers;

import java.io.*;
import java.util.Scanner;

public class CensoredComposition {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] sizeAndCens = reader.readLine().split(" ");
//            String string = reader.readLine();

            Scanner scanner = new Scanner("50 0\n" +
                    "bbbakbbbabazaababbbaabbbaybaabbaabababbbbbaabbaabb\n");
            String[] sizeAndCens = scanner.nextLine().split(" ");
            String string = scanner.nextLine();

            long cens = Long.parseLong(sizeAndCens[1]);
            char[] charString = string.toCharArray();

            int result = calculate(charString, cens);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculate(char[] charString, long cens) {
        int maxLength = 0;
        int[][] countArray = new int[2][charString.length + 1];
        countArray[0][0] = 0; //a
        countArray[1][0] = 0; //b
        long countedCens = 0;
        int l = 0;
        for (int r = 1; r < countArray[0].length; r++) {
            countArray[0][r] = countArray[0][r - 1];
            countArray[1][r] = countArray[1][r - 1];
            if (charString[r - 1] == 'a') {
                countArray[0][r] = countArray[0][r] + 1;
            } else if (charString[r - 1] == 'b') {
                countArray[1][r] = countArray[1][r - 1] + 1;
                countedCens = countedCens + (countArray[0][r] - countArray[0][l]);
            }

            while ((countedCens > cens) && (l < r - 1)) {
                l++;
                if (charString[l - 1] == 'a') {
                    countedCens = countedCens - (countArray[1][r] - countArray[1][l]);
                }
            }

            if (countedCens <= cens) {
                maxLength = Math.max(maxLength, r - l);
            }
        }

        return maxLength;
    }
}
