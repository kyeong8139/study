import sys

data = sys.stdin.read().split("\n")
for line in data:
    if line:    
        A, B = map(int, line.split())
        print(A+B)