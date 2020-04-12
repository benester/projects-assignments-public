#include <stdio.h>

char* text1 = "This is a string.";
char* text2 = "Yet another thing.";
int list1[sizeof(text1)];
int list2[sizeof(text2)];
int count = 0;


void copycodes(char* text, int list[])
{
    int i = 0;
    
    while(*(text + i) != 0)
    {
        list[i] = (int) *(text+i);
        i++;
        count++;    
    }
    
    /*for(int i = 0; i < 20; i++)
    {
        if( (int) (*(text + i)) != 0)
        {
            list[i] = (int) *(text+i);
            count++;
        }
        else
            break;
    }*/
}

void work()
{
    copycodes(text1, list1);
    copycodes(text2, list2);
}

void printlist(const int* lst){
  printf("ASCII codes and corresponding characters.\n");
  while(*lst != 0){
    printf("0x%03X '%c' ", *lst, (char)*lst);
    lst++;
  }
  printf("\n");
}

void endian_proof(const char* c){
  printf("\nEndian experiment: 0x%02x,0x%02x,0x%02x,0x%02x\n", 
         (int)*c,(int)*(c+1), (int)*(c+2), (int)*(c+3));
  
}

int main(void){
  work();

  printf("\nlist1: ");
  printlist(list1);
  printf("\nlist2: ");
  printlist(list2);
  printf("\nCount = %d\n", count);

  endian_proof((char*) &count);
}