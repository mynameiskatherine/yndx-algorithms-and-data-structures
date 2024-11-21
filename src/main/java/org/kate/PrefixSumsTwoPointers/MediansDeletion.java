package org.kate.PrefixSumsTwoPointers;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MediansDeletion {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String size = reader.readLine();
//            String[] numbers = reader.readLine().split(" ");


            Scanner scanner = new Scanner("1\n" +
                    "1 2\n");
            String size = scanner.nextLine();
            String[] numbers = scanner.nextLine().split(" ");

            int[] sequence = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                sequence[i] = Integer.parseInt(numbers[i]);
            }
            Arrays.sort(sequence);

            String[] result = calculate(sequence);
            writer.write(String.join(" ", result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] calculate(int[] sequence) {
        String[] result = new String[sequence.length];
        int l = sequence.length / 2 - 1;
        int r = sequence.length / 2;
        int remainingLength = sequence.length;
        for (int i = 0; i < result.length; i++) {
            if (remainingLength % 2 == 0) {
                result[i] = String.valueOf(sequence[l]);
                l--;
            } else {
                result[i] = String.valueOf(sequence[r]);
                r++;
            }
            remainingLength--;
        }
        return result;
    }
}
