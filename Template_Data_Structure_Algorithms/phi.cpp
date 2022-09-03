#include <iostream>
#include <vector>
using namespace std;
const int m=1e6;
vector<unsigned long long> phi(m+1);

void build_nlogn(){
	phi[0] = 0;
	phi[1] = 1;
	for(int i=2;i<=m;i++) phi[i]=i-1;
	for(int i=2;i<=m;i++){
		for(int j=i+i;j<=m;j+=i)
			phi[j] -= phi[i] ;
	}
}
void build_nloglogn(){
	for(int i=0;i<=m;i++) phi[i]=i;

	for(int i=2;i<=m;i++){
		if(phi[i]==i){
			for(int j=i;j<=m;j+=i)
				phi[j] -=phi[j]/i;
		}
	}
}

main(){
	ios_base::sync_with_stdio(false);
	build_nloglogn();
	//build_nlogn();
	for(int i=1;i<=m;i++){
		phi[i]=phi[i]*phi[i]+phi[i-1];
	}

	int T;cin>>T;
	for(int t=1;t<=T;t++){
		int a,b; cin >> a >> b;
		cout << "Case "<<t<<": "<< phi[b]-phi[a-1] << endl;
	}
		
}
     
