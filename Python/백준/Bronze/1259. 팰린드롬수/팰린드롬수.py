while True:
    data = input()
    if int(data) == 0: break

    answer = "yes"
    for i in range(len(data)//2):
        left = i
        right = len(data) - i - 1
        if data[left] != data[right]:
            answer = "no"
            break

    print(answer)