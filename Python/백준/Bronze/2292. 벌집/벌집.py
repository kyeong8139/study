n = int(input())

cur = 1
depth = 1
while (cur < n):
    cur += 6 * depth
    depth += 1

print(depth)