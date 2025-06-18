r = 31
MOD = 1234567891

length = int(input())
data = input()

sum = 0
idx = 0
for alphabet in data:
    num = ord(alphabet) - ord("a") + 1
    sum = (sum + num * r ** idx) % MOD
    idx += 1

print(sum)