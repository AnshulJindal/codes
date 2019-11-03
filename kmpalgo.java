public class kmpalgo
{
	public static void findmat(int[] mat,String pat,int patlen)
	{
		int j=0;
		for(int i=1;i<patlen;)
		{
			if(pat.charAt(j)==pat.charAt(i))
			{
				while(i<patlen && pat.charAt(j)==pat.charAt(i))
				{
						mat[i]=j+1;
						j++;
						i++;
				}
			}
			else
			{
				while(j!=0 && pat.charAt(j)!=pat.charAt(i))
					j=mat[j-1];
				if(j==0 && pat.charAt(j)!=pat.charAt(i))
					i++;
					/*continue;
				else
					i--;*/
			}
		}
	}
	public static void main(String[] args)
	{
		String str="ABABDABACDABABCABAB";
		String pat="AAACAAAAAC";
		int strlen=str.length();
		int patlen=pat.length();
		int[] mat=new int[patlen];
		findmat(mat,pat,patlen);
		for(int i=0;i<patlen;i++)
		{
			System.out.println(mat[i]);
		}
		for(int i=0,j=0;i<strlen;)
		{
			if(pat.charAt(j)==str.charAt(i))
			{
				i++;
				j++;
			}
			if(j==patlen)
			{
				System.out.println(i-j+" "+i);
				j=mat[j-1];
			}
			else if(i<strlen && pat.charAt(j)!=str.charAt(i))
			{
				if(j!=0)
					j=mat[j-1];
				else
					i++;
			}
    }
  }
}
