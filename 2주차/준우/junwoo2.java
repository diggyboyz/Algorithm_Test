import java.util.*;

class Solution {
    public int solution(int N, int number) {
        ArrayList<HashSet<Integer>> numList = new ArrayList<HashSet<Integer>>();
        int answer = 1;
        int tempN = 0;
        while(true){            
            //종료조건
            if(answer >9){                
                return -1;
            }
            //NN을 만드는 연산
            tempN = 0;
            for(int i=0; i<answer; i++){
                tempN = tempN * 10 + N;
            }
            
            HashSet<Integer> tempSet = new HashSet<Integer>();
            tempSet.add(tempN);
                                    
            for(int i=1; i<answer; i++){                                    
                        Iterator<Integer> iterA = numList.get(i-1).iterator();
                        Iterator<Integer> iterB = numList.get(answer-i-1).iterator();                        
                        while(iterA.hasNext()){                            
                            int tempA = iterA.next();                            
                            while(iterB.hasNext()){
                                int tempB = iterB.next();
                                tempSet.add(tempA+tempB);
                                tempSet.add(tempA-tempB);
                                tempSet.add(tempA*tempB);
                                if(tempB!=0){
                                    tempSet.add(tempA/tempB);    
                                }                            
                            }
                            iterB = numList.get(answer-i-1).iterator();
                        }
                        
                        
                }
                if(tempSet.contains(number)) return answer;            
                numList.add(tempSet);
                answer++;                                   
        }
                        
    }                
}
