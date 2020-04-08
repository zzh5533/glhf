package com.zhddk.Springv1.service.imp;

import java.util.ArrayList;
import java.util.List;

public class Greypre {

	 		public static double[] greyMarkov(double[] data) {
	 			double[] dk = gm(data, 4);
				double[] residual = new double[3];
				double grey = dk[0];
				System.arraycopy(dk,1,residual,0,3);
				List<Integer> levelList = new ArrayList<Integer>();
					
				double max = residual[0]>residual[1]?(residual[0]>residual[2]?residual[0]:residual[2]):residual[1];
				double min = residual[0]<residual[1]?(residual[0]<residual[2]?residual[0]:residual[2]):residual[1];
				
				int[] statusValue = new int[] {0,1};//状态
				int[] statusValueNum = new int[statusValue.length];
				
				double sum = 0;//残差期望
				for(double s:residual) {
					sum+=s;
				}
				sum=sum/residual.length;
				
				for (int j = 0; j < residual.length; j++) {
					if (residual[j] < sum) {
						statusValueNum[0]++;
						levelList.add(0);
					}else {
						statusValueNum[1]++;
						levelList.add(1);
					}
				}
				
				//一步转移概率矩阵	
				Double[][] transProbablityMatrix = Markov.statusTransProbablity(statusValueNum, levelList);
	 			
				int a = residual[2]<sum?0:1;//当前概率状态
				double[] result = new double[2];//结果区间
				
				if(transProbablityMatrix[a][0]==0 && transProbablityMatrix[a][1]==0) {
					result[0] = grey>grey/(1-sum)?grey/(1-sum):grey;
					result[1] = grey<grey/(1-sum)?grey/(1-sum):grey;
				}else if(transProbablityMatrix[a][0]==0 && transProbablityMatrix[a][1]==1) {
					result[0] = grey/(1-max)>grey/(1-sum)?grey/(1-sum):grey/(1-max);
					result[1] = grey/(1-max)<grey/(1-sum)?grey/(1-sum):grey/(1-max);
				}else if(transProbablityMatrix[a][0]==1 && transProbablityMatrix[a][1]==0) {
					result[0] = grey/(1-min)>grey/(1-sum)?grey/(1-sum):grey/(1-min);
					result[1] = grey/(1-min)<grey/(1-sum)?grey/(1-sum):grey/(1-min);
				}else{
					result[0] = grey/(1-max);
					result[1] = grey/(1-min);
				}
				
	 			return result;
	 		}
	
	
		    public static double[] gm(double[] fs, int T) {

		        // Ԥ��ģ�ͺ���
		        int size = fs.length;
		        int tsize = fs.length - 1;
		        double[] arr = fs;// ԭʼ����
		        double[] arr1 = new double[size];// ����һ���ۼ�����
		        double sum = 0;
		        for (int i = 0; i < size; i++) {
		            sum += arr[i];
		            arr1[i] = sum;
		        }
		        double[] arr2 = new double[tsize];// arr1�Ľ��ھ�ֵ����
		        for (int i = 0; i < tsize; i++) {
		            arr2[i] = (double) (arr1[i] + arr1[i + 1]) / 2;
		        }
		         
		        double[][] B = new double[tsize][2];
		        for (int i = 0; i < tsize; i++) {
		            for (int j = 0; j < 2; j++) {
		                if (j == 1)
		                    B[i][j] = 1;
		                else
		                    B[i][j] = -arr2[i];
		            }

		        }
		      
		        double[][] YN = new double[tsize][1];
		        for (int i = 0; i < tsize; i++) {
		            for (int j = 0; j < 1; j++) {
		                YN[i][j] = arr[i + 1];
		            }
		        }

		        double[][] BT = new double[2][tsize];
		        for (int i = 0; i < 2; i++) {
		            for (int j = 0; j < tsize; j++) {
		                BT[i][j] = B[j][i];
		            }
		        }
		       
		        double[][] B2T = new double[2][2];
		        for (int i = 0; i < 2; i++) {// rows of BT
		            {
		                for (int j = 0; j < 2; j++)// cloums of B
		                {
		                    for (int k = 0; k < tsize; k++)// cloums of BT=rows of B
		                    {
		                        B2T[i][j] = B2T[i][j] + BT[i][k] * B[k][j];
		                    }
		                }
		            }
		        }
		        
		        double[][] B_2T = new double[2][2];
		        B_2T[0][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
		                * B2T[1][1];
		        B_2T[0][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
		                * (-B2T[0][1]);
		        B_2T[1][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
		                * (-B2T[1][0]);
		        B_2T[1][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
		                * B2T[0][0];
		        
		        double[][] A = new double[2][tsize];
		        for (int i = 0; i < 2; i++) {// rows of B_2T
		            {
		                for (int j = 0; j < tsize; j++)// cloums of BT
		                {
		                    for (int k = 0; k < 2; k++)// cloums of B_2T=rows of BT
		                    {
		                        A[i][j] = A[i][j] + B_2T[i][k] * BT[k][j];
		                    }
		                }

		            }
		        }
		      
		        double[][] C = new double[2][1];
		        for (int i = 0; i < 2; i++) {// rows of A
		            {
		                for (int j = 0; j < 1; j++)// cloums of YN
		                {
		                    for (int k = 0; k < tsize; k++)// cloums of A=rows of YN
		                    {
		                        C[i][j] = C[i][j] + A[i][k] * YN[k][j];
		                    }
		                }

		            }
		        }
		       
		        double a = C[0][0], b = C[1][0];
		        int i = T;// ��ȡһ����ֵ
		        double Y = (arr[0] - b / a) * Math.exp(-a * (i + 1)) - (arr[0] - b / a)
		                * Math.exp(-a * i);
		        
		        double[] dk = new double[4];
		        dk[0]=Y;
		        for(int n=1;n<4;n++) {
		        	dk[n]=(arr[n-1]-Y)/arr[n-1]; //�в�
		        }
		        
		        return dk;
		  }
		    
			public Double[][] statusTransProbablity(int[] statusValueNum, List<Integer> levelList) {
				Double[][] transProbablityMatrix = new Double[2][2];
				
				for (int s = 0; s < statusValueNum.length; s++) {
					int status = 0;
					for (int i = 0; i < statusValueNum.length; i++) {
						int index = 0; 
						for (int j = 0; j < levelList.size() - 1; j++) {
							int k = j;
							while (levelList.get(k).intValue() == s && levelList.get(k + 1).intValue() == status) {
								index++;
								if (k < levelList.size() - 2) {
									k++;
								} else {
									break; 
								}
							}
						}
						transProbablityMatrix[s][i] = Double.valueOf(index) / Double.valueOf(statusValueNum[s]);
						status++;
					}
				}
				return transProbablityMatrix;
			}

}

