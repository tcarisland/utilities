#include <stdio.h>
#include "wavfile.h"

int main(int argc, char *argv[]) {
  if(argc > 1) {
    readfile(argv[1]);
  } else {
    printf("usage ./readwav [filename]\n");
  }
}
