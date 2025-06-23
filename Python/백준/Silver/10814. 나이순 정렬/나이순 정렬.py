import sys

class Member:

    def __init__(self, order, age, name):
        self.order = order
        self.age = age
        self.name = name


read = sys.stdin.readline
n = int(read())
members = []

order = 1
for _ in range(n):
    age, name = read().split()
    age = int(age)
    member = Member(order, age, name)
    members.append(member)

    order += 1

members.sort(key= lambda member: (member.age, member.order))

sys.stdout.write("\n".join(f"{member.age} {member.name}" for member in members))