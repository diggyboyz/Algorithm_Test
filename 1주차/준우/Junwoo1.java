package kr.ac.sejong.software.dbp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Junwoo1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int time = 0;
		int direct = 0; // 0 오른쪽 1 아래 2 왼쪽 3 위

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int appleCount = Integer.parseInt(br.readLine());

		int[][] board = new int[n + 2][n + 2];
		LinkedList<int[]> snake = new LinkedList<int[]>();
		// 뱀 초기값 (1,1)
		int[] coord = new int[2];
		coord[0] = 1;
		coord[1] = 1;
		snake.add(coord);

		// 보드채우기
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < n + 2; j++) {
				if (i == 0 || j == 0 || i == n + 1 || j == n + 1) {
					board[i][j] = 1;
				} else {
					board[i][j] = 0;
				}
			}
		}
		board[1][1] = 1;
		// 사과채우기
		for (int i = 0; i < appleCount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열

			board[r][c] = 2; // 사과는 2
		}
		int directCount = Integer.parseInt(br.readLine()) * 2;
		Object[] obj = new Object[directCount];
		for (int i = 0; i < directCount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			obj[i] = Integer.parseInt(st.nextToken());
			i++;
			obj[i] = st.nextToken();
		}
		/*
		 * 디버깅을 위한 출력구문 for (int i=0; i<directCount; i++) { System.out.print(obj[i] +
		 * " "); } System.out.println(); for (int i=0; i<n+2; i++) { for(int j=0; j<n+2;
		 * j++) { System.out.print(board[i][j]); } System.out.println(); }
		 */

		int directIndex = 0;
		while (true) {
			time += 1;

			// 뱀의머리좌표를 가져오고 방향쪽으로 +1 또는 -1 이동시킴 snake.addFirst(좌표) 그리고 board좌표가 1인지 확인(벽 or
			// 몸통과 충돌)
			// if충돌 끝
			// else if 사과인지 검사 사과라면 continue
			// else 뱀의 마지막 꼬리좌표삭제 = 연결리스트 마지막노드 삭제

			// 뱀의 머리좌표 가져옴
			int[] snakeCoord = snake.getFirst();
			int snake_x = snakeCoord[0];
			int snake_y = snakeCoord[1];
			// 방향에 따라 이동
			if (direct == 0) { // 오른쪽
				snake_x += 1;
			} else if (direct == 1) { // 아래
				snake_y += 1;
			} else if (direct == 2) { // 왼쪽
				snake_x -= 1;
			} else { // 위
				snake_y -= 1;
			}

			// 충돌이라면 끝
			if (board[snake_y][snake_x] == 1) {
				break;
			} // 사과라면 머리좌표만 추가함
			else if (board[snake_y][snake_x] == 2) {
				// 뱀의 머리좌표 추가
				// 뱀에 값추가하는법 (얕은복사 깊은복사에 대한이해)
				coord = coord.clone();
				coord[0] = snake_x;
				coord[1] = snake_y;
				board[snake_y][snake_x] = 1;
				snake.addFirst(coord);
			} // 일반 블럭이라면 뱀의 머리좌표 추가하고 ,뱀의 꼬리좌표삭제 = 연결리스트 마지막노드 삭제
			else {
				// 뱀의 머리좌표 추가
				// 뱀에 값추가하는법 (얕은복사 깊은복사에 대한이해)
				coord = coord.clone();
				coord[0] = snake_x;
				coord[1] = snake_y;
				board[snake_y][snake_x] = 1;
				snake.addFirst(coord);
				// 꼬리좌표삭제
				int[] lastValue = snake.removeLast();
				board[lastValue[1]][lastValue[0]] = 0;

			}
			/*
			 * 디버깅을 위한 출력구문 System.out.println();
			 * 
			 * for (int i=0; i<n+2; i++) { for(int j=0; j<n+2; j++) {
			 * System.out.print(board[i][j]); } System.out.println(); }
			 */
			// 방향조정해주기가 마지막에 가야함 초가 끝난뒤에 적용해야 해서
			while (directIndex < directCount) { // direct 배열이 끝나면 돌아가지 않음
				if (obj[directIndex].equals(time)) {
					System.out.println(time + " " + obj[directIndex + 1]);
					if (obj[directIndex + 1].equals("D")) {
						direct += 1;
						// direct %= 4;
						if (direct == 4) {
							direct = 0;
						}
					} else {
						direct -= 1;
						if (direct == -1) {
							direct = 3;
						}

					}
					directIndex += 2;

				}
				break;
			}
		}
		bw.write(time + "\n");
		br.close();
		bw.flush();
		bw.close();
	}

}
