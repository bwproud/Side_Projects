#include <stdio.h>
#include <string.h>

main(){
int input, x;
printf("Please enter a number from 1 to 5:\n" );
scanf(" %d", &input);
if(!(input>=1&&input<=5)){
printf("Number is not in the range from 1 to 5\n");
}
else{
for(x = 1; x<=input; x++){
printf("%d Hello World\n", x);
}
}
return 0;
}
