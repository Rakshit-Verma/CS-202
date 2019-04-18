brackets      (\ )*(\().*(\))
valid_var     [\ ]*[\*]*[a-z][a-z0-9]*
var_end       ([\ \t]*((\[(.*)\])?|(=({brackets}|[^(,;\n)]*)*)?))
invalid_var   [\ ]*[\*]*[0-9]*[a-zA-Z0-9]+
inline_comment  "//"
comment_start       "/*"
type_name     ((char(\*)*\ )|(int(\*)*\ )|(double(\*)*\ )|(long(\*)*\ )|(float(\*)*\ )|(void(\*)*\ ))
valid_function_name [\ ]*[a-z_)]+[\(]
invalid_function_name [a-z_]*[^(a-z_\ \t\n;=)]+[^(\ \t\n;\(\))]*[\(]
func_end      ")"
comma           [\ ]*[,][\ ]*
function_declaration    ")"(\ )*(;)(\ )*

%{
    #include <stdio.h>
    #include <stdlib.h>
    int line_num = 1;
    int comment;
    int error;
    int variable;
    int function;
    int multiple;

    void reset()
    {
        BEGIN 0;
        variable = 0;
        function = 0;
        multiple = 0;
    }

    void commentcheck()
    {
        if(comment == 0 && variable == 1 && multiple == 0)
        {
            error = 1;
            printf("Line %d: Variable not documented\n", line_num);
        }
        else if(comment == 0 && function == 1 && multiple == 0)
        {
            error = 1;
            printf("Line %d: Function not documented\n", line_num);
        }
        variable = 0;
        comment = 0;
    }
%}

%x TYPE
%x COMMENT
%x INCLUDE
%x INLINE

%%
"#include"                      {BEGIN INCLUDE;}
<INCLUDE>">"                    {BEGIN 0;}
<INCLUDE>.                      ;

"for("                          ;
"while("                        ;
"if("                           ;

{type_name}                     {BEGIN TYPE;}
<TYPE>{valid_var}              {variable = 1;}
<TYPE>{var_end}                ;
<TYPE>{invalid_var}            {error = 1; printf("Line %d: Invalid variable name\n",line_num); variable = 1;}
<TYPE>";"                      {commentcheck(); multiple = 0; comment = 0;}

<TYPE>{func_end}             {if(function == 1) {reset();}}
<TYPE>{function_declaration}   {multiple = 1; function = 0;}
<TYPE>"\n"                     {line_num++; reset();}

<TYPE>{comma}                  {if(function == 1) BEGIN 0;}
<TYPE>.                        ;
.                               ;
"\n"                            line_num++;

{comment_start}                 {BEGIN COMMENT; comment = 1;}
<COMMENT>.                      ;
<COMMENT>"*/"                   {BEGIN 0;}
<COMMENT>"\n"                   line_num++;

{inline_comment}                {comment = 1; BEGIN INLINE;}
<INLINE>.                       ;
<INLINE>"\n"                    {BEGIN 0; line_num++;}

<<EOF>>                         {if(error == 0){printf("Program meets the coding conventions!\n");} yyterminate();}

%%

int yywrap(void)
{
    return 1;
}

int main(int argc, char **argv)
{
    if(argc > 1)
    {
        yyin = fopen(argv[1], "r");
        if(yyin == NULL)
        {
            printf("File Not Found\n");
            return 0;
        }

        yylex();
        fclose(yyin);
    }
    else
        printf("No File Specified!\n");
}