
#include <stdio.h> 
#include <string.h> 


main()
{
  int c, row, mv, x, y, gray, x1, x2, y1, y2, co, r;
  char a[10];
  scanf("%d%d%d%d", &x1, &x2, &y1, &y2);
  scanf("%d%d%d", &c, &row, &mv);
  co = (x2-x1)+1;
  r= (y2-y1)+1;
  printf("P2\n%d\n%d\n%d\n", co, r, 255);
  for(x=0; x<row; x++){
  for(y=0; y<c; y++){
  scanf("%d", &gray);
  if(y>=x1&&y<=x2&&x>=y1&&x<=y2){
  printf("%d\n", gray);
  }
  }
  }
  return 0;
}
