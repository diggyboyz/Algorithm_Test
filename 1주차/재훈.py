directions = ['R', 'D', 'L', 'U']
snake = []

n = int(input())

board = [[0 for col in range(n)] for row in range(n)]

k = int(input())

for idx in range(k):
  i,j = input().split()
  board[int(i) - 1][int(j) - 1] = 1

L = int(input())

commands = []
for i in range(L): 
  X,C = input().split()
  dict = {"count":int(X), "direction":C}
  commands.append(dict)

i = 0
j = -1
d = 0
count = 0
lifeCount = 0
dir = ''

while True:
  if lifeCount == 0 or lifeCount == count + 1:
    if dir == 'D':
      d += 1
    elif dir == 'L':
      d -= 1
    
    if commands:
      command = commands.pop(0)
      count = command["count"]
      dir = command["direction"]
  
  if d == 4: 
    d = 0
  elif d == -1:
    d = 3
  
  if directions[d] == 'R':
    j += 1
  elif directions[d] == 'D':
    i += 1
  elif directions[d] == 'L':
    j -= 1
  elif directions[d] == 'U':
    i -= 1
  
  if i >= n or j >= n or i < 0 or j < 0 :
    break

  if [i,j] in snake :
    break
  else:
    snake.append([i,j])
  
  if board[i][j] != 1 and len(snake) > 1:
    snake.pop(0)
  
  lifeCount += 1

print(lifeCount)