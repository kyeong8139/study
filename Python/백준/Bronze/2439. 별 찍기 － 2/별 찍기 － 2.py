N = int(input())

for line in range(1, N+1):
    space = N - line
    star = line
    for i in range(space):
        print(" ", end="")
    for i in range(star):
        print("*", end="")
    print()