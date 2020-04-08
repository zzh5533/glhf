 package com.zhddk.Springv1.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
public class Markov {
	public static void main(String[] args) {
		double[] gap = new double[] { 
				9.14,7.85,5.9
		};
		// levelList �ڵ�ֵ��0��1��� ����3��ֵ
		List<Integer> levelList = new ArrayList<Integer>();
		// ��3��в��Ϊ2���ȼ� ����/���� ��Ӧ0 1
		int[] statusValue = new int[] {0, 1};
		// ���������ڴ洢ÿ��״̬��Ӧ���ֵĴ�������״ֵ̬ �� ״̬0 �����������г�����x��
		int[] statusValueNum = new int[statusValue.length];
		double sum =0;
		for(double s:gap) {
			sum+=s;
		}
		sum=sum/gap.length;
		for (int i = 0; i < gap.length; i++) {
			if (gap[i] < sum) {
				statusValueNum[0]++;
				levelList.add(0);
			}else {
				statusValueNum[1]++;
				levelList.add(1);
			}
		}
		System.out.println("ÿ��Ľ�������Ӧ��״ֵ̬:\n" + levelList + "\n");
		System.out.println("״̬0��1�ֱ���ֵĴ���:\n" + Arrays.toString(statusValueNum) + "\n");
		// ��ȡת�Ƹ��ʾ���
		Double[][] transProbablityMatrix = statusTransProbablity(statusValueNum, levelList);
		System.out.println("һ��ת�Ƹ��۾�������");
		for (int i = 0; i < transProbablityMatrix.length; i++) {
			for (int j = 0; j < transProbablityMatrix.length; j++) {
				System.out.printf("%.4f\t", transProbablityMatrix[i][j]);// �����ʽ���������
			}
			System.out.println();
		}
	}
 
	// һ��ת�Ƹ��ʾ���
	public static Double[][] statusTransProbablity(int[] statusValueNum, List<Integer> levelList) {
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
