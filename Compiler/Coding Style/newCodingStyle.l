%{
#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<vector>
#include<iostream>
#define LINE 1
#define OP 2
#define CP 3
#define SEMICOLON 4
#define LPR 5
#define RPR 6
#define SYMBOL 7
#define WHITE 8
#define TAB 9
#define CL 0
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

LINE [^ \t\n\{\}][^\{\}]
OP   \{
CP   \}
SYMBOL    [\+\-\*\/\=\<\>]
SEMICOLON \;
LPR       \(
RPR       \)
WHITE     [ ]
TAB 	  [\t]
%%
{LINE}    	 {due pair(LINE,yytext);list.push_back(pair);}
{OP}         {due pair(OP,yytext);list.push_back(pair);}
{CP}         {due pair(CP,yytext);list.push_back(pair);}
{SYMBOL}     {due pair(SYMBOL,yytext);list.push_back(pair);}
{LPR}        {due pair(LPR,yytext);list.push_back(pair);}
{RPR}        {due pair(RPR,yytext);list.push_back(pair);}
{SEMICOLON}  {due pair(SEMICOLON,yytext);list.push_back(pair);}
{WHITE}      {due pair(WHITE,yytext);list.push_back(pair);}
{TAB}		 {}
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
			else if(list[incounter].getNToken() == CP)
			{
				if(result.back() == "\t")
				{
					result.pop_back();
				}
				--tabCounter;
				result.push_back(list[incounter].getText());
			}
			else if(list[incounter].getNToken() == WHITE)
			{
				if(result.back() != "\t")
				{
					result.push_back(list[incounter].getText());
				}
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




