package algorithm.backjoon.exhaustiveserarch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ChessRepaint {


    //https://www.acmicpc.net/problem/1018
    @Test
    void test() throws Exception {
        char[][] board = getBoard("src/test/resources/chess/example2.txt");

        int N = board.length;
        int M = board[0].length;
        int minRepaint = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                minRepaint = Math.min(minRepaint, countRepaint(board, i, j));
            }
        }

        Assertions.assertEquals(12, minRepaint);

    }

    private char[][] getBoard(String filename) throws Exception {
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String[] input = reader.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            char[][] board = new char[N][M];

            for (int i = 0; i < N; i++) {
                String row = reader.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = row.charAt(j);
                }
            }
            return board;
        }
    }

    private int countRepaint(char[][] board, int x, int y) {
        int repaint = 0;
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                // 첫 번째 칸이 흰색인 경우
                char current = board[i][j];
                if (mustBeWhite(i, j)) {
                    if (current != 'W') {
                        repaint++;
                    }
                } else {
                    if (current != 'B') {
                        repaint++;
                    }
                }
            }
        }
        // 시작점이 흰색인 경우와 검은색인 경우 중에서 더 작은 값을 리턴
        return Math.min(repaint, 64 - repaint);
    }

    private boolean mustBeWhite(int x, int y) {
        return (x + y) % 2 == 0;
    }

}
