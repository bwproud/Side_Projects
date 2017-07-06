#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LEN 1000
int * selectSort(int array[]){
    int *p;
    return p;
}

main()
{
    int *nums;
    int x;
    int NUM=0;
    scanf(" %d", &NUM);
    int numbers[NUM];
    for (x=0; x<NUM; x++){
        scanf(" %d", &numbers[x]);
    }
    
    puts("\nHere are the numbers in the order you entered:");
    
    for(x=0; x<NUM; x++){
        printf("%d\n",numbers[x]);
    }
    
    nums=selectSort(numbers);
    
    puts("\nIn numerical order, the numbers are:");
    for(x=0; x<NUM; x++){
        printf("%d\n", numbers[x]);
    }
    
    return 0;
}




