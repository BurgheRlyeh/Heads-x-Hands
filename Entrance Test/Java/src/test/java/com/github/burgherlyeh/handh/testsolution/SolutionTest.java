package com.github.burgherlyeh.handh.testsolution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    static public int RANDOM_TESTS_NUMBER = 10;
    static public int SIZE_BOUND = 100;

    static public List<Integer> numbers() {
        return new Random()
                .ints(RANDOM_TESTS_NUMBER, 1, SIZE_BOUND)
                .boxed()
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("numbers")
    void randomNumberTest(int n) {
        var result = Solution.solution(n);

        assertEquals(result.length, n);

        var set = new HashSet<Integer>();
        for (int i = 0; i < n; ++i) {
            assertTrue(set.add(result[i].length));

            var sorted = Arrays.stream(result[i])
                    .boxed()
                    .sorted(i % 2 == 0 ? Comparator.naturalOrder() : Collections.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();

            assertArrayEquals(result[i], sorted);
        }

    }

    @Test
    void notNaturalParameterTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Solution.solution(0)
        );
    }
}
