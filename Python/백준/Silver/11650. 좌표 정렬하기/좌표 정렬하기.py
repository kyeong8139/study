import sys

n = int(sys.stdin.readline())
data = []

for _ in range(n):
    x, y = map(int, sys.stdin.readline().split())
    data.append((x, y))

data.sort(key = lambda item: (item[0], item[1]))

sys.stdout.write("\n".join(f'{x} {y}' for x, y in data))