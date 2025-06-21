import sys


n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.read().split()))
nums.sort()

for num in nums:
    sys.stdout.write(str(num) + "\n")