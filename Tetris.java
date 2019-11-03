import java.util.*;
import java.io.*;
interface forstack
{
  boolean push(char c);
  char pop();
  void empty();
}
class structure
{
  char oper;
  structure next=null;
}
class fixstructure
{
  int info[]=new int[4];
  fixstructure next=null;
}
class fixstack
{
  fixstructure top=null;
  boolean push(int x,int y,int version,int shape)
  {
    if(top==null)
    {
      top=new fixstructure();
      top.info[0]=x;
      top.info[1]=y;
      top.info[2]=version;
      top.info[3]=shape;
    }
    else
    {
      fixstructure node=new fixstructure();
      node.info[0]=x;
      node.info[1]=y;
      node.info[2]=version;
      node.info[3]=shape;
      node.next=top;
      top=node;
    }
    return true;
  }
  int[] pop()
  {
    if(top!=null)
    {
      int a[]=top.info;
      top=top.next;
      return a;
    }
    else
    {
      return null;
    }
  }
  void empty()
  {
    top=null;
  }
}
class stack implements forstack
{
  structure top=null;
  public boolean push(char c)
  {
    if(top==null)
    {
      top=new structure();
      top.oper=c;
    }
    else
    {
      structure node=new structure();
      node.oper=c;
      node.next=top;
      top=node;
    }
    return true;
  }
  public char pop()
  {
    if(top!=null)
    {
      char x=top.oper;
      top=top.next;
      return x;
    }
    else
    {
      return 'q';
    }
  }
  public void empty()
  {
    top=null;
  }
}
class Board
{
  int count[]=new int[20];
  char board[][]=new char[21][20];
  Board()
  {
    for(int i=0;i<20;i++)
    {
      for(int j=0;j<20;j++)
      {
        if(i==0||j==0||j==19||i==19)
          board[i][j]='*';
        else
          board[i][j]=' ';
      }
    }
  }
  void print()
  {
    for(int i=0;i<20;i++)
    {
      for(int j=0;j<20;j++)
      {
        System.out.print(board[i][j]);
      }
      System.out.println("");
    }
  }
  void clearboard(Shape s)
  {
    for(int i=0;i<4;i++)
    {
      int x=s.arr[i][0];
      int y=s.arr[i][1];
      board[x][y]=' ';
    }
  }
  void fillboard(Shape s)
  {
    for(int i=0;i<4;i++)
    {
      int x=s.arr[i][0];
      int y=s.arr[i][1];
      board[x][y]='#';
    }
  }
  void clearconsole()
  {
    try
    {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    catch(Exception e)
    {

    }
  }
}
class Shape
{
  int version=1;
  int arr[][]=new int[4][2];
  static boolean rotatepossible(Board b,int i,Shape s)
  {
    if(s.arr[i][0]>18 || s.arr[i][1]>18 || s.arr[i][0]<1 || s.arr[i][1]<1 || b.board[s.arr[i][0]][s.arr[i][1]]!=' ')
      return false;
    return true;
  }
  static void typecasti(Shape s,int sh)
  {
    if(sh==1)
    {
      ((Line)s).setcoordinate();
    }
    else if(sh==2)
    {
      ((Lshape)s).setcoordinate();
    }
    else if(sh==3)
    {
      ((Square)s).setcoordinate();
    }
    else if(sh==4)
    {
      ((Zshape)s).setcoordinate();
    }
    else
    {
      ((Tshape)s).setcoordinate();
    }
  }
  int rotate(Shape s,Board b,int sh,stack u)
  {
    if(s.version>3)
    {
      b.clearboard(s);
      s.version=1;
      typecasti(s,sh);
      int flag=0;
      for(int i=0;i<4;i++)
      {
        if(!rotatepossible(b,i,s))
        {
          s.version=4;
          typecasti(s,sh);
          flag=1;
        }
      }
      if(flag==0)
      {
        u.push('w');
        for(int i=0;i<4;i++)
        {
          if(b.board[s.arr[i][0]+1][s.arr[i][1]]!=' ')
          {
            return 1;
          }
        }
      }
    }
    else
    {
      b.clearboard(s);
      s.version++;
      typecasti(s,sh);
      int flag=0;
      for(int i=0;i<4;i++)
      {
        if(!rotatepossible(b,i,s))
        {
          s.version--;
          typecasti(s,sh);
          flag=1;
        }
      }
      if(flag==0)
      {
        u.push('w');
        for(int i=0;i<4;i++)
        {
          if(b.board[s.arr[i][0]+1][s.arr[i][1]]!=' ')
          {
            return 1;
          }
        }
      }
    }
    return 0;
  }
  int moveleft(Shape s,Board b,int sh,stack u)
  {
    b.clearboard(s);
    if(b.board[s.arr[0][0]][s.arr[0][1]-1]==' '&&b.board[s.arr[1][0]][s.arr[1][1]-1]==' '&&b.board[s.arr[2][0]][s.arr[2][1]-1]==' '&&b.board[s.arr[3][0]][s.arr[3][1]-1]==' ')
    {
      u.push('a');
      --s.arr[1][1];
      typecasti(s,sh);
      if(b.board[s.arr[0][0]+1][s.arr[0][1]]!=' '||b.board[s.arr[1][0]+1][s.arr[1][1]]!=' '||b.board[s.arr[2][0]+1][s.arr[2][1]]!=' '||b.board[s.arr[3][0]+1][s.arr[3][1]]!=' ')
        return 1;
    }
    return 0;
  }
  int moveright(Shape s,Board b,int sh,stack u)
  {
    b.clearboard(s);
    if(b.board[s.arr[0][0]][s.arr[0][1]+1]==' '&&b.board[s.arr[1][0]][s.arr[1][1]+1]==' '&&b.board[s.arr[2][0]][s.arr[2][1]+1]==' '&&b.board[s.arr[3][0]][s.arr[3][1]+1]==' ')
    {
      u.push('d');
      ++s.arr[1][1];
      typecasti(s,sh);
      if(b.board[s.arr[0][0]+1][s.arr[0][1]]!=' '||b.board[s.arr[1][0]+1][s.arr[1][1]]!=' '||b.board[s.arr[2][0]+1][s.arr[2][1]]!=' '||b.board[s.arr[3][0]+1][s.arr[3][1]]!=' ')
        return 1;
    }
    return 0;
  }
  int movedown(Shape s,Board b,int sh,stack u)
  {
    u.push('s');
    b.clearboard(s);
    ++s.arr[1][0];
    typecasti(s,sh);
    if(b.board[s.arr[0][0]+1][s.arr[0][1]]!=' '||b.board[s.arr[1][0]+1][s.arr[1][1]]!=' '||b.board[s.arr[2][0]+1][s.arr[2][1]]!=' '||b.board[s.arr[3][0]+1][s.arr[3][1]]!=' ')
      return 1;
    return 0;
  }
  void undo(Shape s,Board b,int sh,stack u,fixstack un)
  {
    Tetris game=new Tetris();
    Shape sha=new Shape();
    char op=u.pop();
    if(op!='q')
    {
      b.clearboard(s);
      if(op=='s')
      {
        s.arr[1][0]--;
        typecasti(s,sh);
      }
      else if(op=='a')
      {
        s.arr[1][1]++;
        typecasti(s,sh);
      }
      else if(op=='d')
      {
        s.arr[1][1]--;
        typecasti(s,sh);
      }
      else if(op=='w')
      {
        if(s.version==1)
          s.version=4;
        else
          s.version--;
        typecasti(s,sh);
      }
      else if(op=='c')
      {
        int info[]=un.pop();
        Shape l;
        if(info!=null)
        {
          if(info[3]==1)
          {
            l=new Line();
          }
          else if(info[3]==2)
          {
            l=new Lshape();
          }
          else if(info[3]==3)
          {
            l=new Square();
          }
          else if(info[3]==4)
          {
            l=new Zshape();
          }
          else
          {
            l=new Tshape();
          }
          l.arr[1][0]=info[0];
          l.arr[1][1]=info[1];
          l.version=info[2];
          typecasti(l,info[3]);
          b.count[arr[1][0]]--;
          b.count[arr[1][0]]--;
          b.count[arr[2][0]]--;
          b.count[arr[3][0]]--;
          char mn=Tetris.moveshape(l,b,sha,info[3],u,un);
        }
      }
    }
  }
}
class Line extends Shape
{
  Line()
  {
    arr[1][0]=2;
    arr[1][1]=(int)(Math.random()*18)+1;
  }
  void setcoordinate()
  {
    if(version==1)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]+1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]+2;
      arr[3][1]=arr[1][1];

    }
    else if(version==2)
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]-2;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]-1;
      arr[3][0]=arr[1][0];
      arr[3][1]=arr[1][1]+1;
    }
    else if(version==3)
    {
      arr[0][0]=arr[1][0]-2;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]-1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1];
    }
    else
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0];
      arr[3][1]=arr[1][1]+2;
    }
  }
}
class Lshape extends Shape
{
  Lshape()
  {
    arr[1][0]=2;
    arr[1][1]=(int)(Math.random()*17)+1;
  }
  void setcoordinate()
  {
    if(version==1)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]+1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1]+1;
    }
    else if(version==2)
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1]-1;
    }
    else if(version==3)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]-1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1];
    }
    else
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1]+1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0];
      arr[3][1]=arr[1][1]-1;
    }
  }
}
class Square extends Shape
{
  Square()
  {
    arr[1][0]=1;
    arr[1][1]=(int)(Math.random()*17)+1;
  }
  void setcoordinate()
  {
    arr[0][0]=arr[1][0];
    arr[0][1]=arr[1][1]+1;
    arr[1][0]=arr[1][0];
    arr[1][1]=arr[1][1];
    arr[2][0]=arr[1][0]+1;
    arr[2][1]=arr[1][1];
    arr[3][0]=arr[1][0]+1;
    arr[3][1]=arr[1][1]+1;
  }
}
class Zshape extends Shape
{
  Zshape()
  {
    arr[1][0]=2;
    arr[1][1]=(int)(Math.random()*17)+1;
  }
  void setcoordinate()
  {
    if(version==1)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1]+1;
    }
    else if(version==2)
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]+1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]+1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1]-1;
    }
    else if(version==3)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]-1;
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1];
    }
    else
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]-1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0]-1;
      arr[3][1]=arr[1][1]+1;
    }
  }
}
class Tshape extends Shape
{
  Tshape()
  {
    arr[1][0]=1;
    arr[1][1]=(int)(Math.random()*16)+2;
  }
  void setcoordinate()
  {
    if(version==1)
    {
      arr[0][0]=arr[1][0];
      arr[0][1]=arr[1][1]-1;
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1];
    }
    else if(version==2)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0]+1;
      arr[2][1]=arr[1][1];
      arr[3][0]=arr[1][0];
      arr[3][1]=arr[1][1]-1;
    }
    else if(version==3)
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0];
      arr[3][1]=arr[1][1]-1;
    }
    else
    {
      arr[0][0]=arr[1][0]-1;
      arr[0][1]=arr[1][1];
      arr[1][0]=arr[1][0];
      arr[1][1]=arr[1][1];
      arr[2][0]=arr[1][0];
      arr[2][1]=arr[1][1]+1;
      arr[3][0]=arr[1][0]+1;
      arr[3][1]=arr[1][1];
    }
  }
}
class Tetris
{
  static char moveshape(Shape s,Board b,Shape sha,int shape,stack undo,fixstack u)
  {
    Scanner sc=new Scanner(System.in);
    char key='e';
    sha.typecasti(s,shape);
    int obstacle=0;
    b.fillboard(s);
    b.print();
    while(obstacle!=1)
    {
      key=sc.next().charAt(0);
      if(key=='w')
      {
        obstacle=sha.rotate(s,b,shape,undo);
      }
      else if(key=='s')
      {
        obstacle=sha.movedown(s,b,shape,undo);
      }
      else if(key=='a')
      {
        obstacle=sha.moveleft(s,b,shape,undo);
      }
      else if(key=='d')
      {
        obstacle=sha.moveright(s,b,shape,undo);
      }
      else if(key=='u')
      {
        sha.undo(s,b,shape,undo,u);
      }
      b.clearconsole();
      b.fillboard(s);
      if(obstacle==0)
        b.print();
      else
      {
        undo.push('c');
        u.push(s.arr[1][0],s.arr[1][1],s.version,shape);
        for(int i=0;i<4;i++)
        {
          b.count[s.arr[i][0]]++;
          if(b.count[s.arr[i][0]]==18)
          {
            for(int j=s.arr[i][0];b.count[j]!=0;j--)
            {
              for(int z=1;z<19;z++)
                b.board[j][z]=b.board[j-1][z];
              b.count[j]=b.count[j-1];
            }
          }
        }
      }
    }
    return key;
  }
  public static void main(String args[])
  {
    char key='s';
    Scanner sc=new Scanner(System.in);
    Board b=new Board();
    stack undo=new stack();
    fixstack u=new fixstack();
    while(key!='q')
    {
      int shape=(int)(Math.random()*5)+1;
      Shape sha=new Shape();
      if(shape==1)
      {
        Line l=new Line();
        key=moveshape(l,b,sha,shape,undo,u);
      }
      else if(shape==2)
      {
        Lshape L=new Lshape();
        key=moveshape(L,b,sha,shape,undo,u);
      }
      else if(shape==3)
      {
        Square s=new Square();
        key=moveshape(s,b,sha,shape,undo,u);
      }
      else if(shape==4)
      {
        Zshape z=new Zshape();
        key=moveshape(z,b,sha,shape,undo,u);
      }
      else
      {
        Tshape t=new Tshape();
        key=moveshape(t,b,sha,shape,undo,u);
      }
    }
  }
}
