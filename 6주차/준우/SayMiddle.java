import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * BOJ 1655번 문제 : 가운데를 말해요
 * Boj(백준이)가 정수를 하나씩 외칠때마다 Brother(동생)이 지금까지 말한 수 중에서 중간값을 말해야 한다.
 * 만약 Boj가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중 작은 수를 말한다.
 */

class Boj {

	int[] sayNumbers() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strNum = br.readLine();
		int num = Integer.parseInt(strNum);
		int[] numArray = new int[num];
		Brother bro = new Brother();
		
		for (int i = 0; i < numArray.length; i++) {
			strNum = br.readLine();
			num = Integer.parseInt(strNum);								
			bro.inputToPQ(num);
			numArray[i] = bro.sayNumber();

		}
		br.close();
		return numArray;
	}
}

class Brother {
	private PriorityQueue<Integer> minPQ = new PriorityQueue<>();
	private PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

	int sayNumber() {
		return maxPQ.peek();
	}

	void inputToPQ(int num) {
		if (isSizeSame(minPQ, maxPQ)) {
			maxPQ.offer(num);
		} else {
			minPQ.offer(num);
		}

		if (isBothNotEmpty(minPQ, maxPQ)) {
			if (isNeedSwap(minPQ, maxPQ)) {
				swap(minPQ, maxPQ);
			}
		}
	}

	boolean isSizeSame(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
		if (minPQ.size() == maxPQ.size()) {
			return true;
		} else {
			return false;
		}
	}

	boolean isBothNotEmpty(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
		if (!minPQ.isEmpty() && !maxPQ.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	boolean isNeedSwap(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
		if (minPQ.peek() < maxPQ.peek()) {
			return true;
		} else {
			return false;
		}
	}

	void swap(PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ) {
		int temp = minPQ.poll();
		minPQ.offer(maxPQ.poll());
		maxPQ.offer(temp);
	}

}

public class SayMiddle {

	static int[] numArray;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		inputNumberInfo();
		printNumberInfo();
	}

	static void inputNumberInfo() throws IOException {
		Boj boj = new Boj();
		numArray = boj.sayNumbers();
	}

	static void inputNumberInfoTest() {
		for (int num : numArray) {
			System.out.print(num + " ");
		}
		System.out.println("");
	}

	static void printNumberInfo() throws IOException {

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < numArray.length; i++) {
			stringBuilder.append(numArray[i]);
			stringBuilder.append("\n");
		}

		bw.write(stringBuilder.toString());
		bw.flush();
		bw.close();

	}

}
