# n, save = input().split()
# n = int(n)
# save = int(save)

import sys
from itertools import combinations

n, save = map(int, sys.stdin.readline().split())

houseList = []
chickenList = []

arr = [0 for _ in range(n)]

for i in range(n):
    arr[i] = list(map(int, input().split()))
    for j in range(n):
        if arr[i][j] == 1:
            houseList.append([i, j]) # 집
        elif arr[i][j] == 2:
            chickenList.append([i, j]) # 치킨

# 남을 치킨 집 수
options = list(combinations(chickenList, save))
selected = []



# 완전 탐색 (브루트 포스)
for option in options:
    distance = 0
    for hx, hy in houseList:
        distanceList = []
        for cx, cy in option:
            temp = abs(hx - cx) + abs(hy - cy) #거리
            distanceList.append(temp) # [...]
            
        distance += min(distanceList) 
    selected.append(distance)

print(min(selected))