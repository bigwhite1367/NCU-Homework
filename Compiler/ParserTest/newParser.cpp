#include<iostream>
#include<string>
#include<vector>
#include<map>
#include<regex>
#define SEMICOLON 1
#define STRLIT 2
#define ID 3
#define LBR 4
#define RBR 5
#define DOT 6
using namespace std;
bool invalidFlag = false; //因為預設合法所以invalid flag為false 
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
std::string result;
void generateToken(int* s_ptr, string s, vector<due>* list);
void semicolonToken(int* s_ptr, string s,vector<due>* list);
void idToken(int* s_ptr, string s,vector<due>* list);
void strlitToken(int* s_ptr, string s,vector<due>* list);
void lbrToken(int* s_ptr, string s,vector<due>* list);
void rbrToken(int* s_ptr, string s,vector<due>* list);
void dotToken(int* s_ptr, string s,vector<due>* list);
void match();
void program();
void stmts();
void exp();
void stmt();
void primary();
void primary_tail();


int main()
{
	m[1] = "SEMICOLON";
	m[2] = "STRLIT";
	m[3] = "ID";
	m[4] = "LBR";
	m[5] = "RBR";
	m[6] = "DOT";
	string s;
	string line;
	while(getline(cin,line))
	{
		s += line;
	}

	int s_ptr = 0;
	//vector<due> list;
	generateToken(&s_ptr, s, &list);
	if(invalidFlag == false)
	{
		program();
	}
	else
	{
		cout<<"Invalid"<<endl;
	}
	return 0;

}

void generateToken(int* s_ptr, string s, vector<due>* list)
{
	while(*s_ptr < s.size())
	{
		if(invalidFlag == true)
		{
			break;
		}
	
		if(std::regex_match( s.substr( (unsigned long)*s_ptr, 1), regex("\\;")))//SEMEICOLON
		{	
			semicolonToken(s_ptr, s, list);
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("\"")))//STRLIT
		{
			strlitToken(s_ptr, s, list);
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("[A-Za-z_][A-Za-z0-9_]*")))//ID
		{
			idToken(s_ptr, s, list);
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("[ \f\n\r\t\v]")))
		{
			(*s_ptr)+=1;
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("\\(")))
		{
			lbrToken(s_ptr, s, list);
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("\\)")))
		{
			rbrToken(s_ptr, s, list);
		}
		else if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("\\.")))
		{
			dotToken(s_ptr, s, list);
		}	
		else 
		{
			invalidFlag = true;
			break;
		}
	}
}

void semicolonToken(int* s_ptr, string s, vector<due>* list)
{
	string str;
	str.append(s.substr( (unsigned long)*s_ptr, 1));
	*s_ptr+=1;

	if( std::regex_match( str, regex("\\;")))
	{
		due pair(SEMICOLON,str);
		list->push_back(pair);
	}
	else
	{
		invalidFlag = true;
	}
	
}

void idToken(int* s_ptr, string s,vector<due>* list)
{
	string str;
	while(*s_ptr<s.size())
	{
		if(std::regex_match(s.substr( (unsigned long)*s_ptr, 1), regex("[A-Za-z0-9_]")))
		{
			str.append(s.substr( (unsigned long)*s_ptr, 1));
			*s_ptr+=1;
		}
		else
		{
			break;
		}
	}
	if( std::regex_match( str, regex("[A-Za-z_][A-Za-z0-9_]*")))
	{
		due pair(ID,str);
		list->push_back(pair);
	}
	else
	{
		invalidFlag = true;
	}
}

void strlitToken(int* s_ptr, string s,vector<due>* list)
{
	string str;
	int strlitEnd = *s_ptr;
	for(int counter= (*s_ptr)+1 ; counter<s.size(); counter++)
	{
			
		if(std::regex_match( s.substr( (unsigned long)counter, 1), regex("\"")))
		{
			strlitEnd = counter;
			break;
		}
	}

	if(strlitEnd != *s_ptr)
	{
		str.append(s.substr( (unsigned long)*s_ptr, 1));
		*s_ptr+=1;
		while(*s_ptr<strlitEnd)
		{
			str.append(s.substr( (unsigned long)*s_ptr, 1));
			*s_ptr+=1;
			
		}
		str.append(s.substr( (unsigned long)*s_ptr, 1));
		*s_ptr+=1;
	}
	else
	{
		invalidFlag = true;
	}
	due pair(STRLIT,str);
	list->push_back(pair);

}

void lbrToken(int* s_ptr, string s,vector<due>* list)
{
	string str;
	str.append(s.substr( (unsigned long)*s_ptr, 1));
	*s_ptr+=1;

	if( std::regex_match( str, regex("\\(")))
	{
		due pair(LBR,str);
		list->push_back(pair);
	}
	else
	{
		invalidFlag = true;
	}
}

void rbrToken(int* s_ptr, string s,vector<due>* list)
{
	string str;
	str.append(s.substr( (unsigned long)*s_ptr, 1));
	*s_ptr+=1;

	if( std::regex_match( str, regex("\\)")))
	{
		due pair(RBR,str);
		list->push_back(pair);
	}
	else
	{
		invalidFlag = true;
	}
}

void dotToken(int* s_ptr, string s,vector<due>* list)
{
	string str;
	str.append(s.substr( (unsigned long)*s_ptr, 1));
	*s_ptr+=1;
	if( std::regex_match( str, regex("\\.")))
	{
		due pair(DOT,str);
		list->push_back(pair);
	}
	else
	{
		invalidFlag = true;
	}
	
}

void MATCH(int t,std::string str)
{
	result+=m[t];
	result+=" ";
	result+=list.front().getText();
	result+="\n";
	//std::cout<<m[t]<<" "<<list.front().getText()<<std::endl;
	list.erase(list.begin());
}
void program()
{
	stmts();
	if(invalidFlag == false)
	{
		//cout<<result;
		cout<<"Valid"<<endl;
	}
	else
	{
		cout<<"Invalid"<<endl;
	}
}

void stmts()
{
	if(!list.empty())
	{
		if(list.front().getNToken() == ID || list.front().getNToken() == STRLIT)
		{
			stmt();
			stmts();
		}
		else
		{
	
		}
	}
}

void stmt()
{
	exp();
	if(!list.empty())
	{
		if(list.front().getNToken() != SEMICOLON)
		{
			invalidFlag = true;
		}
		else
		{
			MATCH(list.front().getNToken(), list.front().getText());
		}
	}
	else
	{
		invalidFlag = true;
	}
}

void exp()
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

		}
	}
}
void primary()
{
	MATCH(list.front().getNToken(), list.front().getText());
	primary_tail();
}

void primary_tail()
{
	if(list.front().getNToken() == DOT)
	{
		MATCH(list.front().getNToken(), list.front().getText());
		if(list.front().getNToken() !=ID)
		{
			invalidFlag = true;
		}
		MATCH(list.front().getNToken(), list.front().getText());
		primary_tail();
	}
	else if(list.front().getNToken() == LBR)
	{
		MATCH(list.front().getNToken(), list.front().getText());
		if(list.front().getNToken() == ID || list.front().getNToken() == STRLIT)
		{
			exp();
		}
		else
		{
			invalidFlag = true;
		}
		if(list.front().getNToken() != RBR)
		{
			invalidFlag = true;
		}
		MATCH(list.front().getNToken(), list.front().getText());
		primary_tail();	
	}
	else
	{
		
	}

}