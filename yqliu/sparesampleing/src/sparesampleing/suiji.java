package sparesampleing;

import java.util.Random;

public class suiji {                        //初始化种群+二进制转换
int i,j,k;
// 初始化种群
public int[][] chu(int row,int qu,int wei ){        //row 为列数 qu 为区间数  wei为单个样本位数
	int col=qu+qu*wei;
	int[][] zq=new int[row][col];
	Random ne=new Random();
	for(i=0;i<row;i++){
		for(j=0;j<col;j++){
			zq[i][j]=ne.nextInt(2);         //生成二进制数
		}
	}
	
	return zq;
}

//二进制转换
public double[][] etos(int[][] arr,int qu,int wei){        //二进制转换十进制
	int row=arr.length;
	int col=arr[0].length ;
	double[][] jie=new double[row][qu+1];
	for(i=0;i<row;i++){
		for(j=0;j<(qu+1);j++){
			if(j==0){
				double zh=0;
				for(k=1;k<qu;k++){       //将第一个空去
					zh=zh+arr[i][k];
				}
				jie[i][j]=zh+1;            //第一列表示分成了几层
			}else{
				double zh=0;
				for(k=0;k<wei;k++){
					zh=zh+arr[i][qu*j+k]*Math.pow(2, k);     //从第二列开始表示各层样本量
				}
				jie[i][j]=zh;
			}
		}
	}
	
	return jie;
}

}