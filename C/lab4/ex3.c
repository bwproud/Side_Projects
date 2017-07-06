#include <stdio.h>
#include <string.h> 
#include <stdlib.h>

#define NUM 25
#define LEN 1000 
main()
{
  char* Strings[NUM];
  char temp[LEN];
  int x, y;
  printf("Please enter %d strings, one per line:\n", NUM);
  for (x=0; x<NUM; x++){
    fgets(temp, LEN, stdin);
    int length = strlen(temp);
    Strings[x] = malloc(length+1);
    strcpy(Strings[x], temp);
  }

  puts("\nHere are the strings in the order you entered:");

  for(x=0; x<NUM; x++){
  printf("%s",Strings[x] );
  }

  for(x=0; x<NUM; x++){
  for(y=0; y<NUM; y++){
  if(strcmp(Strings[x], Strings[y])<0){
  char* temp = Strings[x];
  Strings[x]=Strings[y];
  Strings[y]=temp;
  }
  }
  }

  puts("\nIn alphabetical order, the strings are:");     
  for(x=0; x<NUM; x++){
  printf("%s", Strings[x]);
  }

  for(x=0; x<NUM; x++){
  free(Strings[x]);
  }
  
  return 0;
}

