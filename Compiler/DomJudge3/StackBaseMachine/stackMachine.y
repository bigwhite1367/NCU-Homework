%{
#include<stdio.h>
#include<stdlib.h>
extern int yylineno;
typedef struct node{
	int value;
	struct node *front;
	struct node *next;
}node;
typedef struct headNode{

	unsigned int length;
	struct node *next;
	struct node *first;
	struct node *last;

}headnode;

int yylex();
void add();
void sub();
void mul();
void mod();
void incLast(headnode *list);
void decLast(headnode *list);
void copyLast(headnode *list);
void swap();
int back(headnode *list);
void push_back(headnode *list, int value);
void pop_back(headnode *list);
void yyerror(const char *message);
struct headNode inputStack;
int flag = 0;
%}
%union{
	int ival;

}
%token <ival> NUMBER
%token <opertator> OPERATOR
%token ADD
%token SUB
%token MUL
%token MOD
%token LOAD  
%token inc
%token dec
%token copy
%token delete
%token switch

%%
line : 
	 | expr line
     ;
expr : LOAD NUMBER {push_back(&inputStack,$2);}
	 | operator {}
	 ;
operator : ADD {if(inputStack.length>=2){ add();}else{flag = 1;}}
		 | SUB {if(inputStack.length>=2){ sub();}else{flag = 1;}}
		 | MUL {if(inputStack.length>=2){ mul();}else{flag = 1;}}
		 | MOD {if(inputStack.length>=2){ mod();}else{flag = 1;}}
		 | inc {if(inputStack.length>=1){incLast(&inputStack);}else{flag = 1;}}
		 | dec {if(inputStack.length>=1){decLast(&inputStack);}else{flag = 1;}}
		 | copy 	{if(inputStack.length>=1){copyLast(&inputStack);}else{flag = 1;}}
		 | delete	{if(inputStack.length>=1){pop_back(&inputStack);}else{flag = 1;}}
		 | switch	{if(inputStack.length>=2){ swap();}else{flag = 1;}}
%%
void yyerror (const char *message)
{
        fprintf (stderr, "%d %s\n",yylineno-1, message);
}

void push_back(headnode *list, int value)
{
	struct node *tmp = malloc(sizeof(struct node));
	tmp->value = value;
	if(list->length == 0)
	{
		tmp->front = NULL;
		tmp->next = NULL;
		list->last = tmp;
	}
	else
	{
		tmp->front = list->last;
		list->last->next = tmp;
		list->last = tmp;
	}
	list->length++;
}

void pop_back(headnode *list)
{
	if(list->length>1)
		{
			list->last = list->last->front;
			list->last->next = NULL;
			list->length--;
		}
		else if(list->length == 1)
		{
			list->last = NULL;
			list->length--;
		}
}

int back(headnode *list)
{
	return list->last->value;
} 


void copyLast(headnode *list)
{
	int tmp = back(&inputStack);
	push_back(&inputStack, tmp);
}

void swap()
{
	int tmp1 = back(&inputStack);
	pop_back(&inputStack);
	int tmp2 = back(&inputStack);
	pop_back(&inputStack);
	push_back(&inputStack, tmp1);
	push_back(&inputStack, tmp2);
}

void incLast(headnode *list)
{
	list->last->value++;
}

void decLast(headnode *list)
{
	list->last->value--;
}

void add()
{
	int tmp1 = back(&inputStack);
	pop_back(&inputStack);
	int tmp2 = back(&inputStack);
	pop_back(&inputStack);
	push_back(&inputStack, tmp1+tmp2);
}

void sub()
{
	int tmp1 = back(&inputStack);
	pop_back(&inputStack);
	int tmp2 = back(&inputStack);
	pop_back(&inputStack);
	push_back(&inputStack, tmp1-tmp2);
	
}

void mul()
{
	int tmp1 = back(&inputStack);
	pop_back(&inputStack);
	int tmp2 = back(&inputStack);
	pop_back(&inputStack);
	push_back(&inputStack, tmp1*tmp2);
}

void mod()
{
	int tmp1 = back(&inputStack);
	pop_back(&inputStack);
	int tmp2 = back(&inputStack);
	pop_back(&inputStack);
	push_back(&inputStack, tmp1%tmp2);
}

int main(int argc, char *argv[]) 
{
        yyparse();
        if(inputStack.length == 1 && flag == 0)
        {
        	printf("%d\n", back(&inputStack));
        }
        else if(flag == 1)
        {
        	printf("Invalid format\n");
        }
        else
        {
        	printf("Invalid format\n");
        }
        return(0);
}