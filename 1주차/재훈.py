directions = ['R', 'D', 'L', 'U']
snake = []
commands = []

n = int(input())
board = [[0 for col in range(n)] for row in range(n)]

k = int(input())
for idx in range(k):
    i, j = input().split()
    board[int(i) - 1][int(j) - 1] = 1

L = int(input())
for i in range(L):
    X, C = input().split()
    dict = {"count": int(X), "direction": C}
    commands.append(dict)

i, j, d = 0, -1, 0
count, lifeCount = 0, 0
dir = ''

while True:

    if lifeCount == count + 1:
        if dir == 'D':
            d += 1
        elif dir == 'L':
            d -= 1

        if commands:
            command = commands.pop(0)
            count = command["count"]
            dir = command["direction"]

    if directions[d % 4] == 'R':
        j += 1
    elif directions[d % 4] == 'D':
        i += 1
    elif directions[d % 4] == 'L':
        j -= 1
    elif directions[d % 4] == 'U':
        i -= 1

    if i >= n or j >= n or i < 0 or j < 0:
        break

    if [i, j] in snake:
        break
    else:
        snake.append([i, j])

    if board[i][j] != 1 and len(snake) > 1:
        snake.pop(0)

    lifeCount += 1

print(lifeCount)
