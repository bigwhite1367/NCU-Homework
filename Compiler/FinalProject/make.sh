#compile bison
bison -d -o final.tab.c final.y	
gcc -c -g -I.. final.tab.c
#compile flex
flex -o final.yy.c final.l
gcc -c -g -I.. final.yy.c
#compile and link bison and flex
gcc -o final final.tab.o final.yy.o -ll