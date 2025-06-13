test_case_cnt = int(input())

for test_case in range(test_case_cnt):
    repeat, data = input().split()

    for c in data:
        for r in range(int(repeat)):
            print(c, end="")
    print()