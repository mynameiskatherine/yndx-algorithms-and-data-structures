package org.kate.InternshipNov24;

import java.io.*;
import java.util.Arrays;

public class SodaConcentrate {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String size = reader.readLine();
            String[] numbers = reader.readLine().split(" ");


//            Scanner scanner = new Scanner("5\n" +
//                    "1 1 5 4 5\n");
//            String size = scanner.nextLine();
//            String[] numbers = scanner.nextLine().split(" ");

            int[] containers = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                containers[i] = Integer.parseInt(numbers[i]);
            }

            int result = calculate(containers);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculate(int[] containers) {
        int[] sortedContainers = Arrays.copyOf(containers, containers.length);
        Arrays.sort(sortedContainers);
        if (!Arrays.equals(containers, sortedContainers)) {
            return -1;
        } else {
            return sortedContainers[sortedContainers.length - 1] - sortedContainers[0];
        }
    }
}
