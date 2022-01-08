package algorithm4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class StartLink {
	static int buttonPressedCount = 0;
	static boolean useElevator = true;
	static HashSet<Integer> visited = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int totalFloor = Integer.parseInt(st.nextToken());
		int currentLocation = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());

		visited.add(currentLocation);

		move(totalFloor, currentLocation, destination, up, down);

		if (useElevator) {
			bw.write(buttonPressedCount + "\n");
		} else {
			bw.write("use the stairs\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}

	static void move(int totalFloor, int currentLocation, int destination, int up, int down) {
		while (true) {
			// 출발지가 목적지인 경우도 고려해야 한다 따라서 맨 앞에 와야 함.
			if (currentLocation == destination) {
				break;
			}

			if (currentLocation + up > totalFloor && currentLocation - down < 1) {
				useElevator = false;
				break;
			}

			if (currentLocation < destination) {
				// have to go up
				if (currentLocation + up > totalFloor) {
					// 위로올라가야하는데 전체층수보다 큰 경우
					currentLocation -= down;
				} else {
					currentLocation += up;
				}

			} else {
				// have to go down
				if (currentLocation - down < 1) {
					// 아래로 내려가야하는데 1층보다 낮은 경우
					currentLocation += up;
				} else {
					currentLocation -= down;
				}

			}

			buttonPressedCount++;

			if (visited.contains(currentLocation)) {
				useElevator = false;
				break;
			} else {
				visited.add(currentLocation);
			}

		}
	}

}
