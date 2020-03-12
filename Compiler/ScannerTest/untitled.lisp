%{
#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<iostream>
extern YY_BUFFER_STATE yy_scan_string(const char *str);
%}

NUM      (\+|\-)?([0-9]\.[0-9]*)E(\+|\-)?(([1-9][0-9]*)|0)
%%
{NUM}	{ECHO; printf("\n");}
.       {//do nothing}
%%
int yywrap(){return(1);}
int main(int argc, char *argv[]) {

	yylex();

	return(0);
}

