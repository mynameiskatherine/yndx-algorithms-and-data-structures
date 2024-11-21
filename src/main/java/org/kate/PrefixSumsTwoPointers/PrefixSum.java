package org.kate.PrefixSumsTwoPointers;

import java.io.*;
import java.util.Scanner;

public class PrefixSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Scanner scanner = new Scanner("5\n" +
                "10 -4 5 0 2");

//        int size = Integer.parseInt(reader.readLine());
//        String numbers = reader.readLine();
        int size = Integer.parseInt(scanner.nextLine());
        String numbers = scanner.nextLine();
        String[] array = numbers.split(" ");

        int[] result = calculate(array);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]).append(" ");
        }
        writer.write(builder.toString().trim());

        reader.close();
        writer.close();
    }

    private static int[] calculate(String[] array) {
        int[] prefixSums = new int[array.length];
        prefixSums[0] = Integer.parseInt(array[0]);
        for (int i = 1; i < array.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + Integer.parseInt(array[i]);
        }
        return prefixSums;
    }
}
