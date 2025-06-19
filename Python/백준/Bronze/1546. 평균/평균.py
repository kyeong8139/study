n = int(input())
scores = list(map(int, input().split()))

sum = 0
max_value = 0
for score in scores:
    max_value = max(score, max_value)
    sum += score

answer = ((sum * 100) / max_value) / n
print(answer)