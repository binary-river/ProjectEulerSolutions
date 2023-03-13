/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>
#include <string.h>


int printIntroduction();

int main() {

    if( printIntroduction() ) return 1;

    return 0;
}

int printIntroduction()
{
    typedef struct{
        char   name     [20+1];
        int    age            ;
        char*  marriage       ;
        char*  from           ;
        char*  like     [  10];
        char*  dislike  [  10];

        char*  projects [  10];
    } StPersonalInformation;

    StPersonalInformation ltPi;
    memset(&ltPi, 0x00, sizeof(StPersonalInformation));

    /* Set Name */
    memcpy(ltPi.name, "이진기", sizeof("이진기"));

    /* Set Age  */
    ltPi.age = 34;

    /* Set Marriage */
    ltPi.marriage = "N (잘하면 Y로 변경 가능)";

    /* Set From */
    ltPi.from = "메타넷디지털";

    /* Set like */
    ltPi.like[0] = "돈";
    ltPi.like[1] = "새로운 거";
    ltPi.like[2] = "규격, 표준, 체계";

    /* Set dislike */
    ltPi.dislike[0] = "같은 일";
    ltPi.dislike[1] = "했던 거";
    ltPi.dislike[2] = "비효율";

    /* Set Projects */
    ltPi.projects[0] = "우체국보험(2016 ~ 2023)";


    return 0;
}
