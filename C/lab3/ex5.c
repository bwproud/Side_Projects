#include <stdio.h>
#include <string.h>
main(){
int input=1;
do{
printf("Enter a number:\n");
scanf(" %d", &input);
printf("fibonacci(%d) = %d\n", input, fibonacci(input));
}while(input!=0);
}

fibonacci(int n){
if(n==1){
return 1;
}
else if(n==0){
return 0;
}
else{
return fibonacci(n-1)+fibonacci(n-2);
}
}
