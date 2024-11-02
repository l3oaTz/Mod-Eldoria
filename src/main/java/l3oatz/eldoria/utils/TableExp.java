package l3oatz.eldoria.utils;

public class TableExp
{
	public static int updatelevel(int level)
	{
		int baseexp;
		switch(level) {
	        case 1:
	        	baseexp = 14;
	            break;
	        case 2:
	            baseexp = 14;
	            break;
	        case 3:
	            baseexp = 20;
	            break;
	        case 4:
	            baseexp = 36;
	            break;
	        case 5:
	            baseexp = 90;
	            break;
	        default:
	            baseexp = 100 + (level * 10);  // 5
	            break;
	    }
		return baseexp;
	}
	
	/*public static int updatelevel(EntityPlayer player)
	{
		IPlayerData status = PlayerDataCapability.getPlayerData(player);
		int baseexp;
		if (status.getLevel() == 1) {
			baseexp = 14;
		} else if (status.getLevel() == 2) {
			baseexp = 14;
		} else if (status.getLevel() == 3) {
			baseexp = 20;
		} else if (status.getLevel() == 4) {
			baseexp = 36;
		} else if (status.getLevel() == 5) {
			baseexp = 90;
		} else if (status.getLevel() == 6) {
			baseexp = 152;
		} else if (status.getLevel() == 7) {
			baseexp = 250;
		} else if (status.getLevel() == 8) {
			baseexp = 352;
		} else if (status.getLevel() == 9) {
			baseexp = 480;
		} else if (status.getLevel() == 10) {
			baseexp = 591;
		} else if (status.getLevel() == 11) {
			baseexp = 743;
		} else if (status.getLevel() == 12) {
			baseexp = 973;
		} else if (status.getLevel() == 13) {
			baseexp = 1290;
		} else if (status.getLevel() == 14) {
			baseexp = 1632;
		} else if (status.getLevel() == 15) {
			baseexp = 1928;
		} else if (status.getLevel() == 16) {
			baseexp = 2340;
		} else if (status.getLevel() == 17) {
			baseexp = 3480;
		} else if (status.getLevel() == 18) {
			baseexp = 4125;
		} else if (status.getLevel() == 19) {
			baseexp = 4995;
		} else if (status.getLevel() == 20) {
			baseexp = 5880;
		} else if (status.getLevel() == 21) {
			baseexp = 7840;
		} else if (status.getLevel() == 22) {
			baseexp = 6875;
		} else if (status.getLevel() == 23) {
			baseexp = 8243;
		} else if (status.getLevel() == 24) {
			baseexp = 10380;
		} else if (status.getLevel() == 25) {
			baseexp = 13052;
		} else if (status.getLevel() == 26) {
			baseexp = 16450;
		} else if (status.getLevel() == 27) {
			baseexp = 20700;
		} else if (status.getLevel() == 28) {
			baseexp = 26143;
		} else if (status.getLevel() == 29) {
			baseexp = 31950;
		} else if (status.getLevel() == 30) {
			baseexp = 38640;
		} else if (status.getLevel() == 31) {
			baseexp = 57035;
		} else if (status.getLevel() == 32) {
			baseexp = 65000;
		} else if (status.getLevel() == 33) {
			baseexp = 69125;
		} else if (status.getLevel() == 34) {
			baseexp = 72000;
		} else if (status.getLevel() == 35) {
			baseexp = 87239;
		} else if (status.getLevel() == 36) {
			baseexp = 105863;
		} else if (status.getLevel() == 37) {
			baseexp = 128694;
		} else if (status.getLevel() == 38) {
			baseexp = 182307;
		} else if (status.getLevel() == 39) {
			baseexp = 221450;
		} else if (status.getLevel() == 40) {
			baseexp = 269042;
		} else if (status.getLevel() == 41) {
			baseexp = 390368;
		} else if (status.getLevel() == 42) {
			baseexp = 438550;
		} else if (status.getLevel() == 43) {
			baseexp = 458137;
		} else if (status.getLevel() == 44) {
			baseexp = 468943;
		} else if (status.getLevel() == 45) {
			baseexp = 560177;
		} else if (status.getLevel() == 46) {
			baseexp = 669320;
		} else if (status.getLevel() == 47) {
			baseexp = 799963;
		} else if (status.getLevel() == 48) {
			baseexp = 1115396;
		} else if (status.getLevel() == 49) {
			baseexp = 1331100;
		} else if (status.getLevel() == 50) {
			baseexp = 1590273;
		} else {
			baseexp = 0;
		}
		return baseexp;
	}*/
}