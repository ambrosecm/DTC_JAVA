package TriangleClass;

public class Triangle {


    public String triangle(int a, int b, int c){

        if(a > 0 && b > 0 && c >0){
            if(a + b >c)
            {
                if(a == b || b ==c || a ==c)
                {
                    if(a == b && b == c)
                    {
                        return "equilateral";
                    }
                    return "isosceles";
                }
                else{
                    return "scalene";
                }
            }
            else{
                return "Not Triangle";
            }
        }
        else{
            return "Not Triangle";
        }



    }

    public int a;
    public int b;
    public int c;

}