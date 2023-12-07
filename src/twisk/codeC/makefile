all: client main


client: client.o
	gcc -o client def.h
	gcc -shared programmeC.o client.o -o libTwisk.so
main:
	gcc -Wall -L. main.c -o main -lTwisk
.PHONY: clean
clean:
	rm main
	rm client.o
	rm libTwisk.so
