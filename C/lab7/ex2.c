#include <stdio.h>
#include <string.h>

main(){
char a[20];
int LEN=20;
int result;
int fact;
do{
fgets(a, LEN, stdin);
result = a_to_i(a);
fact = factorial(result);
printf("%d\n", fact );
}while(result!=0);
return 0;
}

int a_to_i(char* str){
int add=0, x=0;
while((*str>='0')&&(*str<='9')){
add = (add*10)+(*str-'0');
str++;
}
return add;
}

int factorial(int n){
if(n==0){
return 1;
}
else{
return  n * factorial(n-1);
}
}
