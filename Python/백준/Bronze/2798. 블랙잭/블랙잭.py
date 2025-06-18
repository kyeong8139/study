N, M = map(int, input().split())
cards = list(map(int, input().split()))

answer = 0
step = 300000
for i in range(0, len(cards)):
    for j in range(i+1, len(cards)):
        for k in range(j+1, len(cards)):
            sum = cards[i] + cards[j] + cards[k]
            if sum <= M and M - sum < step:
                step = M - sum
                answer = sum

print(answer)
            