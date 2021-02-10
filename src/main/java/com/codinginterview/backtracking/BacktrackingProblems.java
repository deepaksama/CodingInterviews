package com.codinginterview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingProblems {

	/* @formatter:off
	 * Problem 1: BlockedMaze - Find all paths from (0,0) to (n - 1,n - 1) for a given BlockedMaze
	 * Definition : findMazePaths(maze, row, col, path, visited, solutions) will check all the possible paths that can 
	 * 				contribute to destination from give row, col
	 * Recurrence Relation : findMazePaths(maze, row, col, path, visited, solutions) 	= 
	 * 							findMazePaths(maze, row - 1, col, path, visited, solutions) , 
	 * 							findMazePaths(maze, row + 1, col, path, visited, solutions) ,
	 * 							findMazePaths(maze, row, col - 1, path, visited, solutions) ,
	 * 							findMazePaths(maze, row, col + 1, path, visited, solutions)
	 * Base Case: Boundaries hit, maze block hit, already visited, or found valid path
	 * 								
	 * @formatter:on
	 */

	public void findMazePaths(Integer[][] maze, int row, int col, String path, boolean[][] visited,
			List<String> solutions) {
		// Reached the destination
		if (row == maze.length - 1 && col == maze[0].length - 1) {
			// System.out.println(path);
			solutions.add(path);
			return;
		}

		// Base Case
		if (isInvalid(maze, row, col, visited)) {
			return;
		}

		visited[row][col] = true;

		// Check upper
		findMazePaths(maze, row - 1, col, path + "U", visited, solutions);
		// Check bottom
		findMazePaths(maze, row + 1, col, path + "D", visited, solutions);
		// Check left
		findMazePaths(maze, row, col - 1, path + "L", visited, solutions);
		// Check right
		findMazePaths(maze, row, col + 1, path + "R", visited, solutions);

		visited[row][col] = false;
	}

	private boolean isInvalid(Integer[][] maze, int row, int col, boolean[][] visited) {

		boolean upperBoundary = row < 0;
		boolean bottomBoundary = row >= maze.length;
		boolean leftBoundary = col < 0;
		boolean rightBoundary = col >= maze[0].length;

		return (upperBoundary || bottomBoundary || leftBoundary || rightBoundary || maze[row][col] == 1
				|| visited[row][col]);
	}
	
	
	/* @formatter:off
	 * Problem 2: WordSearch
	 * Definition : The word can be constructed from letters of sequentially adjacent cells, where "adjacent" 
	 * 				cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
	 * 				wordSearch(board, row, col, currentWord, word, visited) Given an m x n board and a word, find if the word exists in the grid.
	 * Base Case: Boundaries hit, already visited, or found valid word
	 * Source : LeetCode : #79 - Word Search 					
	 * @formatter:on
	 */

	public boolean wordSearch(char[][] board, String word) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		boolean found = false;
		outerloop:
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				String currentWord = "";
				found = wordSearch(board, row, col, currentWord, word, visited);
				if (found)
					break outerloop;
			}
		}
		return found;
	}

	public boolean wordSearch(char[][] board, int row, int col, String currentWord, String expectedWord,
			boolean[][] visited) {
		// Base condition boundary hit
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
			return false;
		}

		if (expectedWord.equals(currentWord + board[row][col])) {
			return true;
		}

		visited[row][col] = true;
		boolean found = false;
		if (expectedWord.charAt(currentWord.length()) == board[row][col]) {
			found = (wordSearch(board, row - 1, col, currentWord + board[row][col], expectedWord, visited)
					|| wordSearch(board, row + 1, col, currentWord + board[row][col], expectedWord, visited)
					|| wordSearch(board, row, col - 1, currentWord + board[row][col], expectedWord, visited)
					|| wordSearch(board, row, col + 1, currentWord + board[row][col], expectedWord, visited));
		}

		visited[row][col] = false;

		return found;
	}

	//Problem 3.1: Subsets or SuperSet
	public List<List<Integer>> subSets(int [] nums, int start, List<Integer> partial) {
		List<List<Integer>> list = new ArrayList<>(); 
		List<Integer> newPartial = new ArrayList<>(partial);
		if(start >= nums.length) {
			list.add(partial);
			return list;
		}

		list.addAll(subSets(nums, start + 1, partial));
		newPartial.add(nums[start]);
		list.addAll(subSets(nums, start + 1, newPartial));
		return list;
	}

	// Problem 3.2: Subsets with no duplicates or Superset with no duplicates
	public List<List<Integer>> subSets2(int[] nums) {
		sortArray(nums);
		return subSets2(nums, 0, new ArrayList<Integer>(), false);
	}

	public List<List<Integer>> subSets2(int[] nums, int start, List<Integer> partial, boolean isRightTree) {
		List<List<Integer>> list = new ArrayList<>();
		/*
		 * Base condition: stop when the end of arry is reached
		 */
		if (start >= nums.length) {
			list.add(new ArrayList<>(partial));
		} else {
			List<Integer> newPartial = new ArrayList<>(partial);
			/* @formatter:off
			 * Make two recursive calls 
			 * 1. call excluding element at start and 
			 * 2. call with including element at start 
			 * and combine the results from both the calls to get results
			 * @formatter:on
			 */
			/*
			 * As duplicate elements generate duplicate subsets through 
			 * include case in left tree and exclude case in right tree.
			 * To avoid duplicates avoid call to exclude case in right tree for duplicate element
			 */
			if (isRightTree && (start > 0 && nums[start - 1] == nums[start])) {
				newPartial.add(nums[start]);
				list.addAll(subSets2(nums, start + 1, newPartial, true));
			} else {
				list.addAll(subSets2(nums, start + 1, newPartial, false));
				newPartial.add(nums[start]);
				list.addAll(subSets2(nums, start + 1, newPartial, true));
			}
		}
		return list;
	}

	public int[] sortArray(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
		return nums;
	}

	/*
	 * Problem 4: Given an array of integers, generate all the permutations of the
	 * array
	 */
	public void permutations(Integer[] input, List<Integer> partial, boolean[] used, List<List<Integer>> solution) {
		if (null == solution) {
			solution = new ArrayList<>();
		}
		/*
		 * Check if partial solution is valid
		 */
		if (partial.size() == input.length) {
			solution.add(new ArrayList<>(partial));
			return;
		}

		/*
		 * Generate all possible candidates
		 */
		for (int i = 0; i < input.length; i++) {
			if (!used[i]) {
				used[i] = true;
				partial.add(input[i]);
				permutations(input, partial, used, solution);
				used[i] = false;
				partial.remove(partial.size() - 1);
			}
		}
	}

	public List<List<Integer>> permutations(Integer[] input) {
		boolean[] used = new boolean[input.length];
		List<List<Integer>> solution = new ArrayList<>();
		permutations(input, new ArrayList<>(), used, solution);
		return solution;
	}

	/*
	 * Problem 5: Choose K integers from given array.
	 */
	public void chooseKCombinations(Integer[] input, int k, List<Integer> partial, int start,
			List<List<Integer>> solution) {

		if (partial.size() == k) {
			solution.add(new ArrayList<>(partial));
			return;
		}

		for (int i = start; i < input.length; i++) {
			partial.add(input[i]);
			chooseKCombinations(input, k, partial, i + 1, solution);
			partial.remove(partial.size() - 1);
		}
	}

	public List<List<Integer>> chooseKCombinations(Integer[] input, int k) {
		List<List<Integer>> solution = new ArrayList<>();
		chooseKCombinations(input, k, new ArrayList<>(), 0, solution);
		return solution;
	}
}
