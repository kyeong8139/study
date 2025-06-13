data = input()

cur_chr = "a"

while ord(cur_chr) != ord("z") + 1:
    idx = data.find(cur_chr)
    print(idx, end=" ")

    cur_chr = chr(ord(cur_chr) + 1)