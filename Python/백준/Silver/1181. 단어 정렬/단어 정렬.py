import sys

_ = int(sys.stdin.readline())
words = list(set(sys.stdin.read().split()))
words.sort(key=lambda x: (len(x), x))

sys.stdout.write("\n".join(words))