#include <stdio.h>
int main(int argc, char *argv[]) {
  int i;
  for(i = 32; i < 127; i++) {
    printf("| %d | %c\t", i, i);
    if(!((i-31) % 8)) {
      printf("\n");
    }
  }
  printf("\n");
}
