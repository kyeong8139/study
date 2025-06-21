import sys

n = int(sys.stdin.readline())
cnt = 0
num = 666

while True:
    if "666" in str(num):
        cnt += 1

    if cnt == n:
        sys.stdout.write(str(num))
        break

    num += 1