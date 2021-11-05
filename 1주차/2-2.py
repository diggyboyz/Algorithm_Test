n, save = input().split()
n = int(n)
save = int(save)

houseList = []
chickenList = []

arr = [0 for _ in range(n)]

for i in range(n):
    arr[i] = list(map(int, input().split()))

    for j in range(n):
        if arr[i][j] == 1:
            houseList.append([i, j])
        elif arr[i][j] == 2:
            chickenList.append([i, j])

selected = []
for chicken in chickenList:
    distanceList = []
    for house in houseList:
        distance = abs(chicken[0] - house[0]) + abs(chicken[1] - house[1])
        distanceList.append(distance)

    selected.append([sum(distanceList), distanceList])

selected = sorted(selected)[0:save]

compare = selected[0][1]

for S in selected:
    for idx, (val, com) in enumerate(zip(S[1], compare)):
        if val < com:
            compare[idx] = val

print(sum(compare))