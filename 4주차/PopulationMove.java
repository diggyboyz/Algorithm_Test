package algorithm4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.math.BigInteger;
public class PopulationMove {
	
	static boolean move = true;
	static int totalPopulation = 0;
	static int countCountry = 0;
	static int[][] population = null;
	static boolean[][] visited = null; 
	static ArrayList<int[]> saveUnion = new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int L = Integer.parseInt(st.nextToken()); 
		int R = Integer.parseInt(st.nextToken());
		
		population = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {				
				population[i][j] = Integer.parseInt(st.nextToken());				
				visited[i][j] = false;
			}			
		}
		int result = 0;
		
		while(move) {
			move = false;			
			visitCountry(N, L, R);
			if(move == false) {
				break;
			}
			result++;
			resetVisited(N);
		}
		
		bw.write(result + "\n");
		br.close();
		bw.flush();
		bw.close();

	}
	
	static void visitCountry(int N, int L, int R) {
		for(int i=0; i< N; i++) {		
			for(int j=0; j < N; j++) {
				 if(visited[i][j] == false) {
					 openBorder(N, L, R, i, j);
					 movePeople();					 
				 }
				 //초기화
				 countCountry =0;
				 totalPopulation = 0;
				 saveUnion = null;
				 saveUnion = new ArrayList<int[]>();
				 
			}
		}
	}
	
	static void openBorder(int N, int L, int R, int i, int j) {
		countCountry++;
		totalPopulation += population[i][j];
		visited[i][j] = true;
		//연합의 좌표를 저장하는 ArrayList인 saveUnion
		int [] coord = new int[2];
		coord[0] = i;
		coord[1] = j;
		saveUnion.add(coord);
		
		if(i>0) {
			if(visited[i-1][j] == false && checkOpenBorder(i, i-1, j, j ,L, R)) {				
				move = true;
				openBorder(N, L, R, i-1, j);				
			}			
		}
		if(i<N-1) {
			if(visited[i+1][j] == false && checkOpenBorder(i, i+1, j, j, L, R)) {
				move = true;
				openBorder(N, L, R, i+1, j);				
			}
		}
		if(j>0) {
			if(visited[i][j-1] == false && checkOpenBorder(i, i, j, j-1 ,L, R)) {
				move = true;
				openBorder(N, L, R, i, j-1);
			}
		}
		if(j<N-1) {
			if(visited[i][j+1] == false && checkOpenBorder(i, i, j, j+1 ,L, R)) {
				move = true;
				openBorder(N, L, R, i, j+1);
			}
		}		
	}
	
	static boolean checkOpenBorder(int i1, int i2, int j1, int j2, int L, int R) {
		int temp = Math.abs(population[i1][j1] - population[i2][j2]);
		if( temp >= L && temp <= R) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static void movePeople() {
		if(countCountry!= 1) { // 연합이 있다.
			 int eachUnionPopulation = totalPopulation / countCountry;
			 int [] temp = new int[2];
			 //연합을 순회하면서 넣어주기
			 for(int i=0; i<saveUnion.size(); i++) {
				 temp = saveUnion.get(i);
				 population[temp[0]][temp[1]] = eachUnionPopulation;
			 }
		 }
	}
	
	static void resetVisited(int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = false;
			}
		}
		
	}
}
