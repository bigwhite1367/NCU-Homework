%{
#include<iostream>
%}
%%
^[+\-]?([1-9])(\.[0-9]*)?([eE][+\-]?[0-9]+)?$   {std::cout<<yytext<<std::endl;}
^.*$ {printf("");}
%%
int main()

{
        yylex();
        return 0;
}
