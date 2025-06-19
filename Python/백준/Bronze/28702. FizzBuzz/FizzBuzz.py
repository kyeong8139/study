import sys, re

def is_integer_str(data):
    if re.fullmatch(r'\d+', data):
        return True
    else: 
        return False


a, b, c = sys.stdin.read().split()

next_num = 0
if is_integer_str(a):
    next_num = int(a) + 3
elif is_integer_str(b):
    next_num = int(b) + 2
else:
    next_num = int(c) + 1

if next_num % 15 == 0:
    print("FizzBuzz")
elif next_num % 3 == 0:
    print("Fizz")
elif next_num % 5 == 0:
    print("Buzz")
else:
    print(next_num)
