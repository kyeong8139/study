def getGCD(a, b):
    if b == 0:
        return a
    return getGCD(b, a%b)

def getLCM(a, b, gcd):
    return (a // gcd) * (b // gcd) * gcd

a,b = map(int, input().split())
if b > a:
    a, b = b, a

gcd = getGCD(a, b)
lcm = getLCM(a, b, gcd)

print(gcd)
print(lcm)