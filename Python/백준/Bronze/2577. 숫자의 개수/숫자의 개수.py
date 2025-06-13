import sys

A, B, C = map(int, sys.stdin.read().split())
cnts = [0] * 10

num = A * B * C
cur = num
while cur > 0:
    num = cur % 10
    cnts[num] += 1
    
    cur = cur // 10

for cnt in cnts:
    print(cnt)