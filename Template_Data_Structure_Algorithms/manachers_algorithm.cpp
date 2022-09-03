/// manacher's algorithm

#include <iostream>
#include <vector>
using namespace std;
vector<int>d1,d2;

void manacher(string s){
    for(int i=0,l=0,r=-1;i<s.size();i++){
        int k=(i>r)?1:min(r-i+1,d1[r-(i-l)]) ;
        while(i-k>=0 && i+k<s.size() && s[i-k]==s[i+k] )
            ++k;
        
        d1[i]=k--;
        if(i+k>r){
            r=i+k;
            l=i-k;
        }
    }

    for(int i=0,l=0,r=-1;i<s.size();i++){
        int k=(i>r)?0:min(d2[r-i+l+1],r-i+1) ;
        while(i-k-1>=0 && i+k<s.size() && s[i-k-1]==s[i+k])
            ++k;
        d2[i]=k--;
        if(i+k>r){
            l=i-k-1;
            r=i+k;
        }
    }
}
string _longest_from_zero(string &s){
    int n=s.size();
    int m1=-1,m2=-1,i1=-1,i2=-1,length,idx;

    for(int i=0,l=0,r=-1;i<n;i++){
        int len=(i>r)?1:min(r-i+1,d1[l-i+r]);
        while(i-len>=0 && i+len<n && s[i-len]==s[i+len])
            ++len;
        d1[i]=len--;
        if(i-len==0 && d1[i]>=m1){
            m1=d1[i];
            i1=i-len;
        }
        if(i+len>r){
            r=i+len;
            l=i-len;
        }
    }

    for(int i=0,l=0,r=-1;i<n;i++){
        int len=(i>r)?0:min(r-i+1,d2[l+r-i+1]);
        while( i-len-1>=0 && i+len<n && s[i-len-1]==s[i+len])
            ++len;
        d2[i]=len--;
        if(i-len-1==0 && d2[i]>=m2){
            m2=d2[i];
            i2=i-len-1;
        }
        if(i+len>r){
            r=i+len;
            l=i-len-1;
        }
    }

    idx=0;
  //  cout << m1 << " " << m2 <<endl;
    if(2*m1-1>2*m2) 
        length=2*m1-1;
    else length=2*m2;

    return s.substr(0,length);
}

main(){
    vector<int> dd1,dd2;
    string s;
    cin >> s;

    d1.resize(s.size());
    d2.resize(s.size());
    dd1.resize(s.size());
    dd2.resize(s.size());
    int n=s.size();
    for(int i=0;i<n;i++){
        dd1[i]=1;
        while(i-dd1[i]>=0 && i+dd1[i]<n && s[i-dd1[i]]==s[i+dd1[i]])
            ++dd1[i] ;

        dd2[i]=0;
        while(i-dd2[i]-1>=0 && i+dd2[i]<n && s[i-dd2[i]-1]==s[i+dd2[i]])
            ++dd2[i];

    }
    for(int i=0;i<n;i++) cout << dd1[i] << " " ;
    cout << endl;
    for(int i=0;i<n;i++) cout << dd2[i] << " " ;
    cout << endl;
    
    manacher(s);
    for(int i=0;i<n;i++) cout << d1[i] << " " ; cout << endl;
    for(int i=0;i<n;i++) cout << d2[i] << " " ; cout << endl;
    return 0;
}