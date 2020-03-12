%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
void yyerror(const char *message);
typedef struct Node
{
	char name[1024];
	int val;
	struct Node *next;
}node;

struct Node *first;
struct Node *now;
struct Node *temp;

first = malloc(sizeof(node));
now = first;
now->next = 0;
%}

%union{
	int ival;
	char *tok;
	char cm[32768];
}
%token<tok>	and
%token<tok> or
%token<tok> not
%token<tok> defi
%token<tok> mod
%token<tok>	lambda
%token<tok> iff
%token<ival> number
%token<tok> id
%token<tok> print_bool
%token<tok> print_num
%token<ival> boolval
%type<cm>	PROGRAM
%type<cm>	STMT
%type<cm>	PRINT_STMT
%type<cm>	VARIABLE
%type<cm>	FUN_IDs
%type<cm>	IDs
%type<cm>	LAST_EXP
%type<cm>	FUN_NAME
%type<cm>	DEF_STMT
%type<ival>	PARAM
%type<ival> EXP
%type<ival>	FUN_CALL
%type<ival>	FUN_EXP
%type<ival>	NUM_OP
%type<ival>	PLUS
%type<ival> PLUS_EXP
%type<ival> MINUS
%type<ival> MULTIPLY
%type<ival> MULTIPLY_EXP
%type<ival> DIVIDE
%type<ival> MODULUS
%type<ival> GREATER
%type<ival> SMALLER
%type<ival>	EQUAL
%type<ival> EQUAL_EXP
%type<ival> LOGICAL_OP
%type<ival>	IF_EXP
%type<ival> TEST_EXP
%type<ival>	THEN_EXP
%type<ival>	ELSE_EXP
%type<ival> AND_OP
%type<ival> AND_EXP
%type<ival> OR_OP
%type<ival> OR_EXP
%type<ival> NOT_OP
%type<ival>	FUN_BODY
%left '+'
%left '-'
%left '*'
%left '/'
%left '('
%left ')'
%left '<'
%left '>'
%left '='
%%
PROGRAM		:	PROGRAM STMT			{
	

								}
			|	STMT			{

								}
			
			;

STMT		:	EXP 			{
			
								}
			|	DEF_STMT		{

								}
			|	PRINT_STMT		{

								}
			;

PRINT_STMT	:	'(' print_num	EXP ')'		{
												printf("%d\n",$3);
											}
			|	'(' print_bool	EXP ')'		{
												if($3 == 1)
												{
													printf("#t\n");
												}
												else if ($3 == 0)
												{
													printf("#f\n");
												}
											}
			;

EXP 		:	boolval		{
									$$ = $1;
								}
			|	number			{
									$$ = $1;
								}
			|	VARIABLE		{
                					temp = first->next;
                					while(temp)
                					{
                    					if(strcmp(temp->name,$1) == 0)
                    					{
                        					$$ = temp->val;
                        					break;
                   			 			}
                    					else
                    					{
                        					temp = temp->next;
                    					}
                					}
                				}
			|	NUM_OP			{
									$$ = $1;
								}
			|	LOGICAL_OP		{
									$$ = $1;
								}
			|	FUN_EXP			{
									$$ = $1;
								}
			|	FUN_CALL		{
									$$ = $1;
								}
			|	IF_EXP			{
									$$ = $1;
								}
			;

NUM_OP		:	PLUS 			{
									$$ = $1;
								}
			|	MINUS			{
									$$ = $1;
								}
			|	MULTIPLY 		{
									$$ = $1;
								}
			|	DIVIDE 			{
									$$ = $1;
								}
			|	MODULUS 		{
									$$ = $1;
								}
			|	GREATER 		{
									$$ = $1;
								}
			|	SMALLER 		{
									$$ = $1;
								}
			|	EQUAL 			{
									$$ = $1;
								}
			;

PLUS		:	'('	'+'	PLUS_EXP ')'	{
											$$ = $3;
										}
			;

PLUS_EXP	: 	EXP 	EXP  			{
											$$ = $1 + $2;
										}
			|	PLUS_EXP 	EXP 		{
											$$ = $1 + $2;
										}
			;


MINUS		:	'('	'-'	EXP 	EXP ')' {
											$$ = $3 - $4;
										}
			;

MULTIPLY	:	'(' '*'	MULTIPLY_EXP ')'{
											$$ = $3;
										 }
			;

MULTIPLY_EXP :	EXP 	EXP    			{
											$$ = $1 * $2;
										}
			|	MULTIPLY_EXP EXP 		{
											$$ = $1 * $2;
										}
			;

DIVIDE		:	'(' '/' EXP 	EXP ')' {
											$$ = $3 / $4;
										}
			;

MODULUS		:	'(' mod EXP 	EXP ')' {
											$$ = $3 % $4;
										}
			;

