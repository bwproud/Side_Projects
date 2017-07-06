#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LEN 1000
void quickSort(int array[LEN], int low, int high){
    int p, temp, x, y;
    if(low<high){
        y=high;
        p=low;
        x=low;
        while(x<y){
            while(array[x]<=array[p]&&x<high){
                x++;
            }
            while(array[y]>array[p]){
                y--;
            }
            if(x<y){
            temp=array[x];
            array[x]=array[y];
            array[y]=temp;
            }
        }
        temp=array[p];
        array[p]=array[y];
        array[y]=temp;
        quickSort(array, low, y-1);
        quickSort(array, y+1, high);
    }
}


main()
{
    int x;
    int NUM=0;
    scanf(" %d", &NUM);
    int numbers[NUM];
    for (x=0; x<NUM; x++){
        scanf(" %d", &numbers[x]);
    }

    quickSort(numbers, 0, NUM-1);

    for(x=0; x<NUM; x++){
        printf("%d\n", numbers[x]);
    }
    return 0;
}




