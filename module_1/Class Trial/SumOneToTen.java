public class SumOneToTen
{
  public static void main(String[] argv)
  {
    int i, sum = 0;
      for(i = 1; i <= 10; i++)
      {
        sum = sum + i;
      }
      System.out.println("The sum of" +
                        "1 through 10 is" +
                        sum );
  }
}
