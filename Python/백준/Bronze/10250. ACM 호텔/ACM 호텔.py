testCaseCnt = int(input())

for t in range(testCaseCnt):
    height, width, people = map(int, input().split())

    curHeight = 0
    curWidth = 1
    for i in range(people):
        curHeight += 1
        if curHeight > height:
            curHeight = 1
            curWidth += 1

    print(f"{curHeight}{curWidth:02}")