GREATER		:	'('	'>'	EXP 	EXP ')'	{
											if($3>$4)
											{
												$$ = 1;
											}
											else
											{
												$$ = 0;
											}
										}
			;

SMALLER		:	'(' '<'	EXP 	EXP ')' {
											if($3<$4)
											{
												$$ = 1;
											}
											else
											{
												$$ = 0;
											}
										}
			;

EQUAL		:	'(' '=' EQUAL_EXP ')'	{
											if($3 != 102939732)
											{
												$$ = 1;
											}
											else
											{
												$$ = 0;
											}
										}
			;

EQUAL_EXP	:	EXP 	EXP 			{
											if($1 == $2) 
											{
												$$ = $1;
											}
											else
											{
												$$ = 102939732;
											}
										}
			|	EQUAL_EXP 	EXP 		{
											if($1 == $2) 
											{
												$$ = $1;
											}
											else
											{
												$$ = 102939732;
											}
										}
			;

LOGICAL_OP	:	AND_OP					{
											$$ = $1;
										}
			|	OR_OP					{
											$$ = $1;
										}
			|	NOT_OP					{
											$$ = $1;
										}
			;

AND_OP		:	'('	and AND_EXP ')'		{
                							if($3 == 1)
                							{
                    							$$ = 1;
               				 				}
                							else
                							{
                    							$$ = 0;
                							}
            							}
			;

AND_EXP		:	EXP EXP 		  	{
                						if($1 + $2 == 2)
                						{
                    						$$ = 1;
                						}
               	 						else
               	 						{
                    						$$ = 0;
                						}
            						}
			|	AND_EXP EXP 		{
             				   			if($1 + $2 == 2)
             				   			{
                    						$$ = 1;
                						}
                						else
                						{
                    						$$ = 0;
                						}
            						}
			;

OR_OP 		:	'(' or OR_EXP ')'	{
                						if($3 == 1)
                						{
                    						$$ = 1;	
                						}
                						else
                						{
                    						$$ = 0;
                						}
           							}
			;

OR_EXP		:	EXP EXP 			{
                						if($1 + $2 >= 1)
                						{
                    						$$ = 1;
                						}
                						else
                						{
                    						$$ = 0;
                						}
						            }
			|	OR_EXP	EXP 		{
                						if($1 + $2 >= 1)
                						{
                    						$$ = 1;
                						}
                						else
                						{
                    						$$ = 0;
                						}
            						}
			;

NOT_OP 		:	'(' not EXP ')'		{
                						if($3 == 0)
                						{
                    						$$ = 1;
                						}
                						else if($3 == 1)
                						{
                    						$$ = 0;
                						}
            						}
			; 	
	

DEF_STMT	:	'(' defi	VARIABLE	EXP ')'	 {
                									temp = malloc(sizeof(node));
         									       	strcpy(temp->name,$3);
                									temp->val = $4;
                									temp->next = 0;
                									now->next = temp;	
                									now = temp;
            									}
			;

VARIABLE	:	id 	{
                		strcpy($$,$1);
            		}
			;

FUN_EXP		:	'('	FUN_IDs 	FUN_BODY 	')'	{
													$$ = $3;
												}
			;

FUN_IDs 	:	'('	IDs ')'		{
									temp = first->next;
                					while(temp)
                					{
                    					if(strcmp(temp->name,$2) == 0)
                    					{
                    						strcat($$, $2);
                 
                        					break;
                   			 			}
                    					else
                    					{
                        					temp = temp->next;
                    					}
                					}
								}
			;

IDs 		:	VARIABLE		{
									
								}
			|	IDs 	VARIABLE	{

									}
			;

FUN_BODY 	:	EXP 	{
               		 		$$ = $1;
            			}
			;

FUN_CALL	:	'('	FUN_EXP 	PARAM ')'	{
												$$ = $2;
											}
			|	'('	FUN_NAME	PARAM ')'	{
												$$ = 1;
											}	
			;

PARAM		:	EXP 	{
               		 		$$ = $1;
            			}
			|	PARAM EXP
			;	

LAST_EXP	:	EXP 	{
               		 		$$ = $1;
            			}
			;

FUN_NAME	:	id 	{
                		strcpy($$,$1);
            		}
			;

IF_EXP		:	'(' iff 	TEST_EXP THEN_EXP ELSE_EXP ')'	{
                												if($3 == 1)
                												{
                    												$$ = $4;
               													}
                												else if($3 == 0)
                												{
                    												$$ = $5;
                												}
            											}
			;

TEST_EXP	:	EXP 	{
               		 		$$ = $1;
            			}
			;

THEN_EXP	:	EXP 	{
               		 		$$ = $1;
            			}
			;

ELSE_EXP 	:	EXP 	{
               		 		$$ = $1;
            			}
			;

%%


void yyerror(const char *message)
{
    printf("syntax error\n");
}

int main(int argc,char *argv[]){
    
    yyparse();
    return(0);
}

