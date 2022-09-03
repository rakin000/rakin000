#include <iostream>
using namespace std;

int main(int argc,char **argv){
    if(argc<2){
        cout<<"aborting\n";
        return -1;
    }
    int t = stoi( string(argv[1]) );
    cout<<t<<endl;
    while(t--){
        int n = max(3,rand()%(20)+1); 
        cout<<n<<endl;
        for(int i=0;i<n;i++)
            if( rand()&1 ) cout<<"R";
            else cout<<"L";
        cout<<endl;
    }

}