package org.kate.PrefixSumsTwoPointers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class LuckyCarPlatesSum {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] sizeAndSum = reader.readLine().split(" ");
            int luckyNumber = Integer.parseInt(sizeAndSum[1]);
            String[] sequence = reader.readLine().split(" ");


//            Scanner scanner = new Scanner("5 10\n" +
//                    "1 2 3 4 1\n");
//            String[] sizeAndSum = scanner.nextLine().split(" ");
//            int luckyNumber = Integer.parseInt(sizeAndSum[1]);
//            String[] sequence = scanner.nextLine().split(" ");

            int result = calculate(sequence, luckyNumber);
            writer.write(String.valueOf(result));

        } catch (Exception e) {
            throw new RuntimeException("IO Exception");
        }
    }

    private static int calculate(String[] sequence, int luckyNumber) {
        Map<Integer, Integer> prefixSums = new HashMap<>(sequence.length + 1, 1);
        prefixSums.put(0, 0);
        int currentSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            currentSum = currentSum + Integer.parseInt(sequence[i]);
            prefixSums.put(currentSum, prefixSums.containsKey(currentSum - luckyNumber) ? 1 : 0);
        }
        Integer result = prefixSums.values().stream().reduce(0, Integer::sum);
        return result;
    }
}
