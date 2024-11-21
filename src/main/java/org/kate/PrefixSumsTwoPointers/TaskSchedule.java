package org.kate.PrefixSumsTwoPointers;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskSchedule {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] tasksAndK = reader.readLine().split(" ");
//            int coefficient = Integer.parseInt(sizeAndDistance[1]);
//            String[] numbers = reader.readLine().split(" ");


            Scanner scanner = new Scanner("3 0\n" +
                    "1 3 1\n");
            String[] tasksAndK = scanner.nextLine().split(" ");
            int coefficient = Integer.parseInt(tasksAndK[1]);
            String[] numbers = scanner.nextLine().split(" ");

            int[] tasks = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                tasks[i] = Integer.parseInt(numbers[i]);
            }
            Arrays.sort(tasks);

            int result = calculate(tasks, coefficient);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculate(int[] tasks, int coefficient) {
        int days = 1;
        int left = 0;
        int right = 1;

        while (right < tasks.length) {
            if (tasks[right] - tasks[left] > coefficient) {
                left++;
                right++;
            } else {
                right++;
                days++;
            }
        }
        return days;
    }
}
