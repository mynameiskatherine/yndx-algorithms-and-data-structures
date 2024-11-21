package org.kate.PrefixSumsTwoPointers;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SumTripleMultiplications {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
//
//            String size = reader.readLine();
//            String[] numbers = reader.readLine().split(" ");
//
//
            Scanner scanner = new Scanner("100\n" +
                    "545269 697218 263222 274437 292188 659054 196272 395733 375953 605087 822379 111051 397736 209483 692646 79062 600107 381052 543509 352890 928981 781199 149150 173519 613336 217334 218266 345855 947225 588534 142298 920716 657199 580778 507552 327239 983783 532606 890785 295834 825155 394932 705098 882752 75830 892195 807032 418631 684969 485107 798782 236373 311418 502434 68259 63904 868920 965058 829281 449717 756520 252216 408616 154034 184131 811327 170584 54411 613685 569315 845322 935816 199200 10311 92488 445958 981591 408927 519505 46898 440995 591478 710294 607889 555650 971370 852864 371003 618787 548459 394599 896155 387947 298623 703196 382577 571827 849089 732192 400976");
            String size = scanner.nextLine();
            String[] numbers = scanner.nextLine().split(" ");

            int[] sequence = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                sequence[i] = Integer.parseInt(numbers[i]);
            }

            long result = calculate(sequence);
            writer.write(String.valueOf(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calculate(int[] sequence) {
        int length = sequence.length;
        long[] prefixSum = new long[length];
        prefixSum[0] = sequence[0];
        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + sequence[i];
        }
        System.out.println(Arrays.toString(prefixSum));
        long mod = 1000000007L;
        long sum = 0L;
        for (int i = 1; i < length - 1; i++) {
            long modMultiplication = ((((prefixSum[i - 1] % mod)
                    * (sequence[i] % mod))
                    % mod)
                    * ((prefixSum[length - 1] - prefixSum[i]) % mod))
                    % mod;
            System.out.println(modMultiplication);
            sum = sum + modMultiplication;
            System.out.println(sum);
        }

        return sum % mod;
    }
}
