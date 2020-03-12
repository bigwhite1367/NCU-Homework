%{
#include<stdlib.h>
#include<stdio.h>	
#include "stackMachine.tab.h"
%}

NUM  	 ([1-9][0-9]*)|0
PLUS     "add"
MINUS    "sub"
MUL      "mul"
DIV      "mod"
LOAD	 "load"
INC 	 "inc"
DEC		 "dec"
COPY	 "copy"
DELETE	 "delete"
SWITCH 	 "switch"
WHITE    [ \f\n\r\t\v]

%%
{NUM}  		{yylval.ival = atoi(yytext);yylineno++;return NUMBER ; }
{PLUS} 		{yylineno++;return ADD; }
{MINUS} 	{yylineno++;return SUB; }
{MUL}   	{yylineno++;return MUL; }
{DIV}		{yylineno++;return MOD; }
{LOAD}  	{yylineno++;return LOAD; }
{INC}		{yylineno++;return inc; }
{DEC}		{yylineno++;return dec; }
{COPY}		{yylineno++;return copy; }
{DELETE}	{yylineno++;return delete; }
{SWITCH}	{yylineno++;return switch; }
{WHITE} {}
.		{ }
%%
int yywrap(){return(1);}

