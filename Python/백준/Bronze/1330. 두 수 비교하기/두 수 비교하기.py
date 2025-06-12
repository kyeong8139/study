A, B = map(int, input().split())

answer = ""
if A > B:
    answer = ">"
elif A < B:
    answer = "<"
else:
    answer = "=="

print(answer)