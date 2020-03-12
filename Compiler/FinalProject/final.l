%{
	#include "final.tab.h"
%}
PLUS		"+"
MINUS 		"-"
MUL			"*"
DIV			"/"
MOD 		"mod"
GTR			">"
SLR			"<"
EQU			"="
OP			"("
CP			")"
PB			"print-bool"
PN			"print-num"
AND			"and"
OR			"or"
NOT			"not"	
DEF			"define"
LAMBDA		"lambda"
IF			"if"
letter 		[a-z]
digit		[0-9]
separator	[ \t\n\r]
number		[1-9][0-9]*|-[1-9][0-9]*|0
ID			([a-z]|[0-9]|"-")*
BOOL_TRUE	"#t"
BOOL_FALSE	"#f"
%%
{PLUS}		{ 
				return(yytext[0]);
			}
{MINUS}		{ 
				return(yytext[0]);
			}
{MUL}		{ 
				return(yytext[0]);
			}
{DIV}		{ 
				return(yytext[0]);
			}
{MOD}		{
				yylval.tok = yytext;
				return(mod);
			}
{GTR}		{ 
				return(yytext[0]);
			}
{SLR}		{ 
				return(yytext[0]);
			}
{EQU}		{ 
				return(yytext[0]);
			}
{OP}		{ 
				return(yytext[0]);
			}
{CP}		{ 
				return(yytext[0]);
			}
{AND}		{
				yylval.tok = yytext;
				return(and);
			}
{OR}		{
				yylval.tok = yytext;
				return(or);
			}
{NOT}		{
				yylval.tok = yytext;
				return(not);
			}
{DEF}		{
				yylval.tok = yytext;
				return(defi);
			}
{LAMBDA}	{
				yylval.tok = yytext;
				return(lambda);
			}
{IF}		{
				yylval.tok = yytext;
				return(iff);
			}
{separator}	{

			}
{number}	{
				yylval.ival = atoi(yytext);
                return(number);
			}

{PB}		{
				yylval.tok = yytext;
                return(print_bool);
			}
{PN}		{
				yylval.tok = yytext;
               	return(print_num);
			}
{BOOL_TRUE}	{
				yylval.ival = 1;
                return(boolval);
			}
{BOOL_FALSE}	{
					yylval.ival = 0;
                	return(boolval);
				}
{ID}		{
				yylval.tok = yytext;
                return(id);
			}
.			{
				return(yytext[0]);
			}
%%
int yywrap(){return(1);}


