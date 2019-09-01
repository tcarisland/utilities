CC = gcc
CFLAGS = -Wall
DEPS = wavfile.h
OBJ = readwav.o wavfile.o

%.o: %.c $(DEPS)
	$(CC) $(CFLAGS) -c -o $@ $<

readwav: $(OBJ)
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm -f *.o
	rm -f *~
	rm readwav
