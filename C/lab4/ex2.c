#include <stdio.h> 
#include <string.h> 

#define NUM 25   
#define LEN 1000  

main()
{
  char Strings[NUM][LEN];
  int a, x, y, z;
  char p, v;
  printf("Please enter %d strings, one per line:\n", NUM);
  for(x=0; x<NUM; x++){
  fgets(Strings[x], LEN, stdin);
  }

  puts("\nHere are the strings in the order you entered:");


  /* Write a for loop here to print all the strings. */

  for(x=0; x<NUM; x++){
  printf("%s", Strings[x]);
  }
  
  for(x=0; x<NUM; x++){
  for(y=0; y<NUM; y++){
  if(strcmp(Strings[x], Strings[y])<0){
  char temp[LEN];
  strcpy(temp, Strings[x]);
  strcpy(Strings[x], Strings[y]);
  strcpy(Strings[y], temp);
  }
  }
  }

  puts("\nIn alphabetical order, the strings are:");     
  for(x=0; x<NUM; x++){
  printf("%s", Strings[x]);
  }
  return 0;
}
