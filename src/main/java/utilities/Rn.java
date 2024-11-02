package utilities;

import java.util.Random;

public class Rn
{
	public static float randFloat(float min, float max)
	{
		Random rand = new Random();
		float result = rand.nextFloat() * (max - min) + min;
		return result;
	}
	
	public static int random(int low, int hight)
	{
		Random r = new Random();
		int Low = low;
		int High = hight + 1;
		int Result = r.nextInt(High - Low) + Low;
		return Result;
	}
	
	public static int random2number(int num1, int num2)
	{
		Random r = new Random();
		int Low = 0;
		int High = 101;
		int Result = r.nextInt(High - Low) + Low;
		if (Result < 50) {
			return num1;
		}
		return num2;
	}
	
	public static int randomatk(int low, int hight)
	{
		Random r = new Random();
		int Low = low;
		int High = hight + 1;
		int Result = r.nextInt(High - Low) + Low;
		if (Result < low) {
			Result = low;
		}
		return Result;
	}
	
	public static int randompk(int low, int hight)
	{
		Random r = new Random();
		int Low = low;
		int High = hight + 1;
		int Result = r.nextInt(High - Low) + Low;
		return Result;
	}
}