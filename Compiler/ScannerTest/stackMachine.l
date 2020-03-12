%{
#include<stdlib.h>
#include<stdio.h>
#include<iostream>
#include<vector>	
%}

NUM  ([1-9][0-9]*)|0
PLUS     add
MINUS    sub
MUL      mul
DIV      mod
LOAD	 load
WHITE    [ \f\n\r\t\v]
%%
{NUM}  	{echo;}
{PLUS} 	{echo;}
{MINUS} {ehco;}
{MUL}   {echo;}
{DIV}	{echo;}
{WHITE} {}
%%

int main()
{
	yylex();
	return 0;
}