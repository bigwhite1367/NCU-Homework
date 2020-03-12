#compile bison
bison -d -o stackMachine.tab.c stackMachine.y	
gcc -c -g -I.. stackMachine.tab.c
#compile flex
flex -o stackMachine.yy.c stackMachine.l
gcc -c -g -I.. stackMachine.yy.c
#compile and link bison and flex
gcc -o stackMachine stackMachine.tab.o stackMachine.yy.o -ll

