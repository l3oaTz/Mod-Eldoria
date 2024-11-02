package l3oatz.eldoria.capabilities.api;

import l3oatz.eldoria.gui.inventory.InventoryInv;

public interface IPlayerData
{
	InventoryInv CustomInventory();
	
    boolean getSetClass();
    void setSetClass(boolean setClass);

    String getPlayerClass();
    void setPlayerClass(String playerClass);

    int getLevel();
    void setLevel(int level);
    void addLevel(int level);

    int getExp();
    void setExp(int exp);
    void addExp(int exp);

    int getPoint();
    void setPoint(int point);
    void addPoint(int point);

    int getCoin();
    void setCoin(int coin);

    int getCash();
    void setCash(int cash);

    int getStr();
    void setStr(int str);

    int getSta();
    void setSta(int sta);

    int getDex();
    void setDex(int dex);

    int getInts();
    void setInts(int ints);
    
    int getAttack();
    void setAttack(int ints);
    
    int getDefend();
    void setDefend(int ints);
    
    int getAccuracy();
    void setAccuracy(int ints);
    
    int getEvasion();
    void setEvasion(int ints);
    
    int getCriRate();
    void setCriRate(int ints);
    
    int getCriDamage();
    void setCriDamage(int ints);
    
    int getReCriRate();
    void setReCriRate(int ints);
    
    int getReCriDamage();
    void setReCriDamage(int ints);
    
    float getMaxHealth();
    void setMaxHealth(float health);
    
    float getMana();
    void setMana(float ints);
    
    float getMaxMana();
    void setMaxMana(float ints);
    
    int getRegenHP();
    void setRegenHP(int ints);
    
    int getRegenMP();
    void setRegenMP(int ints);
    
}