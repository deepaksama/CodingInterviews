package com.codinginterview.backtracking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BacktrackingProblemsTest {

	private BacktrackingProblems solutions;

	@BeforeEach
	public void setup() {
		solutions = new BacktrackingProblems();
	}

	// Problem 1
	private static Stream<Arguments> provideMazeForFindMazePaths() {
		return Stream.of(
		// @formatter:off
			Arguments.of(
				new Integer[][] {
					{ 0, 1, 0, 0},
					{ 0, 0, 0, 0},
					{ 0, 1, 0, 0},
					{ 0, 1, 1, 0}
				}, Arrays.asList(new String[] {"DRRURDDD","DRRDRD","DRRRDD"})
			),
			Arguments.of(
				new Integer[][] {
					{ 0, 1, 0},
					{ 0, 0, 0},
					{ 0, 1, 0},
					{ 0, 1, 0}
				}, Arrays.asList(new String[] {"DRRDD"})
			));
		//@formatter:on
	}

	@ParameterizedTest
	@MethodSource("provideMazeForFindMazePaths")
	void test_findMazePaths(Integer[][] maze, List<String> expectedPaths) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		List<String> actualPaths = new ArrayList<>();
		solutions.findMazePaths(maze, 0, 0, "", visited, actualPaths);
		Assert.assertEquals(expectedPaths.size(), actualPaths.size());
		expectedPaths.forEach(path -> assertTrue(actualPaths.contains(path)));
	}

	// Problem 2
	private static Stream<Arguments> provideBoardForWordSearch() {
		return Stream.of(
		// @formatter:off
				
			Arguments.of(
				new char[][] {
					{ 'A', 'B', 'C', 'E'},
					{ 'S', 'F', 'C', 'S'},
					{ 'A', 'D', 'E', 'E'}
				}, "ABCCED" , true),
				
			Arguments.of(
				new char[][] {
					{ 'A', 'B', 'C', 'E'},
					{ 'S', 'F', 'C', 'S'},
					{ 'A', 'D', 'E', 'E'}
				}, "SEE" , true),
			Arguments.of(
				new char[][] {
					{ 'A', 'B', 'C', 'E'},
					{ 'S', 'F', 'C', 'S'},
					{ 'A', 'D', 'E', 'E'}
				}, "ABCB" , false),
			Arguments.of(
				new char[][] {
					{ 'C', 'A', 'A'},
					{ 'A', 'A', 'A'},
					{ 'B', 'C', 'D'}
				}, "AAB" , true)
			);
		//@formatter:on
	}

	@ParameterizedTest
	@MethodSource("provideBoardForWordSearch")
	void test_wordSearch(char[][] board, String word, boolean expected) {
		boolean actual = solutions.wordSearch(board, word);
		assertEquals(expected, actual);
	}

	// Problem 3
	private static Stream<Arguments> provideListForSubSets() {
		return Stream.of(
		// @formatter:off
			Arguments.of(
				new int[] {1,2,3},
				List.of(List.of(),List.of(3),
						List.of(2),List.of(2,3),
						List.of(1),List.of(1,3),
						List.of(1,2),List.of(1,2,3))
			),

			Arguments.of(
					new int[] {1,1,2},
					List.of(List.of(),List.of(2),
							List.of(1),List.of(1,2),
							List.of(1,1),List.of(1,1,2))
			),
			Arguments.of(
					new int[] {1,1,2, 2},
					List.of(List.of(),List.of(1),
							List.of(1,1),List.of(1,1,2),
							List.of(1,1,2,2),List.of(1,2)),
							List.of(1,2,2),List.of(2),List.of(2,2)
				)
			);
		//@formatter:on
	}

	@ParameterizedTest
	@MethodSource("provideListForSubSets")
	void test_subSets(int[] nums, List<List<Integer>> expected) {
		List<List<Integer>> actualList = solutions.subSets2(nums);
		System.out.print("[");
		actualList.forEach(l -> System.out.print(l));
		System.out.println("]");
		expected.stream().forEach(l -> assertTrue(actualList.contains(l)));
	}
}
