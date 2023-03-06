cmd_/home/thanhyk14/os/week2/p1/simple.mod := printf '%s\n'   simple.o | awk '!x[$$0]++ { print("/home/thanhyk14/os/week2/p1/"$$0) }' > /home/thanhyk14/os/week2/p1/simple.mod
