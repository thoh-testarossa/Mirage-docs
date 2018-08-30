package sparesampleing;


//适应性函数+排序（优秀者在前）
public class shiy {
	int i,j,k;
	public int[][] sy(int[][] arr,int qu,int wei,int yb,int[] tzh,int[] tfw,double ae,int num){
		int len=arr.length ;
		int high=arr[0].length;
		int[][] bian=arr;
		int[] med=new int[high];
		suiji mu=new suiji();
		double[][] zh=mu.etos(arr, qu, wei);//十进制
		int tj;
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
			
			int tzhi=tzh[0];           //N
			int txia=tfw[0];           //b-a
			int tyb=ybl1[0];           //n
			double sum1=0;            //局部和
			tj=0;                  //层
			double[][] cengs=new double[(int)zh[i][0]][4];  //(n,b-a,N,e)
			double jce=0;
/*
			System.out.println((int)zh[i][0]);
			System.out.println("t "+cengs.length);
*/
			
			for(j=1;j<qu;j++){
				if(arr[i][j]==0){
					tyb=tyb+ybl1[j];
					tzhi=tzhi+tzh[j];
					if(j==(qu-1)){
						cengs[tj][1]=tfw[j*2+1]-txia;
						cengs[tj][0]=tyb;
						cengs[tj][2]=tzhi;
						cengs[tj][3]=cengs[tj][1]*Math.pow(1/(2*cengs[tj][0])*Math.log(2/(1-ae)), 0.5);
						sum1=sum1+cengs[tj][2]*cengs[tj][3];
					}			
				}else{
					cengs[tj][1]=tfw[j*2-1]-txia;
					txia=tfw[j*2];
					cengs[tj][0]=tyb;
					tyb=ybl1[j];
					cengs[tj][2]=tzhi;
					tzhi=tzh[j];
					// 目标函数
					cengs[tj][3]=cengs[tj][1]*Math.pow(1/(2*cengs[tj][0])*Math.log(2/(1-ae)), 0.5);
					sum1=sum1+cengs[tj][2]*cengs[tj][3];
					tj=tj+1;
					if(j==(qu-1)){
						cengs[tj][1]=tfw[j*2+1]-txia;
						cengs[tj][0]=tyb;
						cengs[tj][2]=tzhi;
						cengs[tj][3]=cengs[tj][1]*Math.pow(1/(2*cengs[tj][0])*Math.log(2/(1-ae)), 0.5);
						sum1=sum1+cengs[tj][2]*cengs[tj][3];
					}
				}
				
			}
			minj[i]=sum1/num;//目标函数
			//System.out.println("   "+minj[i]);
		}	
		
		for(i=0;i<len;i++){              //冒泡排序
			for(j=len-1;j>i;j--){
				if(minj[j]<minj[j-1]){
					double z=minj[j];
					minj[j]=minj[j-1];
					minj[j-1]=z;
					System.arraycopy(bian[j], 0, med,0, high);
					System.arraycopy(bian[j-1], 0, bian[j],0, high);
					System.arraycopy(med, 0, bian[j-1],0, high);
				}
			}
		}
		
		return bian;
	}
}
