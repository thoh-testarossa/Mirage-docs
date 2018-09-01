//  2018.8.14      遗传算法                             ——刘永强

package yichuan;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class ycsf {
	
	//随机初始种群
	public int[][] chushi(int row,int col){
		int[][] arr=new int[row][col];
		int i,j;
		Random ne=new Random();
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				arr[i][j]=ne.nextInt(2);//生成二进制数
			}
		}
		
		return arr;  //返回构造出的种群
	}
	
	// 二进制转十进制（前半部分整数，后半部分小数，）
	public double[] stoe(int[][] arr){
		int row=arr.length;
		int col=arr[0].length;
		int qian=col/2;
		double[] shi=new double[row];//output
		int i,j;
		int count,count1;
		double sum;
		for(i=0;i<row;i++){
			sum=0;
			count=0;
			count1=0;
			for(j=qian-1;j>=0;j--){
				sum=sum+arr[i][j]*Math.pow(2, count);
				count=count+1;
			}
			for(j=qian;j<col;j++){
				count1=count1+1;
				sum=sum+arr[i][j]*Math.pow(2, -count1);
			}
			shi[i]=sum;
			
		}
		return shi;
	}
	
	
	//适应性函数（比较加排序）
	public int[][] syx(int[][] arr){
		int len=arr.length ;
		int high=arr[0].length;
		int[][] bian=arr;
		int[] med=new int[high];
		int i,j;
		ycsf mu=new ycsf();
		double[] zh=mu.stoe(arr);//十进制
		double[] minj=new double[len];       //目标值
		for(i=0;i<len;i++){
			minj[i]=zh[i]*zh[i]-6*zh[i]+9;//目标函数
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
	
	
	//遗传变异(遗传，交叉，变异)
	public int[][] biany(int[][] arr,double remain,double yc,double jc,double by){
		int row=arr.length;
		int col=arr[0].length ;
		int[][] bi=arr;
		int yc1=(int)Math.ceil(yc*row);
		double jc1=jc*col;
		double by1=by*col;
		int remain1=(int)Math.ceil(remain*row);; //不变率
		int j,i,k;
		Random ne=new Random();
		//遗传
		for(i=remain1;i<yc1;i++){
			for(j=0;j<col;j++){
				if(by1/2>ne.nextInt(col)){         //变异
					bi[i][j]=1-bi[i][j]; 
				}
				if(i%2==1){                      //交叉
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
				if(by1>ne.nextInt(col)){         //优值变异
					bi[i][j]=1-arr[i-yc1][j]; 
				}else{
					bi[i][j]=arr[i-yc1][j];
				}
			}
		}
		
		return bi;
	}

	public static void main(String[] args) throws IOException {
		ycsf ob=new ycsf();
		double yc=0.8;               //遗传
		double jc=0.3;               //交叉
		double by=0.1;               //变异
		double remain=0.3;           //生存
		int row=20;                  //种群数
		int col=20;                  //精度
		int[][] cs=ob.chushi(row, col);
		int i,j;
		int k=100;                  //循环次数
        // 画图
		DefaultCategoryDataset dateset = new DefaultCategoryDataset();
		
		for(i=0;i<k;i++){
			cs=ob.biany(cs, remain,yc, jc, by);
			cs=ob.syx(cs);
			dateset.setValue(ob.stoe(cs)[0], "a", String.valueOf(i));
		}
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				System.out.print(cs[i][j]+" ");
			}
			System.out.println(" ");
		}
		
		double[] jie=ob.stoe(cs);
		for(i=0;i<row;i++){
				System.out.println(jie[i]+" ");	
		}
		
		JFreeChart chart=ChartFactory.createLineChart("ycsf","num","value",dateset,PlotOrientation.VERTICAL,true,false,false);
		OutputStream os=new FileOutputStream("f:\\javajpg\\ycsf.jpg");
		ChartUtilities.writeChartAsJPEG(os, chart, 800, 500);
		os.close();
		
		System.out.println("最终解为"+jie[0]);
	}

}
