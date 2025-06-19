data = input()

total = 0
weight = 1
for i in range(len(data)-1):
    if data[i] == "*": 
        weight = (1 if i % 2 == 0 else 3)
        continue
    
    multiple = (1 if i % 2 == 0 else 3)
    total += int(data[i]) * multiple

checksum = 10 - int(data[len(data) - 1])
if checksum == 10:
    checksum = 0

answer = 0
for i in range(0, 10):
    if (total + i * weight) % 10 == checksum:
        answer = i
        break

print(answer)