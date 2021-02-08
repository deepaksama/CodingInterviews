package com.codinginterview.recursion;

public class RecursionProblems {

	/* @formatter:off
	 * Problem 1: Check numbers in given array are in sequence 
	 * Definition : areInSequence(a, i) will check if the elements of array from i to n are in sequence
	 * Recurrence Relation : areInSequence(a,i) 	= areInSequence(a,i+1) -> if(a[i] == a[i+1] - 1)
	 * Base Case: areInSequence(a,i) = 	true -> if(i == a.length - 1)
	 * 								
	 * @formatter:on
	 */
	public boolean areInSequence(Integer[] a, int i) {
		if (a.length == 0) {
			return true;
		}

		if (i == a.length - 1) {
			return true;
		} else {
			if (a[i] == a[i + 1] - 1) {
				return areInSequence(a, i + 1);
			}

			return false;
		}
	}

	/*
	 * @formatter:off
	 * Problem 2: Find sum of elements of an array
	 * Definition : sum(a, i) is sum of elements of array from 0 to i
	 * Recursive Relation : sum(a, i) 	= 	a[i] + sum(a, i - 1) -> If i > 1
	 * Base Case: sum(a, i)	=	a[i] > If i = 0
	 * @formatter:on
	 */
	public Integer sum(Integer[] a, int i) {
		if (i < 0) {
			return 0;
		}

		if (i == 0) {
			return a[i];
		}

		return a[i] + sum(a, i - 1);
	}
	
	/*
	 * @formatter:off
	 * Problem 3: Find sum of digits of a number
	 * Definition : sumOfDigits(n) is sum of digits of a number n
	 * Recursive Relation : sumOfDigits(n) 	= 	( n % 10 ) + sumOfDigits(n / 10) -> If n > 0
	 * Base Case: sumOfDigits(n)	=	0 > If n = 0
	 * @formatter:on
	 */
	public Integer sumOfDigits(Integer n) {
		if (n == 0) {
			return 0;
		}

		return (n % 10) + sumOfDigits(n / 10);
	}

	
}
