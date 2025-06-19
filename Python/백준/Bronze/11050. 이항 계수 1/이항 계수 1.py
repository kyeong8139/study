n, k = map(int, input().split())
if k > n-k:
    k = n-k

answer = 1
for i in range(k):
    answer *= (n - i)
    
for i in range(k):
    answer //= (i + 1)

print(answer)