/*  Example: C program to find area of a circle */

#include <stdio.h>
#define PI 3.14159

main()
{
  float r, a, c;

  printf("Enter radius (in cm):\n");
  scanf("%f", &r);

  r=(r*(1/2.54));
  a = PI * r * r;
  c = PI * r * 2;

  printf("Circle's area is %3.2f (sq in).\n", a);
  printf("Its circumference is %3.2f (in).\n", c);
  return;
}
