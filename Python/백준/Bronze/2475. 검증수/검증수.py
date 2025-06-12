nums = list(map(int, input().split()))

answer = 0
for num in nums:
    answer += num * num

print(answer % 10)