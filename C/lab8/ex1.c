#include <stdio.h>
#include <string.h>

main(){
int result;
int fib;
do{
scanf("%d", &result);
fib = fibonacci(result);
printf("%d\n", fib );
}while(result!=0);
return 0;
}

int fibonacci(int n){
if(n==0){
return 0;
}
else if(n==1){
return 1;
}
else{
return fibonacci(n-1)+fibonacci(n-2);
}
}
