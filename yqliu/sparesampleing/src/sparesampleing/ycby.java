package sparesampleing;

import java.util.Random;

// �Ŵ�����(�Ŵ������棬����)
public class ycby {
int i,j,k;
public int[][] biany(int[][] arr,double yc,double remain,double jc,double by){	
	int row=arr.length;
	int col=arr[0].length ;
	int[][] bi=arr;
	int yc1=(int)Math.ceil(yc*row);
	double jc1=jc*col;
	double by1=by*col;
	int remain1=(int)Math.ceil(remain*row);; //������
	Random ne=new Random();
	//�Ŵ�
	for(i=remain1;i<yc1;i++){
		for(j=0;j<col;j++){
			if(by1/2>ne.nextInt(col)){         //����
				bi[i][j]=1-bi[i][j]; 
			}
			if(i%2==1){                      //����
				if(jc1>ne.nextInt(col)){
					k=arr[i-remain1][j];
					bi[i][j]=arr[i-1-remain1][j];
					bi[i-1][j]=k;
				}
			}
		}
		
	}
	
	for(i=yc1;i<row;i++){
		for(j=0;j<col;j++){
			if(by1>ne.nextInt(col)){         //��ֵ����
				bi[i][j]=1-arr[i-yc1][j]; 
			}else{
				bi[i][j]=arr[i-yc1][j];
			}
		}
	}
	
	return bi;
}
}
