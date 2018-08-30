package sparesampleing;
import java.util.Random;
//                    2018.8.27      spare+sample            ---刘永强

public class sparesample {
	
	public static void main(String[] args) {
		// 构造原始数据
		double yc=0.8;               //遗传
		double jc=0.3;               //交叉
		double by=0.1;               //变异
		double remain=0.3;           //生存
		int k=1000;                  //循环次数
		int row=20;                 //列数
		int num=100;                      //原始数据量
		int yb=30;                        //样本量
		int wei=5;                        //样本二进制位数
		double ae=0.9;                       //置信度
        int[] yuan=new int[num];
        int[] duan={0,20,40,50,95,100};   //区间分段
        int gs=duan.length-1;             //区间个数
        int[] tzh=new int[gs];            //区间内数据量
        int[] tfw=new int[gs*2];          //各区间范围
        int[] zhi={50,50,40,90,10};      // 随机范围
        int[] fanw={1,61,201,501,100001};   // 值范围大小
        int i,j;
        Random ne=new Random();
        for(i=0;i<gs;i++){                // 构造相关数据格式
        	tzh[i]=duan[i+1]-duan[i];
        	tfw[2*i]=fanw[i];
        	tfw[2*i+1]=fanw[i]+zhi[i]-1;
        	for(j=duan[i];j<duan[i+1];j++){
        	yuan[j]=ne.nextInt(zhi[i])+fanw[i];	
        	}
        }     
        double pj;                        //真实平均值
        int sum=0;
        System.out.println("原始数据为:");
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
	    System.out.println("简单随机样本为：");
	    for(i=0;i<yb;i++){
	    	simp[i]=yuan[ne.nextInt(num)];
	    	sum=sum+simp[i];
	    	System.out.print(simp[i]+"\t");
	    	if((i+1)%10==0){
	    		System.out.println("\t");
	    	}
	    }
	    double pj1=sum/yb;               //简单随机样本平均值
	    
	    //  遗传算法实现
	    suiji sj=new suiji();
	    shiy sy=new shiy();
	    ycby ycb=new ycby();
	    int[][] arr=sj.chu(row, gs, wei);
	    // 循环
	    for(i=0;i<k;i++){
	    	arr=ycb.biany(arr, yc, remain, jc, by);
	    	arr=sy.sy(arr, gs, wei, yb, tzh, tfw, ae, num);
	    }
	    
	    // 输出
	    double[][] jie=sj.etos(arr, gs, wei);    
	    count ct=new count();
	    double[] jg=ct.fanh(arr, yuan, num, gs, wei, yb, duan, tzh);
	    /*
	    countN ctN=new countN();
	    double[] jgN=ctN.fanh(arr, yuan, num, gs, wei, yb, duan, tzh);
	    */

	    System.out.print("层数为： "+jie[0][0]+"\t"+"分层划分为：");

	   
	    	for(j=0;j<gs ;j++){
	    		if(j==0){
	    			System.out.print(arr[0][j]+"\t");
	    		}else if(j==(gs-1)){
	    			System.out.println(arr[0][j]);
	    		}else{
	    			System.out.print(arr[0][j]+"\t");
	    		}
	    	}
	    System.out.println("原始数据平均值为： "+pj);
	    System.out.println("简单抽样平均值为： "+pj1);
	    System.out.println("简单抽样相对误差为： "+(Math.abs(pj-pj1)/pj));
	    System.out.println("分层抽样结果为："+jg[0]);
	    System.out.println("分层抽样相对误差为： "+(Math.abs(pj-jg[0])/pj));
	    /*
	    System.out.println("另一种评估方式： ");
	    System.out.println("分层抽样结果为："+jgN[0]);
	    System.out.println("分层抽样相对误差为： "+(Math.abs(pj-jgN[0])/pj));
	    */
	}
}
