package sparesampleing;
import java.util.Random;
//                    2018.8.27      spare+sample            ---����ǿ

public class sparesample {
	
	public static void main(String[] args) {
		// ����ԭʼ����
		double yc=0.8;               //�Ŵ�
		double jc=0.3;               //����
		double by=0.1;               //����
		double remain=0.3;           //����
		int k=1000;                  //ѭ������
		int row=20;                 //����
		int num=100;                      //ԭʼ������
		int yb=30;                        //������
		int wei=5;                        //����������λ��
		double ae=0.9;                       //���Ŷ�
        int[] yuan=new int[num];
        int[] duan={0,20,40,50,95,100};   //����ֶ�
        int gs=duan.length-1;             //�������
        int[] tzh=new int[gs];            //������������
        int[] tfw=new int[gs*2];          //�����䷶Χ
        int[] zhi={50,50,40,90,10};      // �����Χ
        int[] fanw={1,61,201,501,100001};   // ֵ��Χ��С
        int i,j;
        Random ne=new Random();
        for(i=0;i<gs;i++){                // ����������ݸ�ʽ
        	tzh[i]=duan[i+1]-duan[i];
        	tfw[2*i]=fanw[i];
        	tfw[2*i+1]=fanw[i]+zhi[i]-1;
        	for(j=duan[i];j<duan[i+1];j++){
        	yuan[j]=ne.nextInt(zhi[i])+fanw[i];	
        	}
        }     
        double pj;                        //��ʵƽ��ֵ
        int sum=0;
        System.out.println("ԭʼ����Ϊ:");
        for(i=0;i<num;i++){
        	sum=sum+yuan[i];
        	if((i+1)%10==0){
        	System.out.println(yuan[i]);
        	}else{
        		System.out.print(yuan[i]+"\t");
        	}
        }
	    pj=sum/yuan.length;
	    sum=0;
	    int[] simp=new int[yb];
	    System.out.println("���������Ϊ��");
	    for(i=0;i<yb;i++){
	    	simp[i]=yuan[ne.nextInt(num)];
	    	sum=sum+simp[i];
	    	System.out.print(simp[i]+"\t");
	    	if((i+1)%10==0){
	    		System.out.println("\t");
	    	}
	    }
	    double pj1=sum/yb;               //���������ƽ��ֵ
	    
	    //  �Ŵ��㷨ʵ��
	    suiji sj=new suiji();
	    shiy sy=new shiy();
	    ycby ycb=new ycby();
	    int[][] arr=sj.chu(row, gs, wei);
	    // ѭ��
	    for(i=0;i<k;i++){
	    	arr=ycb.biany(arr, yc, remain, jc, by);
	    	arr=sy.sy(arr, gs, wei, yb, tzh, tfw, ae, num);
	    }
	    
	    // ���
	    double[][] jie=sj.etos(arr, gs, wei);    
	    count ct=new count();
	    double[] jg=ct.fanh(arr, yuan, num, gs, wei, yb, duan, tzh);
	    /*
	    countN ctN=new countN();
	    double[] jgN=ctN.fanh(arr, yuan, num, gs, wei, yb, duan, tzh);
	    */

	    System.out.print("����Ϊ�� "+jie[0][0]+"\t"+"�ֲ㻮��Ϊ��");

	   
	    	for(j=0;j<gs ;j++){
	    		if(j==0){
	    			System.out.print(arr[0][j]+"\t");
	    		}else if(j==(gs-1)){
	    			System.out.println(arr[0][j]);
	    		}else{
	    			System.out.print(arr[0][j]+"\t");
	    		}
	    	}
	    System.out.println("ԭʼ����ƽ��ֵΪ�� "+pj);
	    System.out.println("�򵥳���ƽ��ֵΪ�� "+pj1);
	    System.out.println("�򵥳���������Ϊ�� "+(Math.abs(pj-pj1)/pj));
	    System.out.println("�ֲ�������Ϊ��"+jg[0]);
	    System.out.println("�ֲ����������Ϊ�� "+(Math.abs(pj-jg[0])/pj));
	    /*
	    System.out.println("��һ��������ʽ�� ");
	    System.out.println("�ֲ�������Ϊ��"+jgN[0]);
	    System.out.println("�ֲ����������Ϊ�� "+(Math.abs(pj-jgN[0])/pj));
	    */
	}
}
