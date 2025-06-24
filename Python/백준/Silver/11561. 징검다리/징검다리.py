import sys

read = sys.stdin.readline

test_case_cnt = int(read())
for test_case in range(test_case_cnt):
    n = int(read())

    left = 0
    right = n
    answer = 0
    while left <= right:
        mid = (left + right) // 2
        if (mid * (mid + 1)) // 2 <= n:
            answer = max(answer , mid)
            left = mid + 1
        else:
            right = mid - 1

    sys.stdout.write(f"{answer}\n")