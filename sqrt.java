import java.util.*;
public class sqrt
{
  public static void main(String args[])
  {
      int num;
      Scanner sc=new Scanner(System.in);
      num=sc.nextInt();
      int left=1;
      int right=((num/2)+1);
      float ans=0;
      while(left<=right)
      {
          int mid=(left+right)/2;
          if((mid*mid)<=num)
          {
              ans=mid;
              left=mid+1;
          }
          else
          {
              right=mid-1;
          }
      }
      float x=1;
      for(int i=0;i<3;i++)
      {
          x=x/10;
          for(float j=ans;(j*j)<=num;j+=x)
          {
              ans=j;
          }
      }
      System.out.printf("%.3f",ans);
  }
}
