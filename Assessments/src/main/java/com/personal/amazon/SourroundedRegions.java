//**********************************************************************
// Copyright (c) 2019 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package com.personal.amazon;

/*Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of
 * the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected
 * to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent
 *  cells connected horizontally or vertically.
 * */
public class SourroundedRegions
{
    public void solve(char[][] board)
    {
        if (board == null || board.length == 0)
            return;

        int rows = board.length; //row
        int columns = board[0].length; //Columns

        boolean flag = false;

        for (int row = 1; row < rows - 1; row++)
        {
            for (int column = 1; column < columns - 1; column++)
            {
                if (board[row][column] == 'O')
                {
                    if (((row - 1) == 0 && board[0][column] == 'O') ||
                            ((column - 1) == 0 && board[row][0] == 'O') ||
                            ((row + 1) == rows - 1 && board[rows - 1][column] == 'O') ||
                            ((column + 1) == columns - 1 && board[row][columns - 1] == 'O'))
                    {
                        board[row][column] = resolve(row, column, 'O', board);
                        flag = true;
                    }
                    else if (flag)
                    {
                        board[row][column] = resolve(row, column, 'O', board);
                    }
                    else if (board[row - 1][column] == 'O')
                    {
                        board[row][column] = resolve(row, column, 'O', board);
                        flag = true;
                    }
                    else
                    {
                        board[row][column] = 'F';
                    }
                }
                else
                {
                    flag = false;
                }
            }
            flag = false;
        }

        replace(board);
    }

    private static char resolve(int x, int y, char v, char[][] board)
    {
        int rows = board.length;
        int columns = board[0].length;

        if (x == 0 ||
                y == 0 ||
                (x + 1) == rows ||
                (y + 1) == columns)
        {
            return v;
        }

        if (board[x - 1][y] == 'F')
        {
            board[x - 1][y] = v;
            resolve(x - 1, y, v, board);
        }

        if (board[x][y - 1] == 'F')
        {
            board[x][y - 1] = v;
            resolve(x, y - 1, v, board);
        }

        if (board[x + 1][y] == 'F')
        {
            board[x + 1][y] = v;
            resolve(x + 1, y, v, board);
        }

        if (board[x][y + 1] == 'F')
        {
            board[x][y + 1] = v;
            resolve(x, y + 1, v, board);
        }

        return v;
    }

    private static void replace(char[][] board)
    {
        int rows = board.length;
        int columns = board[0].length;

        for (int row = 1; row < rows - 1; row++)
        {
            for (int column = 1; column < columns - 1; column++)
            {
                if (board[row][column] == 'F')
                {
                    if (board[row - 1][column] == 'O')
                    {
                        board[row][column] = 'O';
                    }
                    else if (board[row + 1][column] == 'O')
                    {
                        board[row][column] = 'O';
                    }
                    else if (board[row][column - 1] == 'O')
                    {
                        board[row][column] = 'O';
                    }
                    else if (board[row][column + 1] == 'O')
                    {
                        board[row][column] = 'O';
                    }
                    else
                    {
                        board[row][column] = 'X';
                    }
                }
            }
        }
    }
}
