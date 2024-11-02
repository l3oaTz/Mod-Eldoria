package l3oatz.eldoria.capabilities;

import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.gui.inventory.InventoryInv;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerData implements IPlayerData
{
	private InventoryInv customInventory = new InventoryInv();
    private boolean setClass = false;
    private String playerClass = "";
    private int level = 1;
    private int exp = 0;
    private int point = 0;
    private int coin = 0;
    private int cash = 0;
    private int str = 0;
    private int sta = 0;
    private int dex = 0;
    private int ints = 0;
    private int attack = 0;
    private int defend = 0;
    private int accuracy = 0;
    private int evasion = 0;
    private int crirate = 0;
    private int cridamage = 0;
    private int recrirate = 0;
    private int recridamage = 0;
    private float health = 0;
    private float mana = 0;
    private float maxmana = 0;
    private int regenhp = 0;
    private int regenmp = 0;
    
    public PlayerData()
    {
        this.setClass = false;
        this.playerClass = "Vargrant";
        this.level = 1;
        this.exp = 0;
        this.point = 1;
        this.coin = 0;
        this.cash = 0;
        this.str = 15;
        this.sta = 15;
        this.dex = 15;
        this.ints = 15;
        this.attack = 0;
        this.defend = 0;
        this.accuracy = 0;
        this.evasion = 0;
        this.crirate = 0;
        this.cridamage = 0;
        this.recrirate = 0;
        this.recridamage = 0;
        this.health = 100.0F;
        this.mana = 100.0F;
        this.maxmana = 100.0F;
        this.regenhp = 1;
        this.regenmp = 1;
    }
    
    public InventoryInv CustomInventory() {
        return customInventory; // CustomInventory
    }

    public boolean getSetClass() {
        return setClass;
    }

    public void setSetClass(boolean setclass) {
        this.setClass = setclass;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public void setPlayerClass(String playerclass) {
        this.playerClass = playerclass;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int value) {
        this.level = value;
    }
    
    public void addLevel(int value) {
        this.level += value;
    }

    public int getExp() {
        return this.exp;
    }
    
    public void setExp(int value) {
        this.exp = value;
    }
    
    public void addExp(int value) {
    	System.out.println("EXP: " + value); // Debug
        this.exp += value; 
        System.out.println("EXP " + this.exp); // Debug
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int value) {
        this.point = value;
    }

    public void addPoint(int value) {
        this.point += value;
    }

    public int getCoin() {
        return this.coin;
    }

    public void setCoin(int value) {
        this.coin = value;
    }

    public int getCash() {
        return this.cash;
    }

    public void setCash(int value) {
        this.cash = value;
    }

    public int getStr() {
        return this.str;
    }
    
    public void setStr(int value) {
        this.str = value;
    }
    
    public int getSta() {
        return this.sta;
    }

    public void setSta(int value) {
        this.sta = value;
    }

    public int getDex() {
        return this.dex;
    }
    
    public void setDex(int value) {
        this.dex = value;
    }

    public int getInts() {
        return this.ints;
    }

    public void setInts(int value) {
        this.ints = value;
    }
    
	@Override
	public int getAttack() {
		return this.attack;
	}

	@Override
	public void setAttack(int value) {
		this.attack = value;
	}

	@Override
	public int getDefend() {
		return this.defend;
	}

	@Override
	public void setDefend(int value) {
		this.defend = value;
	}

	@Override
	public int getAccuracy() {
		return this.accuracy;
	}

	@Override
	public void setAccuracy(int value) {
		this.accuracy = value;
	}

	@Override
	public int getEvasion() {
		return this.evasion;
	}

	@Override
	public void setEvasion(int value) {
		this.evasion = value;
	}

	@Override
	public int getCriRate() {
		return this.crirate;
	}

	@Override
	public void setCriRate(int value) {
		this.crirate = value;
	}

	@Override
	public int getCriDamage() {
		return this.cridamage;
	}

	@Override
	public void setCriDamage(int value) {
		this.cridamage = value;
	}

	@Override
	public int getReCriRate() {
		return this.recrirate;
	}

	@Override
	public void setReCriRate(int value) {
		this.recrirate = value;
	}

	@Override
	public int getReCriDamage() {
		return this.recridamage;
	}

	@Override
	public void setReCriDamage(int value) {
		this.recridamage = value;
	}
    
    public void setMaxHealth(float health) {
        this.health = health;
    }
    
    public float getMaxHealth() {
        return health;
    }
    
	@Override
	public float getMana() {
		return this.mana;
	}

	@Override
	public void setMana(float value) {
		this.mana = value;
	}

	@Override
	public float getMaxMana() {
		return this.maxmana;
	}

	@Override
	public void setMaxMana(float value) {
		this.maxmana = value;
	}

	@Override
	public int getRegenHP() {
		return this.regenhp;
	}

	@Override
	public void setRegenHP(int value) {
		this.regenhp = value;
	}

	@Override
	public int getRegenMP() {
		return this.regenmp;
	}

	@Override
	public void setRegenMP(int value) {
		this.regenmp = value;
	}
	
	public static void calstats(EntityPlayer player)
	{
		
	}
}