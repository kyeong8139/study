import sys

class Person():

    def __init__(self, weight, height):
        self.weight = weight
        self.height = height 
        self.rank = 0

read = sys.stdin.readline
n = int(read())
people = []

for _ in range(n):
    weight, height = map(int, read().split())
    person = Person(weight, height)
    people.append(person)

for i in people:
    rank = 1
    for j in people:
        if j.weight > i.weight and j.height > i.height:
            rank += 1
    i.rank = rank

sys.stdout.write(" ".join(f"{person.rank}" for person in people))