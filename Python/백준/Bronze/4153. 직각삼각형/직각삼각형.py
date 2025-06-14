while True:
    sides = list(map(int, input().split()))
    if sides[0] == 0 and sides[1] == 0 and sides[2] == 0: break

    max = 0
    mid = 0
    min = 0
    for side in sides:
        if side > max:
            min = mid
            mid = max
            max = side
        elif side > mid:
            min = mid
            mid = side
        elif side > min:
            min = side

    result = "wrong"
    if max * max == (mid * mid + min * min):
        result = "right"

    print(result)