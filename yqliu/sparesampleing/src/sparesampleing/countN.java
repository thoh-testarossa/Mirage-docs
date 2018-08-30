package sparesampleing;
import java.util.Random;

// 将分层结果抽样返回          N1/N


public class countN {
		int i,j,k;

		double[] fanh(int[][] arr,int[] yuan,int num,int qu,int wei,int yb,int[] duan,int[] tzh){	
			int len=arr.length ;
			suiji mu=new suiji();
			double[][] zh=mu.etos(arr, qu, wei);//十进制
			int tj;
			Random ne=new Random();
			double[] minj=new double[len];       //目标值
			double[] ybl=new double[qu];
			int[] ybl1=new int[qu];              //各区间样本量
			for(i=0;i<len;i++){
				double sum=0;
				for(j=1;j<qu+1;j++){
					sum=sum+zh[i][j];
				}
				int te=yb;
				for(j=0;j<qu;j++){
					ybl[j]=zh[i][j+1]*yb/sum;
					ybl1[j]=(int)ybl[j]/1;
					ybl[j]=ybl[j]-ybl1[j];
					te=te-ybl1[j];
				}
				for(j=0;j<te;j++){
					double z=ybl[0];
					int kk=0;
					for(k=1;k<qu;k++){
						if(ybl[k]>z){
							z=ybl[k];
							kk=k;
						}
					}
					ybl1[kk]=ybl1[kk]+1;         //保证样本总数一定
					ybl[kk]=0;
				}
				System.out.println("各层区间样本量为:");
				for(j=0;j<qu;j++){
					if(j==(qu-1)){
						System.out.println(ybl1[j]);
					}else{
						System.out.print(ybl1[j]+"\t");
					}
				}
				
				
				int tzb=duan[0];           //原始数据区间下标
				int tzhi=tzh[0];           //N
				int tyb=ybl1[0];           //n
				double sum1=0;            //局部和
				double ave=0;
				tj=0;                  //层
				double[][] cengs=new double[(int)zh[i][0]][2];  //(n,N)

				for(j=1;j<qu;j++){
					if(arr[i][j]==0){
						tyb=tyb+ybl1[j];
						tzhi=tzhi+tzh[j];
						if(j==(qu-1)){
							cengs[tj][0]=tyb;
							cengs[tj][1]=tzhi;
							for(k=0;k<cengs[tj][0];k++){
								int zk=yuan[ne.nextInt(duan[j]-tzb)+tzb];
								sum1=sum1+zk;
								System.out.println(zk+"\t");
								if(k==(cengs[tj][0]-1)){
									ave=ave+(sum1*cengs[tj][1])/num;
									sum1=0;
								}
							}
						}			
					}else{
						cengs[tj][0]=tyb;
						tyb=ybl1[j];
						cengs[tj][1]=tzhi;
						tzhi=tzh[j];
						for(k=0;k<cengs[tj][0];k++){
							int zk=yuan[ne.nextInt(duan[j]-tzb)+tzb];
							sum1=sum1+zk;
							System.out.println(zk+"\t");
							if(k==(cengs[tj][0]-1)){
								ave=ave+(sum1*cengs[tj][1])/num;
								sum1=0;
							}
						}
						tzb=duan[j];
						tj=tj+1;
						if(j==(qu-1)){
							cengs[tj][0]=tyb;
							cengs[tj][1]=tzhi;						
							for(k=0;k<cengs[tj][0];k++){
								int zk=yuan[ne.nextInt(duan[j+1]-tzb)+tzb];
								sum1=sum1+zk;
								System.out.println(zk+"\t");
								if(k==(cengs[tj][0]-1)){
									ave=ave+(sum1*cengs[tj][1])/num;
									sum1=0;
								}
							}
						}
					}
					
				}
				minj[i]=ave;//目标函数
				System.out.println("   "+minj[i]);
			}	
			
			return minj;
		}
	

}
