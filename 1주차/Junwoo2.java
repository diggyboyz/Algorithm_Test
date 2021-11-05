package kr.ac.sejong.software.dbp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Junwoo2 {
	static LinkedList<Character> gear1 = new LinkedList<Character>();
	static LinkedList<Character> gear2 = new LinkedList<Character>();
	static LinkedList<Character> gear3 = new LinkedList<Character>();
	static LinkedList<Character> gear4 = new LinkedList<Character>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int sum = 0;

		String tempGear1 = br.readLine();
		String tempGear2 = br.readLine();
		String tempGear3 = br.readLine();
		String tempGear4 = br.readLine();

		for (int i = 0; i < tempGear1.length(); i++) {
			gear1.add(tempGear1.charAt(i));
			gear2.add(tempGear2.charAt(i));
			gear3.add(tempGear3.charAt(i));
			gear4.add(tempGear4.charAt(i));

		}

		int k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNumber = Integer.parseInt(st.nextToken()); // 톱니번호
			int direct = Integer.parseInt(st.nextToken()); // 회전방향 1은 시계방향 -1은 반시계방향
			/*
			 * 톱니 디버깅 System.out.println(); System.out.println(gear1);
			 * System.out.println(gear2); System.out.println(gear3);
			 * System.out.println(gear4);
			 */
			gearLotation(gearNumber, direct, gear1, gear2, gear3, gear4);

		}
		/*
		 * 톱니 디버깅 System.out.println(); System.out.println(gear1);
		 * System.out.println(gear2); System.out.println(gear3);
		 * System.out.println(gear4);
		 */
		sum = calculateResult(gear1, gear2, gear3, gear4);
		bw.write(sum + "\n");
		br.close();
		bw.flush();
		bw.close();

	}

	public static void gearLotation(int gearNumber, int direct, LinkedList<Character> gear1,
			LinkedList<Character> gear2, LinkedList<Character> gear3, LinkedList<Character> gear4) {
		// 모든 톱니의 회전여부 검사
		// System.out.println(gearNumber + " " + direct + "\n");
		boolean check12 = true;
		boolean check23 = true;
		boolean check34 = true;

		if (gear1.get(2).equals(gear2.get(6))) {
			check12 = false;
		}

		if (gear2.get(2).equals(gear3.get(6))) {
			check23 = false;
		}
		if (gear3.get(2).equals(gear4.get(6))) {
			check34 = false;
		}
		// System.out.println(check12 + " " + check23 + " " + check34 + " ");

		if (gearNumber == 1) {
			// 톱니를 회전하면서 회전여부가 True이면 옆의 톱니도 반대방향으로회전시킴
			lotate(gear1, direct);
			if (check12) {
				lotate(gear2, direct * -1);
				if (check23) {
					lotate(gear3, direct);
					if (check34) {
						lotate(gear4, direct * -1);
					}
				}
			}
		} else if (gearNumber == 2) {
			lotate(gear2, direct);
			if (check12) {
				lotate(gear1, direct * -1);
			}
			if (check23) {
				lotate(gear3, direct * -1);
				if (check34) {
					lotate(gear4, direct);
				}
			}
		} else if (gearNumber == 3) {
			lotate(gear3, direct);
			if (check34) {
				lotate(gear4, direct * -1);
			}
			if (check23) {
				lotate(gear2, direct * -1);
				if (check12) {
					lotate(gear1, direct);
				}
			}
		} else {
			lotate(gear4, direct);
			if (check34) {
				lotate(gear3, direct * -1);
				if (check23) {
					lotate(gear2, direct);
					if (check12) {
						lotate(gear1, direct * -1);
					}
				}
			}
		}

	}

	public static int calculateResult(LinkedList<Character> gear1, LinkedList<Character> gear2,
			LinkedList<Character> gear3, LinkedList<Character> gear4) {
		// 톱니값을 계산하는 함수
		int value1 = Character.getNumericValue(gear1.getFirst());
		int value2 = Character.getNumericValue(gear2.getFirst()) * 2;
		int value3 = Character.getNumericValue(gear3.getFirst()) * 4;
		int value4 = Character.getNumericValue(gear4.getFirst()) * 8;
		int sum = value1 + value2 + value3 + value4;
		return sum;

	}

	public static void lotate(LinkedList<Character> gear, int direct) {
		// 톱니를 회전시키는 함수
		char temp;

		if (direct == 1) { // 시계방향 마지막노드를 처음에 저장하고 마지막노드를 삭제시킴
			temp = gear.getLast();
			gear.addFirst(temp);
			gear.removeLast();

		} else { // 반시계방향 맨앞노드를 마지막에 저장하고 맨앞노드를 삭제시킴
			temp = gear.getFirst();
			gear.addLast(temp);
			gear.removeFirst();

		}

	}

}
