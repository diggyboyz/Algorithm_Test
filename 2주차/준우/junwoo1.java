import java.util.*;

class Solution {
    public int solution(String name) {
        int currentIndex = 0;
        int alphabetCount = 'Z' - 'A' + 1;
        int answer = 0;
        int nameLength = name.length();
        int direct = 1;
        int firstCheck =0;
        int[] directInfo = new int[2];
        boolean reverseDirect = false;
        ArrayList<Boolean> checkList = new ArrayList<Boolean>(Arrays.asList(new Boolean[nameLength]));
        Collections.fill(checkList, false);
                
        for(int i=0; i<name.length(); i++){
            if(name.charAt(i) == 'A'){
                checkList.set(i,true);    
            }            
           // System.out.print(checkList.get(i) + " ");    
        }
        
        directInfo = directCheck(checkList);
        if(directInfo[0] <= directInfo[1]){
            reverseDirect = true;
        }
         /*            
            3가지의 이동경우가 있음
            1. 오른쪽으로 계속가는경우 BBBBBAAB
            2. 왼쪽으로 계속가는경우 AAAAAAAAAAAAB            
            3. 오른쪽으로 가다가 왼쪽으로가는경우 예시 문자열 ABABAAAAAABBB
            4. AAAABAAAAAAAB
            
            1.연속된 A의 가장큰 덩어리를 찾음
            2.덩어리의 길이와 덩어리시작의 index를 비교함.
            3.index보다 덩어리의 길이가 더 크거나 같으면 해당 index에서 방향전환이 발생함
            4.덩어리의 길이보다 index가 더 크면 방향전환없음
        */
        while(true){
            if(reverseDirect && (directInfo[0] == currentIndex)){
                if(currentIndex ==0){
                    direct = -1;
                }
                else{
                    answer--;
                    direct = -1;
                    currentIndex--;
                }                                                                                
            //currentIndex--;
            //direct = -1이면 index가 음수가 나올 수 있음                
            }
            if(currentIndex < 0){
                    currentIndex = checkList.size() - 1;
            }
            
            if (name.charAt(currentIndex) != 'A' && checkList.get(currentIndex)== false) {
                //조이스틱연산
                if ('Z'-name.charAt(currentIndex) >= 13){
                    //위로하는게 빠름                                        
                    answer += name.charAt(currentIndex) - 'A';                    
                }
                else{
                    //아래로하는게 빠름
                    answer += 'Z' - name.charAt(currentIndex);                                        
                    answer += 1;
                }                
                checkList.set(currentIndex,true);
                
                //종료조건 확인
                if(finishCheck(checkList)){
                    break;
                }
              
                //종료하지 않는다면
                currentIndex += direct;                                
                answer += 1;
                
            }                                 
            //checkList가 True인경우            
            else{             
                answer += 1;
                currentIndex += direct;                
            }   
          
        }                        
        return answer;
    }
   
    
    static int[] directCheck(ArrayList<Boolean> checkList){
        int continueCount = 0;
        int continueStartIndex = 0;
        int temp = 0;
        int[] directInfo = new int[2];
        for(int i=0; i<checkList.size(); i++){
            if(checkList.get(i)){
                temp++;
            }
            else{
                if(temp>continueCount){
                    continueCount = temp;
                    continueStartIndex = i - continueCount;
                }
                temp = 0;
            }
            
        }
        directInfo[0] = continueStartIndex;
        directInfo[1] = continueCount;
        return directInfo;
        
    
    }
    
    
    static boolean finishCheck(ArrayList<Boolean> checkList){
        int breakCount = 0;                
        for(int i=0; i<checkList.size(); i++){
            if (checkList.get(i)){
                breakCount ++;
            }
        }

        if(breakCount == checkList.size()){
            return true;
        }
        else{
            return false;
        }
        
    }
}
