data = list(map(int, input().split()))

result = ""

if data[0] == 1:
    result = "ascending"
    expect_val = 1
    for d in data:
        if d != expect_val:
            result = "mixed"
            break
        expect_val += 1
elif data[0] == 8:
    result = "descending"
    expect_val = 8
    for d in data:
        if d != expect_val:
            result = "mixed"
            break
        expect_val -= 1
else:
    result = "mixed"

print(result)