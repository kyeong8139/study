length = int(input())
nums = map(int, input().split())

min_val = 1000000
max_val = -1000000
for num in nums:
    min_val = min(min_val, num)
    max_val = max(max_val, num)

print(f"{min_val} {max_val}")