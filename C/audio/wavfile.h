#ifndef _WAVFILE_H_
#define _WAVFILE_H_

#include <stdint.h>

typedef struct {
  uint8_t sGroupID[4];
  uint32_t dwFileLength;
  uint8_t sRiffType[4];
} wav_header;

typedef struct {
  uint8_t sGroupID[4];
  uint32_t dwChunkSize;
  uint16_t wFormatTag;
  uint16_t wChannels;
  uint32_t dwSamplesPerSec;
  uint32_t dwAvgBytesPerSec;
  uint16_t wBlockAlign;
  uint16_t dwBitsPerSample;
} fmt_header;

typedef struct {
  uint8_t sGroupID[4];
  uint32_t dwChunkSize;
} datachunk_header;

void readfile(char *filename);
void print_wav_header(wav_header *bufferptr);
void print_fmt_header(fmt_header *bufferptr);
void print_datachunk_header(datachunk_header *bufferptr);

#endif
