import sys

nums = list(map(int, sys.stdin.read().split()))

max = 0
idx = 0
for i in range(9):
    if nums[i] > max:
        max = nums[i]
        idx = i+1

print(max)
print(idx)