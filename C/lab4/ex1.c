#include <stdio.h> 
#include <string.h> 

#define NUM 25   
#define LEN 1000  

main()
{
  char Strings[NUM][LEN];
  int a, b, c, ordered;
  char temp[LEN];
  printf("Please enter %d strings, one per line:\n", NUM);
  for(a=0; a<NUM; a++){
  fgets(Strings[a], LEN, stdin);
  }

  puts("\nHere are the strings in the order you entered:");

  for(a=0; a<NUM; a++){
  printf("%s", Strings[a]);
  }
 
   do {
    ordered = 1;
    for (a = 1; a<NUM; a++) {
      for (b = 0; b<LEN; b++) {
        if (Strings[a-1][b] < Strings[a][b]) {
          break;
        }
        if (Strings[a-1][b] > Strings[a][b]) {
          ordered = 0;
          for (c = 0; c<LEN; c++) {
            temp[c] = Strings[a-1][c];
          }
          for (c = 0; c<LEN; c++) {
            Strings[a-1][c] = Strings[a][c];
          }
          for (c = 0; c<LEN; c++) {
            Strings[a][c] = temp[c];
          }
          break;
        }
      }
    }
  } while (!ordered);

  puts("\nIn alphabetical order, the strings are:");     
 
  for(a=0; a<NUM; a++){
  printf("%s", Strings[a]);
  }
  return 0;
}
