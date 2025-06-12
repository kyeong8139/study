grades = {10 : "A", 9 : "A", 8 : "B", 7 : "C", 6 : "D"}

score = int(input())
rank = grades.get(score // 10, "F")
print(rank)