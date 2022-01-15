package algorithm5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BabyShark {
	static int time = 0;
	static int tempCount = 1;
	static int n = 0;
	static int[][] space = null;
	static boolean[][] visited = null;
	static int sharkSize = 2;
	static int eatCount = 0;
	static int[] currentSharkLocation = new int[2];
	static int[] tempSharkLocation = new int[2];
	static LinkedHashSet<int[]> sharkLocationSet = new LinkedHashSet<int[]>();
	static ArrayList<int[]> sharkLocationList= new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		space = new int[n][n];
		visited = new boolean[n][n];
		
		//4가지 초기화 (space, visited, currentSharkLocation, tempSharkLocation)
		initialSetting(br, st);
		/*
		initialSetting() test 성공
		주의사항은 currentSharkLocation에는 j,i로 할당되어있기 때문에 space, visited 배열에 접근할 때 i,j로 접근하면 안되고 j,i로 접근해야 한다.
		
		showCurrentSpace();
		showCurrentVisited();
		System.out.println(currentSharkLocation[0] + " " + currentSharkLocation[1]);
		System.out.println(tempSharkLocation[0] + " " + tempSharkLocation[1]);
		*/				
		saveSharkLocationSet();
		/*
		saveSharkLocationSet() test 성공
		showCurrentSharkLocationSet();
		showCurrentVisited();		
		*/
		
				
		//만약 sharkLocationSet이 비어있다면 상어가 움직이지 못했으므로 끝
		while (!sharkLocationSet.isEmpty()) {
			//showCurrentSharkLocationSet();			
			if (checkEat()) {
				//가장위,가장왼쪽찾는 함수
				calculateCoord();				
				eatCount++;
				resetVisitedArray();				
				calculateTime();
				changeSharkLocation();
				sharkLocationSet.clear();
				saveSharkLocationSet();
				//showCurrentSpace();											
			} else {
				if (visitedAllTrue()) {
					//System.out.println("visitedAllTrue");
					break;
				}
				updateSharkLocationSet();
				tempCount++;
			}											
		}		 
		bw.write(time + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	static void initialSetting(BufferedReader br, StringTokenizer st) throws IOException {
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
				if (space[i][j] == 9) {
					currentSharkLocation[0] = i;
					currentSharkLocation[1] = j;
					visited[i][j] = true;
				}
			}
		}
		tempSharkLocation[0] = currentSharkLocation[0];
		tempSharkLocation[1] = currentSharkLocation[1];
	}

	static void saveSharkLocationSet() {
		// 상어가 갈 수 있는 좌표를 sharkLocationSet에 저장한다.
		// 위, 왼쪽, 오른쪽, 아래 순서로 설계한 이유는 명세서 때문입니다.
		// currentSharkLocation에는 j,i로 할당되어있기 때문에 space, visited 배열에 접근할 때 i,j로 접근하면 안되고 j,i로 접근해야 한다.
		int i, j;
		
		i = tempSharkLocation[1];
		j = tempSharkLocation[0];
		//i,j = 1,2
				
		// 위(Up)
		if (j > 0) {
			if (checkSharkMove(i, j - 1)) {
				tempSharkMove(i, j -1);
			}
		}
		// 왼쪽(Left)
		if (i > 0) {
			if (checkSharkMove(i-1, j )) {
				tempSharkMove(i-1, j );
			}			
		}
		// 오른쪽(Right)
		if (i < n - 1) {
			if (checkSharkMove(i + 1, j )) {
				tempSharkMove(i + 1, j );
			}
		}
		// 아래(Down)		
		if (j < n - 1) {
			if (checkSharkMove(i, j +1)) {
				tempSharkMove(i, j + 1);
			}
		}
	}
	
	static boolean checkSharkMove(int i,int j) {
		if (visited[j][i] == false && space[j][i] <= sharkSize) {
			return true;
		} else {
			return false;
		}
	}

	static void tempSharkMove(int i, int j) {
		//saveSharkLocationSet()에서 이미 i,j의 위치를 변경해서 넘겨줬기 때문에 다시 i와j의 위치를 변경해서 저장
		int[] tempCoord = new int[2];
		tempCoord[0] = j;
		tempCoord[1] = i;		
		sharkLocationSet.add(tempCoord);
		visited[j][i] = true;
		
	}

	static boolean checkEat() {
		int tempI;
		int tempJ;		
		boolean eatFlag = false;
		Iterator<int[]> iter = sharkLocationSet.iterator();		
		int[] tempLocationArray = new int[2];
		while (iter.hasNext()) {
			int[] tempCoord = new int[2];
			tempLocationArray = iter.next();			
			tempI = tempLocationArray[0];
			tempJ = tempLocationArray[1];
			//System.out.println(tempI + " " + tempJ + " " + space[tempI][tempJ]);
			if (space[tempI][tempJ] < sharkSize && space[tempI][tempJ] != 0) {
				tempSharkLocation[0] = tempI;
				tempSharkLocation[1] = tempJ;
				eatFlag = true;
				tempCoord[0] = tempI;
				tempCoord[1] = tempJ;
				//System.out.println(":" + tempCoord[0] + " " + tempCoord[1]);
				sharkLocationList.add(tempCoord);
				
				
			}

		}
		if(eatFlag) {
			return true;
		}
		else {
			return false;
		}

	}
	static void calculateCoord() {
		
		int[] tempCoord = new int[2];
		tempCoord = sharkLocationList.get(0);
		int minI = tempCoord[0];
		int minJ = tempCoord[1];
		for(int i=0; i<sharkLocationList.size(); i++) {
			tempCoord = sharkLocationList.get(i);
			//System.out.println("test: " + tempCoord[0] + " " + tempCoord[1]);
			if(minI > tempCoord[0]) {
				minI = tempCoord[0];
				minJ = tempCoord[1];
			}
			else if(minI == tempCoord[0]) {
				if(minJ > tempCoord[1]) {
					minI = tempCoord[0];
					minJ = tempCoord[1];
				}
			}
			else {
				continue;
			}			
			
		}		
		tempSharkLocation[0] = minI;
		tempSharkLocation[1] = minJ;
		sharkLocationList.clear();
		
	}
	static void resetVisitedArray() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = false;
			}
		}
		visited[tempSharkLocation[0]][tempSharkLocation[1]] = true;

	}

	static void changeSharkLocation() {
		
		
		int currentI = currentSharkLocation[0];
		int currentJ = currentSharkLocation[1];
		int tempI = tempSharkLocation[0];
		int tempJ = tempSharkLocation[1];
		//System.out.println(currentSharkLocation[0] + " " + currentSharkLocation[1]);
		//System.out.println(tempSharkLocation[0]+ " " + tempSharkLocation[1]);
								
		if(sharkSize == eatCount) {
			sharkSize++;
			eatCount = 0;
		}
		space[currentI][currentJ] = 0;				
		space[tempI][tempJ] = 9;		
				
		currentSharkLocation[0] = tempSharkLocation[0];
		currentSharkLocation[1] = tempSharkLocation[1];
		

	}

	static void calculateTime() {		
		time = time + tempCount;
		tempCount = 1;
		//System.out.println("시간:"+ time);
	}

	static void updateSharkLocationSet() {
		Iterator<int[]> iter = sharkLocationSet.iterator();
		ArrayList<int[]> tempArrayList = new ArrayList<int[]>(sharkLocationSet);
		int[] tempCoord = new int[2];

		for (int i = 0; i < tempArrayList.size(); i++) {
			tempCoord = tempArrayList.get(i);			
			tempSharkLocation[0] = tempCoord[0];
			tempSharkLocation[1] = tempCoord[1];
			saveSharkLocationSet();
			sharkLocationSet.remove(tempCoord);
		}
		
	}

	static boolean visitedAllTrue() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void showCurrentSpace() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(space[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void showCurrentVisited() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void showCurrentSharkLocationSet() {
		Iterator<int[]> iter = sharkLocationSet.iterator();
		int[] tempArrayList;
		while(iter.hasNext()) {
			tempArrayList = iter.next();
			System.out.println(tempArrayList[0] + " " + tempArrayList[1]);
			
		}
	}
}
