import java.util.*;

public class StartLink {
    public static int F, S, G, U, D;
    public static int[] dist;
    public static boolean[] visited;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();

        visited = new boolean[F+1];

        if(S == G){
            System.out.println(0);
            return;
        }
        if (U == 0 && D == 0) {
            System.out.println("use the stairs");
            return;
        }
        bfs(S);
    }

    public static void bfs(int start) {
        ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[F+1];
        int dist = 0;
        graph[dist] = new ArrayList<>();
        graph[dist].add(start);
        int search = start;
        visited[search] = true;
        while(!graph[dist].isEmpty()) {
            dist++;
            graph[dist] = new ArrayList<>();
            for(int i = 0 ; i < graph[dist-1].size();i++) {
                if(D != 0){
                    search = graph[dist-1].get(i) - D;
                    if(search > 0 && !visited[search]) {
                        graph[dist].add(search);
                        visited[search] = true;
                    }
                }
                if(U != 0){
                    search = graph[dist-1].get(i) + U;
                    if(search <= F && !visited[search]) {
                        graph[dist].add(search);
                        visited[search] = true;
                    }
                }
            }
            if(graph[dist].contains(G)) {
                System.out.println(dist);
                return;
            }
            if(graph[dist].indexOf(graph[dist].size()-1) > G){
                break;
            }
        }
        System.out.println("use the stairs");
    }
}