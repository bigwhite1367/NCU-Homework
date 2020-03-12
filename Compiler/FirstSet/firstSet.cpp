#include<stdlib.h>
#include<stdio.h>
#include<string>
#include<vector>
#include<map>
#include<iostream>
#include<algorithm>

using namespace std;
void findFirst(string tmp, string current);
void findFirstWithEpsilon(string tmp, string current);
void removeDuplicate(string& target);
void checkS(string tmp,string& target);
void production4(string tmp,string current);
vector<string> rules;
string noTerminal;
map<string,string> sets;
string startSymbol;
int main(int argc, char *argv[])
{
	
	string s;
	while(getline(cin, s)) //read in and generate production rule
	{
		if(s == "END_OF_GRAMMAR")
		{
			break;
		}
		sets[s.substr(0, 1)] = "";
		noTerminal.push_back(s[0]);
		string nt;
		nt.push_back(s[0]);
		for(uint ptr = 2 ; ptr < s.size() ; ptr++)
		{
			if(s.substr(ptr, 1) == "|")
			{
				rules.push_back(nt);
				nt.clear();
				nt.push_back(s[0]);
			}
			else if(ptr == s.size() -1 )
			{
				nt.push_back(s[ptr]);
				rules.push_back(nt);
				nt.clear();
			}
			else
			{
				nt.push_back(s[ptr]);
			}
		}
	}

	sort(noTerminal.begin(),noTerminal.end());
	startSymbol = noTerminal.substr(noTerminal.size()-1,1);
	
	for(uint counter = 0 ; counter<noTerminal.size(); counter++)
	{
		string tmp = noTerminal.substr(counter,1);
		findFirst(tmp, tmp);
		sort(sets[tmp].begin(), sets[tmp].end());
	}

	cout<<endl;

	for(uint counter = 0 ; counter<noTerminal.size(); counter++)
	{
		string tmp = noTerminal.substr(counter,1);
		findFirstWithEpsilon(tmp,tmp);
		sort(sets[tmp].begin(), sets[tmp].end());
	}


	for(uint counter = 0 ; counter<noTerminal.size(); counter++)
	{
		string tmp = noTerminal.substr(counter,1);
		//cout<<tmp<<" "<<sets[tmp]<<endl;
		removeDuplicate(sets[tmp]);
		//cout<<tmp<<" "<<sets[tmp]<<endl;
		//cout<<startSymbol<<endl;
		if(tmp == startSymbol)
		{
			checkS(tmp,sets[tmp]);
			
		}
		cout<<tmp<<" "<<sets[tmp]<<endl;
	}

	cout<<"END_OF_FIRST"<<endl;
}



void findFirst(string tmp, string current)
{
	for(uint incounter = 0 ; incounter<rules.size() ; incounter++)
	{
		if(tmp == rules[incounter].substr(0,1))
		{
			if(isupper(rules[incounter][1]))
			{
				findFirst(rules[incounter].substr(1,1), current);
			}
			else
			{
				sets[current].push_back(rules[incounter][1]);
			}
		}
	}
}

void findFirstWithEpsilon(string tmp, string current)
{
	for(uint incounter = 0 ; incounter<rules.size() ; incounter++)
	{

		if(tmp == rules[incounter].substr(0,1)) //開頭一樣
		{
			if(isupper(rules[incounter][1])) //第一個為NonT
			{
				findFirstWithEpsilon(rules[incounter].substr(1,1), current);
			}
			for(uint ptr = 1; ptr<rules[incounter].size()-1; ptr++)
			{
				if(isupper(rules[incounter][ptr]) && sets[rules[incounter].substr(ptr,1)].substr(0,1) == ";") //NonT 有;
				{
					if(isupper(rules[incounter][ptr+1])) //下一個為NonT
					{
						string next = rules[incounter].substr(ptr+1,1);
						string back = sets[next];
						findFirstWithEpsilon(rules[incounter].substr(ptr+1,1), current);
						sets[current].append(back);
					}
					else if(rules[incounter].substr(ptr+1,1) == ";")
					{
						if(ptr+1 < rules[incounter].size()-1)
						{
							if(isupper(rules[incounter][ptr+2])) //下一個為NonT
							{
								string next = rules[incounter].substr(ptr+2,1);
								string back = sets[next];
								findFirstWithEpsilon(rules[incounter].substr(ptr+2,1), current);
								sets[current].append(back);
							}
						}
					}
					else
					{
						sets[current].push_back(rules[incounter][ptr+1]);
					}
				}
				else
				{
					break;
				}
			}
		}
	}
}


void removeDuplicate(string& target)
{
	uint counter = 0;
	while(counter < target.size() -1)
	{ 
		if(target[counter] == target[counter+1])
		{
			target.erase(target.begin()+counter+1);
		}
		else
		{
			counter++;
		}
	}
}


void checkS(string tmp,string& target)
{
	bool dominateFlag = true;
	bool removeFlag = false;
	for(uint incounter = 0 ; incounter<rules.size() ; incounter++)
	{
		//cout<<tmp<<" "<<rules[incounter]<<endl;
		if(tmp == rules[incounter].substr(0,1))
		{
			if(sets[rules[incounter].substr(rules[incounter].size()-1,1)].substr(0,1) == ";" || rules[incounter].substr(rules[incounter].size()-1,1) == ";")//set of the last element has ;
			{
				break;
			}
			if((!(isupper(rules[incounter][1])) && rules[incounter].substr(1,1) != ";")) //T not ;
			{
				removeFlag =true;
			}
			else if((!(isupper(rules[incounter][1])) && rules[incounter].substr(1,1) == ";"))//T but ;
			{
				dominateFlag = false;
			}
			else if(isupper(rules[incounter][1]) && sets[rules[incounter].substr(1,1)].substr(0,1) == ";") //NonT 有;
			{
				if(rules[incounter].size() > 2)
				{
					for(uint ptr = 1; ptr<rules[incounter].size()-1; ptr++)
					{
						if(rules[incounter].substr(ptr+1,1) == "$")  //if 下一個是＄
						{
							removeFlag = true;
						}
						else if(isupper(rules[incounter][ptr+1]) && sets[rules[incounter].substr(ptr+1,1)].substr(0,1) == ";") //next NonT 有;
						{
							cout<<rules[incounter][ptr+1]<<endl;
							continue;
						}
						else if(!isupper(rules[incounter][ptr+1]))
						{
							removeFlag = true;
						}
					}
				}
				else // S A
				{

					//do nothing
				}
			}
		}
	
	}
	for(uint incounter = 0 ; incounter<rules.size() ; incounter++)
	{
		if(tmp == rules[incounter].substr(0,1))
		{

		}
	}

	if(removeFlag && dominateFlag)
	{
		for(uint incounter = 0 ; incounter < sets[tmp].size() ; incounter++)
		{
			if(sets[tmp].substr(incounter,1) == ";")
			{
				sets[tmp].erase(sets[tmp].begin()+incounter);
				break;
			}
		}
	}
}


