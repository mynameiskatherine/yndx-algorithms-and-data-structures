package org.kate.StackQueueDequeue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MinimumForSegment {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] sizeAndK = reader.readLine().split(" ");
            int segmentSize = Integer.parseInt(sizeAndK[1]);
            String[] numbers = reader.readLine().split(" ");
            int[] sequence = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                sequence[i] = Integer.parseInt(numbers[i]);
            }

//            Scanner scanner = new Scanner("7 3\n" +
//                    "1 1 1 2 3 4 5 5 5\n");
//            String[] sizeAndK = scanner.nextLine().split(" ");
//            int segmentSize = Integer.parseInt(sizeAndK[1]);
//            String[] numbers = scanner.nextLine().split(" ");
//            int[] sequence = new int[numbers.length];
//            for (int i = 0; i < numbers.length; i++) {
//                sequence[i] = Integer.parseInt(numbers[i]);
//            }

            int[] result = calculate(sequence, segmentSize);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                builder.append(result[i]).append("\n");
            }

            writer.write(builder.toString().trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] calculate(int[] sequence, int segmentSize) {
        int[] minimums = new int[sequence.length - segmentSize + 1];
        Deque<List<Integer>> deque = new ArrayDeque<>();
        for (int i = 0; i < sequence.length; i++) {
            while (!deque.isEmpty() && sequence[i] <= deque.peekLast().get(1)) {
                deque.removeLast();
            }
            if (deque.isEmpty() || sequence[i] > deque.peekLast().get(1)) {
                deque.addLast(List.of(i, sequence[i]));
            }

            if (i >= segmentSize - 1) {
                minimums[i - segmentSize + 1] = deque.getFirst().get(1);
            }
            if (deque.getFirst().get(0).equals(i - segmentSize + 1)) {
                deque.removeFirst();
            }
        }
        return minimums;
    }
}
