N = int(input())

for line in range(1, N+1):
    for star in range(line):
        print("*", end="")
    if line != N:
        print()