package sparesampleing;

import java.util.Random;

public class suiji {                        //��ʼ����Ⱥ+������ת��
int i,j,k;
// ��ʼ����Ⱥ
public int[][] chu(int row,int qu,int wei ){        //row Ϊ���� qu Ϊ������  weiΪ��������λ��
	int col=qu+qu*wei;
	int[][] zq=new int[row][col];
	Random ne=new Random();
	for(i=0;i<row;i++){
		for(j=0;j<col;j++){
			zq[i][j]=ne.nextInt(2);         //���ɶ�������
		}
	}
	
	return zq;
}

//������ת��
public double[][] etos(int[][] arr,int qu,int wei){        //������ת��ʮ����
	int row=arr.length;
	int col=arr[0].length ;
	double[][] jie=new double[row][qu+1];
	for(i=0;i<row;i++){
		for(j=0;j<(qu+1);j++){
			if(j==0){
				double zh=0;
				for(k=1;k<qu;k++){       //����һ����ȥ
					zh=zh+arr[i][k];
				}
				jie[i][j]=zh+1;            //��һ�б�ʾ�ֳ��˼���
			}else{
				double zh=0;
				for(k=0;k<wei;k++){
					zh=zh+arr[i][qu*j+k]*Math.pow(2, k);     //�ӵڶ��п�ʼ��ʾ����������
				}
				jie[i][j]=zh;
			}
		}
	}
	
	return jie;
}

}