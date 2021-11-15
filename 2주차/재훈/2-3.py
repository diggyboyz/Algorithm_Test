from itertools import combinations, product

def solution(numbers, target):
    answer = 0

    # (-x, x) 를 길이만큼 담은 리스트 초기화
    options = [(x, -x) for x in numbers]
    # product 로 모든 경우의 조합을 구함
    products = list(product(*options))

    # product 의 합 중에서 target 과 동일한 값을 return
    for P in products:
        if sum(P) == target:
            answer += 1

    return answer