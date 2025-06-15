N = int(input())
prime_nums = [True] * 1001
prime_nums[1] = False

for num in range(2, 501):
    if prime_nums[num] == False: continue

    cur = num * 2
    while cur <= 1000:
        prime_nums[cur] = False
        cur += num

data = list(map(int, input().split()))
answer = 0
for d in data:
    if prime_nums[d]: 
        answer += 1

print(answer)