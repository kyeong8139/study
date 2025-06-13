import sys

nums = list(map(int, sys.stdin.read().split()))
isVisited = [False] * 42
result = 0

for num in nums:
    remain = num % 42
    if not isVisited[remain]:
        isVisited[remain] = True
        result += 1

print(result)