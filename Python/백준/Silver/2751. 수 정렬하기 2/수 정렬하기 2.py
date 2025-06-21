import sys


n = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.read().split()))
nums.sort()
nums = list(map(str, nums))

sys.stdout.write("\n".join(nums))