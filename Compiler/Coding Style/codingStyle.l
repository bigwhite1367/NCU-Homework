%{
#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<vector>
#include<iostream>
#define CL 0
#define NUM 1
#define ID 2
#define STRLIT 3
#define SYMBOL 4
#define INCLUDE 5
#define SHARP 6
#define LPR 9
#define RPR 10
#define SEMICOLON 11
#define OP 12
#define CP 13
#define SLASH 14
extern YY_BUFFER_STATE yy_scan_string(const char *str);
struct due
{
	int ntoken;
	std::string text;
	due(int n)
	{
		ntoken = n;
	}

	due(int n, std::string t)
	{
		ntoken = n;
		text = t;
	} 

	int getNToken()
	{
		return ntoken; 
	}

	std::string getText()
	{
		return text;
	}
};
std::vector<due> list;
std::vector<std::string> result;
%}

NUM       ([1-9][0-9]*)|0
ID        [A-Za-z_][A-Za-z0-9_]*
STRLIT    \"[^\"]*\"
SYMBOL    [\+\-\*\/\=\<\>]
SHARP	  #
INCLUDE   [A-Za-z_][A-Za-z0-9_]*\<[A-Za-z_][A-Za-z0-9_\.]*$\>
LPR       \(
RPR       \)
SEMICOLON \;
OP        \{
CP        \}
SLASH     \/\/
WHITE     [ \f\n\r\t\v]
%%
{NUM}	     {due pair(NUM,yytext);list.push_back(pair);}
{ID}         {due pair(ID,yytext);list.push_back(pair);}
{STRLIT}     {due pair(STRLIT,yytext);list.push_back(pair);}
{SYMBOL}	 {due pair(SYMBOL,yytext);list.push_back(pair);}
{SHARP}		 {due pair(SHARP,yytext);list.push_back(pair);}
{INCLUDE}	 {due pair(INCLUDE,yytext);list.push_back(pair);}
{LPR}        {due pair(LPR,yytext);list.push_back(pair);}
{RPR}        {due pair(RPR,yytext);list.push_back(pair);}
{SEMICOLON}  {due pair(SEMICOLON,yytext);list.push_back(pair);}
{OP}         {due pair(OP,yytext);list.push_back(pair);}
{CP}         {due pair(CP,yytext);list.push_back(pair);}
{SLASH}      {due pair(SLASH,yytext);list.push_back(pair);}
{WHITE}      {}
%%
int yywrap(){return(1);}
void scan_string(const char* str)
{
    yy_switch_to_buffer(yy_scan_string(str));
}
int main(int argc, char *argv[]) {
	
	std::string s;
	char* c;
	int tabCounter = 0;
	while(std::getline(std::cin,s))
	{
	    c = new char[s.length() + 1];	
	    strcpy(c, s.c_str());
	    scan_string(c);
	    yylex();
		delete [] c;
		due pair(CL,"\n");
		list.push_back(pair);
	}

	for(uint incounter = 0 ; incounter < list.size() ; incounter++) // 還未加tab
	{
		if(incounter < list.size() -1)
		{
			if(list[incounter].getNToken() == CL)
			{
				result.push_back(list[incounter].getText());
				
				for(int counter=0; counter<tabCounter; counter++)
				{
					result.push_back("\t");
				}
			}else if(list[incounter].getNToken() == OP)
			{
				++tabCounter;
				//std::cout<<tabCounter<<std::endl;
				result.push_back(list[incounter].getText());
			}
			else if(list[incounter].getNToken() == SHARP)
			{
				result.push_back(list[incounter].getText());
			}
			else if(list[incounter].getNToken() == CP)
			{
				if(result.back() == "\t")
				{
					result.pop_back();
				}
				--tabCounter;
				result.push_back(list[incounter].getText());
			}
			else if(list[incounter].getNToken() != SEMICOLON && list[incounter+1].getNToken() != SEMICOLON && list[incounter].getNToken() != SYMBOL && list[incounter+1].getNToken() != SYMBOL && list[incounter].getNToken() != LPR && list[incounter].getNToken() != RPR && list[incounter+1].getNToken() != LPR && list[incounter+1].getNToken() != RPR && list[incounter+1].getNToken() != OP && list[incounter+1].getNToken() != CP && list[incounter+1].getNToken() != CL && list[incounter+1].getNToken() != CL && list[incounter+1].getNToken() != CL)
			{
				result.push_back(list[incounter].getText());
				result.push_back(" ");
			
			}
			else
			{
				result.push_back(list[incounter].getText());
			}
		}
	}

	for(uint counter=0; counter < result.size() ; counter++)
	{
		std::cout<<result[counter];
	}
	std::cout<<"\n";
	return(0);
}

