import sys

n = int(sys.stdin.readline())
length = 10001
cnts = [0] * length

for i in range(n):
    num = int(sys.stdin.readline())
    cnts[num] += 1

for i in range(1, length):
    for _ in range(cnts[i]):
        print(i)