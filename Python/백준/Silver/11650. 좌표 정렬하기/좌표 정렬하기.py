import sys

n = int(sys.stdin.readline())
data = []

for _ in range(n):
    x, y = map(int, sys.stdin.readline().split())
    data.append([x, y])

data.sort(key = lambda item: (item[0], item[1]))

for x, y in data:
    sys.stdout.write(f"{x} {y}\n")