import sys

n = int(sys.stdin.readline())
idx = 0
cur = 666

while idx < n:
    if str(cur).__contains__("666"):
        idx += 1
    cur += 1

print(cur - 1)