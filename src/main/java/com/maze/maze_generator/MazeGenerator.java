package com.maze.maze_generator;

import com.maze.models.MazeCell;

public interface MazeGenerator {
  /**
   * Generate a maze.
   *
   * @param initalCell Cell from where maze generation begins.
   * @return Fully generated maze.
   */
  public MazeCell[][] generate(MazeCell initalCell);
}
