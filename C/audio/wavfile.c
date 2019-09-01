#include <stdio.h>
#include <sys/stat.h>
#include <stdint.h>
#include <stdlib.h>
#include "wavfile.h"

#define LINE printf("\n");

void print_frames(int frames, int skip_samples, fmt_header *fmt_headerptr, FILE *input_file);

void readfile(char *filename) {
  printf("Filename              ->    %s\n", filename);
  FILE *input_file = fopen(filename, "rb");
  //unsigned char buffer[BUFSIZE];
  wav_header *wav_bufptr = malloc(sizeof(wav_header));
  fmt_header *fmt_bufptr = malloc(sizeof(fmt_header));
  datachunk_header *datachunk_bufptr = malloc(sizeof(datachunk_header));
  fread(wav_bufptr, sizeof(wav_header), 1, input_file);
  fread(fmt_bufptr, sizeof(fmt_header), 1, input_file);
  fread(datachunk_bufptr, sizeof(datachunk_header), 1, input_file);
  LINE;
  print_wav_header(wav_bufptr);
  LINE;
  print_fmt_header(fmt_bufptr);
  LINE;
  print_datachunk_header(datachunk_bufptr);
  LINE;
  print_frames(datachunk_bufptr->dwChunkSize, 8, fmt_bufptr, input_file);
  LINE;
  free(wav_bufptr);
  free(fmt_bufptr);
  free(datachunk_bufptr);
  fclose(input_file);
}

void print_frames(int frames, int skip_samples, fmt_header *fmt_headerptr, FILE *input_file) {
  int a, b, c, offset, sample_counter;
  uint8_t *frame = malloc(sizeof(uint8_t) * fmt_headerptr->wBlockAlign);
  uint32_t sample;
  int16_t s1;
  int32_t s2;
  float s3;
  sample_counter = 0;
  for(a = 0; a < frames; a++) {
    offset = fmt_headerptr->wBlockAlign - 1;
    fread(frame, sizeof(uint8_t) * fmt_headerptr->wBlockAlign, 1, input_file);
    if(((++sample_counter) % skip_samples) == 0) {
      printf("%d ", fmt_headerptr->dwBitsPerSample);
      for(b = 0; b < fmt_headerptr->wChannels; b++) {
        sample = 0;
        s1 = 0;
        s2 = 0;
        s3 = 0;
        for(c = 0; c < (fmt_headerptr->dwBitsPerSample / 8); c++) {
          sample <<= (c * 8);
          sample |= frame[offset--];
        }
        if(fmt_headerptr->dwBitsPerSample == 16) {
            s1 = (int16_t) sample;
            printf("%d\t", s1);
        } else if(fmt_headerptr->dwBitsPerSample == 24) {
            s2 = (int32_t) sample;
            printf("%d\t", s2);
        } else if(fmt_headerptr->dwBitsPerSample == 32) {
            s3 = (float) sample;
            printf("%f\t", s3);
        }
      }
      LINE;
    }
  }
  free(frame);
}

void print_wav_header(wav_header *fmt_bufptr) {
  printf("%lu %s ->    %c%c%c%c \n", sizeof(fmt_bufptr->sGroupID) ,      "sGroupID[4]        ", fmt_bufptr->sGroupID[0], fmt_bufptr->sGroupID[1], fmt_bufptr->sGroupID[2], fmt_bufptr->sGroupID[3]);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwFileLength) ,        "dwFileLength       ", fmt_bufptr->dwFileLength);
  printf("%lu %s ->    %c%c%c%c \n", sizeof(fmt_bufptr->sRiffType) ,     "sRiffType[4]       ", fmt_bufptr->sRiffType[0], fmt_bufptr->sRiffType[1], fmt_bufptr->sRiffType[2], fmt_bufptr->sRiffType[3]);
}

void print_fmt_header(fmt_header *fmt_bufptr) {
  printf("%lu %s ->    %c%c%c%c \n", sizeof(fmt_bufptr->sGroupID) ,      "sGroupID           ", fmt_bufptr->sGroupID[0], fmt_bufptr->sGroupID[1], fmt_bufptr->sGroupID[2], fmt_bufptr->sGroupID[3]);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwChunkSize) ,         "dwChunkSize        ", fmt_bufptr->dwChunkSize);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->wFormatTag) ,          "wFormatTag         ", fmt_bufptr->wFormatTag );
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->wChannels) ,           "wChannels          ", fmt_bufptr->wChannels);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwSamplesPerSec) ,     "dwSamplesPerSec    ", fmt_bufptr->dwSamplesPerSec);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwAvgBytesPerSec) ,    "dwAvgBytesPerSec   ", fmt_bufptr->dwAvgBytesPerSec  );
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->wBlockAlign) ,         "wBlockAlign        ", fmt_bufptr->wBlockAlign);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwBitsPerSample) ,     "dwBitsPerSample    ", fmt_bufptr->dwBitsPerSample);
}

void print_datachunk_header(datachunk_header *fmt_bufptr) {
  printf("%lu %s ->    %c%c%c%c \n", sizeof(fmt_bufptr->sGroupID) ,      "sGroupID           ", fmt_bufptr->sGroupID[0], fmt_bufptr->sGroupID[1], fmt_bufptr->sGroupID[2], fmt_bufptr->sGroupID[3]);
  printf("%lu %s ->    %d \n", sizeof(fmt_bufptr->dwChunkSize) ,         "dwChunkSize        ", fmt_bufptr->dwChunkSize);
}
