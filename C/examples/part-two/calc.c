#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  if(argc < 4) {
    fprintf(stderr, "Usage: ./calc [number] [operator] [number]\n");
    return -1;
  }
  int a = atoi(argv[1]);
  int b = atoi(argv[3]);
  char opr = argv[2][0];
  if(opr == '+') {
    printf("%d %c %d = %d\n", a, opr, b, a + b);
  } else if (opr == '-') {
    printf("%d %c %d = %d\n", a, opr, b, a - b);
  } else if (opr == 'x') {
    printf("%d %c %d = %d\n", a, opr, b, a * b);
  } else if (opr == '/') {
  //Integer division
    printf("%d %c %d = %d\n", a, opr, b, a / b);
  } else {
    fprintf(stderr, "Illegal operator %c \nUse one of + - * or / \n\n", opr);
  }
  return 0;
}
