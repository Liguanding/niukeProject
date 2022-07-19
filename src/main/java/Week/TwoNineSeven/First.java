package Week.TwoNineSeven;

public class First {
    public static void main(String[] args) {
        First first = new First();
        int[][] brackets = new int[][]{{2,50}};
//        int[][] brackets = new int[][]{{3,50},{7,10},{12,25}};
//        int income = 10;
        int income = 0;
        System.out.println(first.calculateTax(brackets,income));
    }


    public double calculateTax(int[][] brackets, int income) {
        double res = 0d;
        int n = brackets.length;
        for(int i = 0;i < n;++i){
            int upper = brackets[i][0];
            int percent = brackets[i][1];

            if(income >= upper){
                if(i == 0){
                    res += upper * (double)percent / 100;
                }else{
                    res += (upper - brackets[i-1][0]) * (double)percent / 100;
                }
            }else{
                if(i == 0){
                    res += income * (double)percent / 100;
                }else{
                    res += (income - brackets[i-1][0]) * (double)percent / 100;
                }
                return res;
            }

        }
        return res;
    }
}
