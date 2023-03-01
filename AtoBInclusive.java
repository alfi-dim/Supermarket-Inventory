import java.util.Scanner;
public class AtoBInclusive
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        int c = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int k = scanner.nextInt();
        int i = 0;
        if (c >= 1 && c <= 100){
            if(1 <= a && a <= b && b < 10000){
                if (1 <= k && k < 10000){
                    for (int j = a;j <= b;j++){
                        if(j % k == 0){
                            i++;
                        }
                    }
                }
            }
        }
        
        System.out.println("Case "+c+": "+i);
    }
}
