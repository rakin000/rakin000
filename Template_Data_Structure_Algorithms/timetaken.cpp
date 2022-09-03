#include <iostream>
#include <time.h>
using namespace std;
main(int argc,char** argv){
    clock_t start=clock();
    string command;
    for(int i=1;i<argc;i++)
        command+=string(argv[i])+" ";
    system(command.c_str());
    clock_t end=clock();
    double t=(double)(end-start)*(1000000.0/CLOCKS_PER_SEC) ;
    cout<<"Time: "<<t<<" ms"<<endl;
}