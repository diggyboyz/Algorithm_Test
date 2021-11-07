package minseok_boj_15686;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer[]> chickenArray = new ArrayList<Integer[]>();
		ArrayList<Integer[]> houseArray = new ArrayList<Integer[]>();
		
		for(int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j = 0; j < n; j++) {
				int num = Integer.parseInt(input[j]);
				
				if(num == 1) {
					houseArray.add(new Integer[]{i + 1, j + 1});
				} else if(num == 2) {
					chickenArray.add(new Integer[]{i + 1, j + 1});
				}
			}
		}
		int chickenSize = chickenArray.size();
		int arr[] = new int [chickenSize];
		
		for(int i = 0; i < chickenSize; i++) {
			arr[i] =i;
		}
		
		boolean[] visited = new boolean[chickenSize];
		Arrays.fill(visited, false);
		
		combination(arr, visited, 0, chickenSize, m, houseArray, chickenArray);
		System.out.println(result);
	}
	
	public static int distance (int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	static int result = 99999;
	
	public static void combination(int[] arr, boolean[] visited, int start, int n, int r, ArrayList<Integer[]> house, ArrayList<Integer[]> chicken) {
	    if(r == 0) {
	    	int sum = 0;
	        for(int i = 0; i < house.size(); i++) {
	        	int x1 = house.get(i)[0];
	        	int y1 = house.get(i)[1];
	        	int dis = 0;
	        	int min = 9999999;
	        	
	        	for(int j = 0; j < chicken.size(); j++) {
	        		if(visited[j] != true) continue;
	        		int x2 = chicken.get(j)[0];
	        		int y2 = chicken.get(j)[1];
	        	
	        		dis = distance(x1, y1, x2, y2);
	        		if(min > dis) min = dis;
	        	}
	        	sum += min;
	        }
	        if(result > sum) result = sum;
	        return;
	    } 

	    for(int i = start; i<n; i++) {
	        visited[i] = true;
	        combination(arr, visited, i + 1, n, r - 1, house, chicken);
	        visited[i] = false;
	    }
	}
	
}
/*		
*/