%{
#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<iostream>
extern YY_BUFFER_STATE yy_scan_string(const char *str);
%}

NUM      ([1-9][0-9]*)|0
PLUS     \+
MINUS    \-
MUL      \*
DIV      \/
LPR      \(
RPR      \)
WHITE    [ \f\n\r\t\v]
%%
{NUM}	{ printf("NUM "); ECHO; printf("\n");}
{PLUS}	{ printf("PLUS\n");}
{MINUS} { printf("MINUS\n");}
{MUL}   { printf("MUL\n");}
{DIV}   { printf("DIV\n");}
{LPR}   { printf("LPR\n");}
{RPR}   { printf("RPR\n");}
{WHITE} { printf("");}
%%
int yywrap(){return(1);}
void scan_string(const char* str)
{
    yy_switch_to_buffer(yy_scan_string(str));
}
int main(int argc, char *argv[]) {

	std::string s;
	char* c;
	while(std::cin>>s)
	{
	    c = new char[s.length() + 1];	
	    strcpy(c, s.c_str());
	    scan_string(c);
	    yylex();
		delete [] c;
	}
	return(0);
}

