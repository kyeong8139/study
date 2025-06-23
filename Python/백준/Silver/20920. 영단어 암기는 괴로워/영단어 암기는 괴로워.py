import sys

read = sys.stdin.readline

n, m = map(int, read().split())
word_dict = {}

for _ in range(n):
    word = read().strip()
    if len(word) < m : 
        continue

    word_dict[word] = word_dict.get(word, 0) + 1

my_word = sorted(word_dict.keys(), key= lambda word : (-(word_dict.get(word)), -(len(word)), word))

sys.stdout.write("\n".join(my_word))