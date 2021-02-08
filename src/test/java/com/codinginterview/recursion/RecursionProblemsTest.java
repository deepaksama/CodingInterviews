package com.codinginterview.recursion;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class RecursionProblemsTest {
	private RecursionProblems solutions;

	@BeforeEach
	public void setup() {
		solutions = new RecursionProblems();
	}

	//Problem 1
	private static Stream<Arguments> provideArrayForAreInSequence() {
		return Stream.of(Arguments.of(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }, true), // In sequence
				Arguments.of(new Integer[] { 1, 3, 4, 5, 6, 7 }, false), // Non in sequence
				Arguments.of(new Integer[] {}, true), // 0 elements
				Arguments.of(new Integer[] { 1 }, true) // Single element
		);
	}

	@ParameterizedTest
	@MethodSource("provideArrayForAreInSequence")
	void test_areInSequence(Integer [] a, boolean expected) {
		boolean actualValue = solutions.areInSequence(a, 0);
		Assert.assertEquals(expected, actualValue);
	}
	
	//Problem 2
	private static Stream<Arguments> provideArrayForSum() {
		return Stream.of(Arguments.of(new Integer[] { 1, 2, 3, 4, 5 }, 15), // In sequence
				Arguments.of(new Integer[] { 1, 3, 4}, 8), // Non in sequence
				Arguments.of(new Integer[] {}, 0), // 0 elements
				Arguments.of(new Integer[] { 1 }, 1) // Single element
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideArrayForSum")
	void test_sum(Integer [] a, Integer expected) {
		Integer actualSumOfElements = solutions.sum(a, a.length - 1);
		Assert.assertEquals(expected, actualSumOfElements);
	}
	
	//Problem 3
	private static Stream<Arguments> provideNumberForSumOfDigits() {
		return Stream.of(Arguments.of(12345, 15), // In sequence
				Arguments.of(134, 8), 
				Arguments.of(0, 0)
		);
	}
	
	@ParameterizedTest
	@MethodSource("provideNumberForSumOfDigits")
	void test_sumOfDigits(Integer n, Integer expected) {
		Integer actualSumOfDigits = solutions.sumOfDigits(n);
		Assert.assertEquals(expected, actualSumOfDigits);
	}
}
