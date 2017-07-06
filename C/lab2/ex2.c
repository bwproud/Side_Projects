#include <stdio.h>
#include <string.h>

main(){
float one, two, three, four, five, sum, mean, product;
printf("Enter five floating-point numbers:\n" );
scanf(" %f", &one);
scanf(" %f", &two);
scanf(" %f", &three);
scanf(" %f", &four);
scanf(" %f", &five);
sum=one+two+three+four+five;
mean=(sum)/5;
product=one*two*three*four*five;
printf("Sum is %.4f\n", sum);
printf("Mean is %.4f\n", mean);
printf("Product is %.4f\n", product);
return 0;
}
