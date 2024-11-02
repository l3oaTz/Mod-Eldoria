package l3oatz.eldoria.database;

public class PlayerAttributes
{
	private String uuid; // UUID
	private String job;
    private int level;
    private int exp;
    private int stpoint = 1;
	private int skpoint = 1;
	private int coin = 0;
	private int cash = 0;
	private int str = 0;
	private int sta = 0;
	private int dex = 0;
	private int ints = 0;
	private int low_attack = 0;
	private int hight_attack = 0;
	private int low_defense = 0;
	private int hight_defense = 0;
	private int accuracy = 0;
	private int evasion = 0;
	private int crirate = 0;
	private int cridamage = 0;
	private int recrirate = 0;
	private int recridamage = 0;
	private int health = 0;
	private int mana = 0;
	private int maxmana = 100;
	private int regenhp = 1;
	private int regenmp = 1;

	// Constructor
    public PlayerAttributes(String uuid, String job, int level, int exp, int stpoint, int skpoint, 
                            int coin, int cash, int STR, int STA, int DEX, int INTS, 
                            int low_attack, int hight_attack, int low_defense, 
                            int hight_defense, int accuracy, int evasion, 
                            int crirate, int cridamage, int recrirate, 
                            int recridamage, int maxhealth, int mana, 
                            int maxmana, int regenhp, int regenmp) {
        this.uuid = uuid; // UUID
        this.job = job;
        this.level = level;
        this.exp = exp;
        this.stpoint = stpoint;
        this.skpoint = skpoint;
        this.coin = coin;
        this.cash = cash;
        this.str = STR;
        this.sta = STA;
        this.dex = DEX;
        this.ints = INTS;
        this.low_attack = low_attack;
        this.hight_attack = hight_attack;
        this.low_defense = low_defense;
        this.hight_defense = hight_defense;
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.crirate = crirate;
        this.cridamage = cridamage;
        this.recrirate = recrirate;
        this.recridamage = recridamage;
        this.health = maxhealth;
        this.mana = mana;
        this.maxmana = maxmana;
        this.regenhp = regenhp;
        this.regenmp = regenmp;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public String getJob() {
        return job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int value) {
        this.level = value;
    }
    
    public int getExp() {
        return exp;
    }

    public void setExp(int value) {
        this.exp = value;
    }
    
    public int getSTPoint() {
        return stpoint;
    }

    public void setSTPoint(int value) {
        this.stpoint = value;
    }
    
    public int getSKPoint() {
        return skpoint;
    }

    public void setSKPoint(int value) {
        this.skpoint = value;
    }
    
    public int getCoin() {
        return coin;
    }

    public void setCoin(int value) {
        this.coin = value;
    }
    
    public int getCash() {
        return cash;
    }

    public void setCash(int value) {
        this.cash = value;
    }
    
    public int getSTR() {
        return str;
    }

    public void setSTR(int value) {
        this.str = value;
    }
    
    public int getSTA() {
        return sta;
    }

    public void setSTA(int value) {
        this.sta = value;
    }
    
    public int getDEX() {
        return dex;
    }

    public void setDEX(int value) {
        this.dex = value;
    }
    
    public int getINT() {
        return ints;
    }

    public void setINT(int value) {
        this.ints = value;
    }

    public int getLowATK() {
        return low_attack;
    }

    public void setLowATK(int value) {
        this.low_attack = value;
    }

    public int getHightATK() {
        return hight_attack;
    }

    public void setHightATK(int value) {
        this.hight_attack = value;
    }

    public int getLowDEF() {
        return low_defense;
    }

    public void setLowDEF(int value) {
        this.low_defense = value;
    }

    public int getHightDEF() {
        return hight_defense;
    }

    public void setHightDEF(int value) {
        this.hight_defense = value;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int value) {
        this.accuracy = value;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int value) {
        this.evasion = value;
    }

    public int getCriRate() {
        return crirate;
    }

    public void setCriRate(int value) {
        this.crirate = value;
    }

    public int getCriDamage() {
        return cridamage;
    }

    public void setCriDamage(int value) {
        this.cridamage = value;
    }

    public int getReCriRate() {
        return recrirate;
    }

    public void setReCriRate(int value) {
        this.recrirate = value;
    }

    public int getReCriDamage() {
        return recridamage;
    }

    public void setReCriDamage(int value) {
        this.recridamage = value;
    }

    public int getMaxHealth() {
        return health;
    }

    public void setMaxHealth(int value) {
        this.health = value;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int value) {
        this.mana = value;
    }

    public int getMaxMana() {
        return maxmana;
    }

    public void setMaxMana(int value) {
        this.maxmana = value;
    }

    public int getRegenHP() {
        return regenhp;
    }

    public void setRegenHP(int value) {
        this.regenhp = value;
    }

    public int getRegenMP() {
        return regenmp;
    }

    public void setRegenMP(int value) {
        this.regenmp = value;
    }
    
    
}