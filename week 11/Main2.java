public class Main2 {

    public static int sum(int n)
    {
        int result = 0;
        for(int i = 1; i <= n; i ++)
        {
            result += i;
        }

        return result;
    }

    public static void main(String[] args)
    {
        int num = sum(100);
        System.out.println(num);
    }
}