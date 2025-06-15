N = int(input())
sizes = list(map(int, input().split()))
T, P = map(int, input().split())

shirt_cnt = 0
for size in sizes:
    shirt_cnt += size // T + (1 if size % T != 0 else 0)

print(shirt_cnt)
print(f"{N // P} {N % P}")