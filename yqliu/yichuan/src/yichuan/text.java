
package yichuan;
import java.util.Random;  
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class text {

	public static void main(String[] args) throws IOException {
		Random ne=new Random();
		int x,y,z;
		int[][] aa={{1,2,3},{4,5,6}};
		int[][] bb={{7,8,9},{10,11,12}};
		//System.arraycopy(bb[1], 0, aa[1],0, 2);
		aa=bb;
		x=ne.nextInt(1);
		y=ne.nextInt(2);
		int i,j;
		int[][] arr=new int[5][3];
		z=(int) ((int)5*1.39);
		int r=arr[0].length;
		double w=Math.pow(10, -5);
		
		
		DefaultCategoryDataset dateset = new DefaultCategoryDataset();
		/*
		dateset.setValue(6, "a", "1");
		dateset.setValue(7, "a", "2");
		dateset.setValue(3, "a", "3");
		dateset.setValue(2, "a", "4");
		dateset.setValue(9, "a", "5");
		dateset.setValue(5, "b", "2");
		dateset.setValue(3, "b", "3");
		dateset.setValue(9, "b", "4");
		*/
          double[] bbb={2,3,4,5,6};
		for(i=5;i<10;i++){
			dateset.setValue(bbb[i-5], "a", String.valueOf(i));
		}

		JFreeChart chart=ChartFactory.createLineChart(
		"test", //图表标题
		"month", //X轴lable
		"sales", //Y轴lable
		dateset, //数据集
		PlotOrientation.VERTICAL, //图表放置模式水平/垂直 
		true, //显示lable
		false, //显示提示
		false //显示urls
		);

	/*	
       ChartPanel chartf = new ChartPanel(chart.freeChart,true);
        
        JFrame jf = new JFrame();
        
        
        jf.setVisible(true);
        jf.setSize(1400, 600);
*/
		
		OutputStream os=new FileOutputStream("f:\\test.jpg");
		ChartUtilities.writeChartAsJPEG(os, chart, 500, 500);
		os.close();
		
		String ww=String.valueOf(12);
		
		
		
		System.out.print(x+"  "+y+"   "+z+"   "+r+"   "+ww+"   "+aa[1][0]);
		
		for(i=0;i<2;i++){
			for(j=0;j<3;j++){
				System.out.println(aa[i][j]+" ");
			}
		}
		double a=5.3;
		int b=(int)a/1;
		double c;
		c=5*Math.pow(1/(2*0.0)*Math.log(2/(1-0.9)), 0.5);
		c=c/5;
		if(c>200000){
			System.out .print("  yes");
		}
		System.out.println("  "+a+" "+b+" "+c);
		

	}

}
