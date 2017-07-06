#include <stdio.h>
#include <string.h>
#include <stdbool.h>

main(){
int  one=2;
int x;
do{
bool prime = true;
int divisor =0;
printf("Number [1-100]: ?\n" );
scanf(" %d", &one);
if(one==0){
printf("Done\n");
return 0;
}
else if(one==1){
printf("Non-prime (special case)\n");
continue;
}
for(x=1; x<one; x++){
if(one%x==0&&x!=1){
prime = false;
divisor=x;
break;
}
}
if(prime){
printf("Prime\n");
}
else{
printf("Non-prime, divisible by %d\n", divisor);
}
}while(one!=0);
return 0;
}
