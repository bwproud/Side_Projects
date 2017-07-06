#include <stdio.h>
#include <string.h>

main(){
int LEN =15;
char array[20][LEN];
char end[]="END";
int a=0, x;
do{
scanf(" %s" ,array[a]);
a++;
}while(strcmp(array[a-1],end)!=0);
bedtimestory(array, 0, a-1);
return 0;
}

void bedtimestory(char words[20][15], int current, int number) {
int x;
char a[40];
if(current==0){
printf("A %s couldn't sleep, so her mother told a story about a little %s,\n", words[current], words[current+1]);
bedtimestory(words, current+1, number);
printf("... and then the %s fell asleep.\n", words[current]);
}
else if(current<number-1){
for(x=0; x<current; x++){
printf("  ");
}
printf("who couldn't sleep, so the %s's mother told a story about a little %s,\n", words[current], words[current+1]);
bedtimestory(words, current+1, number);
for(x=0; x<current; x++){
printf("  ");
}
printf("... and then the little %s fell asleep;\n",words[current]);
}
else if(current==number-1){
for(x=0; x<current; x++){
printf("  ");
}
printf("... who fell asleep.\n");
}
}
