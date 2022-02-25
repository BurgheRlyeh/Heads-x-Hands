package com.github.burgherlyeh.handh.testsolution;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class with a static method solution - solving a test problem for those entering the Heads x Hands development school.
 *
 * At the input, solution receives a parameter n - a natural number.
 * Next, it generates n-arrays and fills them with random numbers, with each array having a random unique size.
 * Each array with an even ordinal number is sorted in ascending order, with an odd ordinal number - in descending order.
 * At the output, solution returns an array with sorted arrays.
 */
public class Solution {
    private static final int SIZE_BOUND = 100;
    private static final int NUMBER_BOUND = 100;

    /**
     * @param n number of arrays
     * @return array of n arrays
     */
    public static int[][] solution(int n) {
        // check n is natural
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        var arrays = new int[n][];                  // array of arrays of numbers
        var sizes = randomUniqueNumbers(n);   // array with random unique numbers - sizes

        for (var i = 0; i < n; ++i) {
            // list of random numbers to sort
            var randomList = new Random()
                    .ints(sizes[i], 0, NUMBER_BOUND)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));

            // sort list in required order and collect to int[]
            arrays[i] = randomList.stream()
                    .sorted(i % 2 == 0 ? Comparator.naturalOrder() : Collections.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        return arrays;
    }

    /**
     * @param n size of array
     * @return array of n random unique numbers
     */
    private static int[] randomUniqueNumbers(int n) {
        var set = new HashSet<Integer>();

        while (set.size() != n) {
            set.add(new Random().nextInt(SIZE_BOUND) + 1);
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
