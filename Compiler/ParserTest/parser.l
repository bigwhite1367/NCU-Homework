%{
#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<iostream>
#include<vector>
#include<map>

#define $ 0
#define ID 1
#define STRLIT 2
#define LBR 3
#define RBR 4
#define DOT 5
#define WHITE 6
#define INVALID 7
void primary();
void primary_tail();
void stmt();
void stmts();
extern YY_BUFFER_STATE yy_scan_string(const char *str);
%}
ID       [A-Za-z_][A-Za-z0-9_]*
STRLIT   \"[^"]*\" 
LBR      \(
RBR      \)
DOT      \.
WHITE    [ \f\n\r\t\v]
%%
{STRLIT} { return STRLIT;}
{ID}     { return  ID;}
{LBR}    { return LBR;}
{RBR}    { return RBR;}
{DOT}    { return DOT;}
{WHITE}  { return WHITE;}
.        { return INVALID;}
%%
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
std::map<int,std::string> m;
std::vector<due> list;

int yywrap(){return(1);}
void MATCH(int t,std::string str)
{
	std::cout<<m[t]<<" "<<list.front().getText()<<std::endl;
	list.erase(list.begin());
}
void scan_string(const char* str)
{
    yy_switch_to_buffer(yy_scan_string(str));
}

void primary_tail()
{
	if(list.front().getNToken() == DOT)
	{
		MATCH(list.front().getNToken(), list.front().getText());
		MATCH(list.front().getNToken(), list.front().getText());
		primary_tail();
	}
	else if(list.front().getNToken() == LBR)
	{
		MATCH(list.front().getNToken(), list.front().getText());
		stmt();
		MATCH(list.front().getNToken(), list.front().getText());
		primary_tail();	
	}
	else
	{
		if(list.front().getNToken() == $)
		{
			//do nothing
		}
		else
		{
			//exit(1);
		}
	}

}

void primary()
{
	MATCH(list.front().getNToken(), list.front().getText());
	primary_tail();
}

void stmt()
{
	if(list.front().getNToken() == ID)
	{
		primary();
	}
	else
	{
		if(list.front().getNToken() == STRLIT)
		{
			MATCH(list.front().getNToken(), list.front().getText());
		}
		else
		{
			//exit(1);
		}
	}
}

void stmts()
{
	if(list.front().getNToken() == ID || list.front().getNToken() == STRLIT)
	{
		stmt();
		stmts();
	}
	else
	{
		if(list.front().getNToken() == $)
		{
			//do nothing
		}
		else
		{
			//exit(1);
		}
	}
}
int main(int argc, char *argv[]) {
	
	m[1] = "ID";
	m[2] = "STRLIT";
	m[3] = "LBR";
	m[4] = "RBR";
	m[5] = "DOT";
	std::string s;
	char* c;
	while(std::cin>>s)
	{
		int ntoken;
	    c = new char[s.length() + 1];	
	    strcpy(c, s.c_str());
	    scan_string(c);
	    
	    ntoken = yylex();
	    while(ntoken)
	    {
	    	std::string str(yytext); 
	    	due pair(ntoken, str);
	    	list.push_back(pair);
	    	ntoken = yylex();
	    }

	    for(int i=0; i<list.size();i++)
	    {
	    	if(list[i].getNToken() == INVALID)
	    	{
	    		printf("invalid input\n");
	    		list.clear();
	    		break;
	    	}
	    }

	    if(!list.empty())
	    {
	    	due pair($);
	    	list.push_back(pair);
	    	stmts();
	    }
		delete [] c;
		list.clear();
	}
	return(0);
}
