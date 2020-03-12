%{
#include <stdio.h>
#include <string.h>
int wrong=0;
int yylex();

void yyerror(const char *message);
void semerror(int site);
%}

%union {
int 	ival;
int 	sym;
struct def{
	int i;
	int j;
	} mat;
}
%type <mat> matrix
%token <ival> NUM
%token <sym> '+'
%token <sym> '*'
%token <sym> '-'
%left '+' '-'
%left '*'
%left '^'
%%
line	:	matrix					{if (wrong==0){ printf("Accepted");}}
		;
matrix	:	'[' NUM ',' NUM ']'		{$$.i = $2;$$.j = $4;}
		|	matrix '+' matrix		{
										if ($1.i==$3.i && $1.j==$3.j){
											$$.i = $1.i;
											$$.j = $1.j;
										}
										else {
											if (wrong==0){
												semerror($2);
												wrong++;
											}
										}
									}
		|	matrix '-' matrix		{
										if ($1.i==$3.i && $1.j==$3.j){
											$$.i = $1.i;
											$$.j = $1.j;
										}
										else {
											if (wrong==0){
												semerror($2);
												wrong++;
											}
										}
									}
		|	matrix '*' matrix		{
										if ($1.j==$3.i){
											$$.i = $1.i;
											$$.j = $3.j;
										}
										else {
											if (wrong==0){
												semerror($2);
												wrong++;
											}
										}
									}
		|	matrix '^' 'T'			{$$.i = $1.j; $$.j = $1.i;}
		| 	'(' matrix ')'			{$$.i = $2.i; $$.j = $2.j;}
		;

%%
void semerror(int site){
	printf("Semantic error on col %d\n", site);
}

void yyerror (const char *message)
{
    fprintf (stderr, "%s\n",message);
}

int main(int argc, char *argv[]) {
        yyparse();
        return(0);
}



%{
#include <stdio.h>
#include <string.h>
int yylex();
void yyerror(const char *message);
void colError(int site);

struct mt{
	int row;
	int column;
	} m;
int flag=0;
%}

%union {
int 	ival;
int 	sym;

}
%type <m> matrix
%token <ival> NUM
%token <sym> '+'
%token <sym> '-'
%token <sym> '*'
%left '+' '-'
%left '*'
%left '^'
%%
stmts	:	matrix					{if (flag==0){ printf("Accepted");}}
		;
matrix	:	'[' NUM ',' NUM ']'		{$$.row = $2;$$.column = $4;}
		|	matrix '+' matrix		{
										if ($1.row==$3.row && $1.column==$3.column)
										{
											$$.row = $1.row;
											$$.column = $1.column;
										}
										else 
										{
											if (flag==0){
												colError($2);
												flag++;
											}
										}
									}
		|	matrix '-' matrix		{
										if ($1.row==$3.row && $1.column==$3.column)
										{
											$$.row = $1.row;
											$$.column = $1.column;
										}
										else 
										{
											if (flag==0)
											{
												colError($2);
												flag++;
											}
										}
									}
		|	matrix '*' matrix		{
										if ($1.column==$3.row)
										{
											$$.row = $1.row;
											$$.column = $3.column;
										}
										else 
										{
											if (flag==0)
											{
												colError($2);
												flag++;
											}
										}
									}
		|	matrix '^' 'T'			{$$.row = $1.column; $$.column = $1.row;}
		| 	'(' matrix ')'			{$$.row = $2.column; $$.column = $2.column;}
		;

%%
void colError(int col){
	printf("Semantic error on col %d\n", col);
}

void yyerror (const char *message)
{
    fprintf (stderr, "%s\n",message);
}

int main(int argc, char *argv[]) {
        yyparse();
        return(0);
}