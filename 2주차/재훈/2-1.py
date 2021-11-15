def solution(name):
    answer = 0
    next = 0
    move = len(name) - 1 # 오른쪽으로만 간다는 가정 하에 초기화
    
    for idx,val in enumerate(name):

        # A,Z 중 위아래 어느방향으로 가는게 더 가까운지
        answer += min(ord('Z') - ord(val) + 1, ord(val) - ord('A'))
        next = idx + 1
        
        # A 를 발견할 시 연속된 A 의 개수를 구함
        while next < len(name) and name[next] == 'A':
            next += 1
        
        # 좌우 어느 방향으로 가는게 더 빠른지
        move = min(move, idx*2 + len(name)-next)
    
    answer += move
    
    return answer