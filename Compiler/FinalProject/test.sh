#compile bison
bison -d -o test.tab.c test.y	
gcc -c -g -I.. test.tab.c
#compile flex
flex -o test.yy.c test.l
gcc -c -g -I.. test.yy.c
#compile and link bison and flex
gcc -o test test.tab.o test.yy.o -ll