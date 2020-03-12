/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     ID = 258,
     NUMBER = 259,
     BOOL = 260,
     PRINTNUM = 261,
     PRINTBOOL = 262,
     ADD = 263,
     SUB = 264,
     MUL = 265,
     DIV = 266,
     MOD = 267,
     BIG = 268,
     SMALL = 269,
     EQU = 270,
     AND = 271,
     OR = 272,
     NOT = 273,
     DEF = 274,
     IF = 275,
     FUN = 276
   };
#endif
/* Tokens.  */
#define ID 258
#define NUMBER 259
#define BOOL 260
#define PRINTNUM 261
#define PRINTBOOL 262
#define ADD 263
#define SUB 264
#define MUL 265
#define DIV 266
#define MOD 267
#define BIG 268
#define SMALL 269
#define EQU 270
#define AND 271
#define OR 272
#define NOT 273
#define DEF 274
#define IF 275
#define FUN 276




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 49 "test.y"
{
    int intval,boolval;
    char* strval;
    struct Node *ndval;
}
/* Line 1529 of yacc.c.  */
#line 97 "test.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

