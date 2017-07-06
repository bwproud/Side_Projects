
#include <stdio.h> 
#include <string.h> 


main()
{
  int c, row, mv, r, g, b, x, y, gray;
  char a[10];
  scanf("%s", a);
  scanf("%d%d%d", &c, &row, &mv);
  printf("P2\n%d\n%d\n%d\n", c, row, 255);
  for(x=0; x<row; x++){
  for(y=0; y<c; y++){
  scanf("%d%d%d", &r, &g, &b);
  gray = ((r*30 + g*59 + b*11) * 255) / (100 * mv);
  printf("%d\n", gray);
  }
  }
  return 0;
}
