class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        
        target(numbers,target,0,0);        
        return answer;
    }
    
    static void target(int[] numbers,int target, int index,int sum){
        
        //종료조건
        if(index == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }                        
        sum+=numbers[index];
        target(numbers,target,index+1,sum);
        sum-=2*numbers[index];         
        target(numbers,target,index+1,sum);
        
        
        
    }
    
}
