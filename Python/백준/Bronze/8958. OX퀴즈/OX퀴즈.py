test_case_cnt = int(input())

for test_case in range(test_case_cnt):
    data = input()
    
    score = 0
    streak = 0
    for result in data:
        if result == "O":
            streak += 1
            score += streak
        else:
            streak = 0
            
    print(score)