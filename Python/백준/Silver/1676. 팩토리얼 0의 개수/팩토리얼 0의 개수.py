import sys

n = int(sys.stdin.readline())

answer = 0
cnt_two = 0
for num in range(1, n+1):
    cur = num
    while cur % 2 == 0:
        cnt_two += 1
        cur //= 2

cnt_five = 0
for num in range(1, n+1):
    cur = num
    while cur % 5 == 0:
        cnt_five += 1
        cur //= 5

answer = min(cnt_two, cnt_five)
sys.stdout.write(str(answer))