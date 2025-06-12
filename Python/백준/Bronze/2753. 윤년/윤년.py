year = int(input())

isLunar = False
if year % 400 == 0 or (year % 4 == 0 and year % 100 != 0):
    isLunar = True

if isLunar:
    print(1)
else:
    print(0)