apartment = [[0] * 15 for _ in range(15)]
for a in range(0, 15):
    for b in range(1, 15):
        if a == 0:
            apartment[a][b] = b
        else:
            apartment[a][b] = apartment[a][b-1] + apartment[a-1][b]

test_case_cnt = int(input())

for test_case in range(test_case_cnt):
    k = int(input())
    n = int(input())

    print(apartment[k][n])