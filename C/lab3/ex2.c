#include <stdio.h>
#include <string.h>

#define MAX 1000 /* The maximum number of characters in a line of input */

main()
{
  char text[MAX], c, copy[MAX];
  int start=0, l, x=0;
  
  puts("Type some text (then ENTER):");
  
  /* Save typed characters in text[]: */
    
  fgets(text, MAX, stdin);
   start = 0;
  l = strlen(text)-2;  
  strcpy(copy, text);

  while(start<l){
  c = text[start];
  text[start]=text[l];
  text[l]=c;
  start++;
  l--;
  }

  
  printf("Your input in reverse is:\n");
  printf("%s", text);
  for(x=0; x<(strlen(text)-2); x++){
  if(text[x]!=copy[x]){
  return 0;
  }
  }
  printf("Found a palindrome!\n");
}
