package cw426;

import java.util.Arrays;
import java.util.Stack;

public class Sort {
    public static void quickSort(int[] array) {
        quickHelper(array,0,array.length-1);
    } //快速排序
    private static void quickHelper(int[] array, int left, int right) {
        if(left>=right){
            return;
        }
        int index=partion(array,left,right);
        quickHelper(array,left,index-1);
        quickHelper(array,index+1,right);
    }
    public static void swap(int[] array,int i,int j){     //一会调用堆顶元素
        int temp = array[i];
        array[i]=array[j];
        array[j] = temp;
    }
    private static int partion(int[] array, int left, int right) {
        int i =left;
        int j =right;
        int base=array[right];
        while (i<j){
            while (i<j && array[i]<=base){
                i++;
            }
            while (i<j && array[j] >=base){
                j--;
            }
            swap(array,i,j);
        }
        swap(array,i,right);
        return i;
    }
    public static void quick1(int[] array){    //非递归
        Stack<Integer> stack = new Stack <>(); //非递归，通过用栈来存取数组的下标，
        // 将左右下标入栈,先入右边数组的下标
        stack.push(array.length-1);
        stack.push(0);
        while (!stack.isEmpty()){  //非空就入栈
            int left = stack.pop();
            int right= stack.pop();
            if(left>=right){
                continue;
            }
            //通过partion方法分为两部分
            int index= partion(array,left,right); //
            stack.push(right);
            stack.push(index+1);   //基准值右边
            stack.push(left);
            stack.push(index+1);  //基准值左边
        }
    }
    public static void combine(int [] array,int low,int mid,int high){  //归并排序,将两个分成两个部分
        int[] output =new int[high-low];  //在这里建一个数组存放排放的数组下标
        int outputIndex=0; //记录当前output中存放了多少元素
        int cur1 = low;
        int cur2 = mid;
        while (cur1 < mid && cur2 < high){
            if(array[cur1] <= array[cur2]){
                output[outputIndex] = array[cur1];  //这里进行
                outputIndex++;
                cur1++;
            }else {
                output[outputIndex] = array[cur2];
                outputIndex++;
                cur2++;
            }
        }
        while (cur1<mid){
            output[outputIndex] = array[cur1];   //cur2剩余
            outputIndex++;
            cur1++;
        }
        while (cur2<high){
            output[outputIndex] = array[cur2++];
            outputIndex++;
            cur2++;
        }
        for (int i = 0; i < high-low ; i++) {
            array[low+i] = output[i];
        }
    }
    public static void main(String[] args) {
        int array[] = {23,4,78,67,66,23,668};         //快速排序
        quickSort(array);
        quick1(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array));      //可以进行优化，三个元素中，中间元素中间值，
        // 把确认的插到数组的末尾
    }
}
