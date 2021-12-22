from itertools import combinations, product

def solution(numbers, target):
    answer = 0

    options = [(x, -x) for x in numbers]
    products = list(product(*options))

    for P in products:
        if sum(P) == target:
            answer += 1

    return answer


if __name__ == "__main__":
    result = solution([1, 1, 1, 1, 1], 3)
    print(result)

    print(solution([1, 2, 1, 2], 2), 3)
    print(solution([1, 2, 1, 2], 6), 1)
