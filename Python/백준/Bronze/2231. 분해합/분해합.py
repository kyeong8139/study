N = int(input())

answer = 0
for num in range(1, N+1):
    result = num
    
    cur = num
    while cur > 0:
        result += cur % 10
        cur = cur // 10

    if result == N:
        answer = num
        break

print(answer)