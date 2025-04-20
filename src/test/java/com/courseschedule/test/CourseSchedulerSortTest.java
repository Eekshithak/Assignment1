package com.courseschedule.test;

import com.courseschedule.CourseSchedulerSort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CourseSchedulerSortTest {

    private final CourseSchedulerSort scheduler = new CourseSchedulerSort();

    // --------------------------------------
    // Parameterized Tests for ECC & BCC using CSV files
    // --------------------------------------

    @ParameterizedTest
    @CsvFileSource(resources = "/ECC_testCases.csv", numLinesToSkip = 1)
    public void testFindOrder_ECC_CSV(int numCourses, String prerequisitesStr, String expectedStr) {
        int[][] prerequisites = parsePrerequisites(prerequisitesStr);
        int[] expected = parseExpected(expectedStr); // This line covers parseExpected()
        int[] result = scheduler.findOrder(numCourses, prerequisites);
        assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BCC_testCases.csv", numLinesToSkip = 1)
    public void testFindOrder_BCC_CSV(int numCourses, String prerequisitesStr, String expectedStr) {
        int[][] prerequisites = parsePrerequisites(prerequisitesStr);
        int[] expected = parseExpected(expectedStr);
        int[] result = scheduler.findOrder(numCourses, prerequisites);
        assertArrayEquals(expected, result);
    }

    // --------------------------------------
    // Helper Methods
    // --------------------------------------

    private int[][] parsePrerequisites(String input) {
        if (input == null || input.trim().equalsIgnoreCase("null") || input.trim().isEmpty()) {
            return new int[0][0]; // covered if any row has "null" or empty
        }

        String[] pairs = input.trim().split(";");
        int[][] prerequisites = new int[pairs.length][2];

        for (int i = 0; i < pairs.length; i++) {
            String[] nums = pairs[i].split(",");
            if (nums.length != 2) {
                // won't get covered since we're removing the invalid format test
                continue;
            }
            prerequisites[i][0] = Integer.parseInt(nums[0].trim());
            prerequisites[i][1] = Integer.parseInt(nums[1].trim());
        }
        return prerequisites;
    }

    private int[] parseExpected(String input) {
        if (input == null || input.trim().equalsIgnoreCase("null") || input.trim().isEmpty()) {
            return new int[0]; // this needs to be hit at least once (you already have such rows in your CSV)
        }

        return Arrays.stream(input.trim().split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
