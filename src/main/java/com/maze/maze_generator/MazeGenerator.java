package com.maze.maze_generator;

import com.maze.models.Maze;
import com.maze.models.MazeCell;

public interface MazeGenerator {
  /**
   * Generate a maze pattern within the provided maze object.
   *
   * @param maze Maze which will be modified to generate the maze pattern.
   * @param initalCell Cell from where maze generation begins.
   * @return Fully generated maze.
   */
  void generate(Maze maze, MazeCell initialCell);
}
