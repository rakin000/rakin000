#include <iostream>
#include <algorithm>
#include <list>
using namespace std;

template<class t>void printlist(list<t> &l){
    for(auto it=l.begin();it!=l.end();++it)
        cout << *it<<" " ;
    cout << endl;
}
main(){
    list<int> l1,l2;

    for(int i=0;i<10;i++){
        l1.push_back(i*2);
        l2.push_front(i*3);
    }
    printlist(l1);
    printlist(l2);

    l2.pop_back();
    printlist(l2);
    l1.pop_front();
    printlist(l1);
}