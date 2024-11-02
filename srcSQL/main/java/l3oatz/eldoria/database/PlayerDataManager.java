package l3oatz.eldoria.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDataManager
{
	private static MySQLHandler sqlHandler = new MySQLHandler();
	
	public PlayerAttributes loadPlayerAttributes(String uuid)
	{
        try {
            ResultSet rs = sqlHandler.executeQuery("SELECT * FROM character_detail WHERE uuid = ?", uuid);
            if (rs.next()) {
                // PlayerAttributes
                return new PlayerAttributes(
                		rs.getString("uuid"),
                		rs.getString("job"),
                		rs.getInt("level"),
                		rs.getInt("exp"),
                		rs.getInt("stpoint"),
                		rs.getInt("skpoint"),
                		rs.getInt("coin"),
                		rs.getInt("cash"),
                		rs.getInt("STR"),
                		rs.getInt("STA"),
                		rs.getInt("DEX"),
                		rs.getInt("INTS"),
                		rs.getInt("low_attack"),
                		rs.getInt("hight_attack"),
                		rs.getInt("low_defense"),
                		rs.getInt("hight_attack"),
                		rs.getInt("accuracy"),
                		rs.getInt("evasion"),
                		rs.getInt("crirate"),
                		rs.getInt("cridamage"),
                		rs.getInt("recrirate"),
                		rs.getInt("recridamage"),
                		rs.getInt("maxhealth"),
                		rs.getInt("mana"),
                		rs.getInt("maxmana"),
                		rs.getInt("regenhp"),
                		rs.getInt("regenmp")
                		);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // null 
    }

    public void saveNewPlayerAttributes(String uuid) {
        try {
            sqlHandler.executeUpdate("INSERT INTO character_detail (uuid) VALUES (?)", uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePlayerAttribute(String uuid, String column, int newValue) {
        String query = "UPDATE player_attributes SET " + column + " = ? WHERE uuid = ?";
        try {
            sqlHandler.executeUpdate(query, newValue, uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerAttributes(String uuid, PlayerAttributes attributes) {
        try {
            sqlHandler.executeUpdate("UPDATE character_detail SET job = ?, level = ?, exp = ?, stpoint = ?, skpoint = ?,"
            		+ " coin = ?, cash = ?, str = ?, sta = ?, dex = ?, ints = ?, low_attack = ?, hight_attack = ?,"
            		+ " low_defense = ?, hight_defense = ?, accuracy = ?, evasion = ?, crirate = ?, cridamage = ?,"
            		+ " recrirate = ?, recridamage = ?, maxhealth = ?, mana = ?, maxmana = ?, regenhp = ?, regenmp = ? WHERE uuid = ?",
            		attributes.getJob(),
            		attributes.getLevel(),
            		attributes.getExp(),
            		attributes.getSTPoint(),
            		attributes.getSKPoint(),
            		attributes.getCoin(),
            		attributes.getCash(),
            		attributes.getSTR(),
            		attributes.getSTA(),
            		attributes.getDEX(),
            		attributes.getINT(),
            		attributes.getLowATK(),
            		attributes.getHightATK(),
            		attributes.getLowDEF(),
            		attributes.getHightDEF(),
            		attributes.getAccuracy(),
            		attributes.getEvasion(),
            		attributes.getCriRate(),
            		attributes.getCriDamage(),
            		attributes.getReCriRate(),
            		attributes.getReCriDamage(),
            		attributes.getMaxHealth(),
            		attributes.getMana(),
            		attributes.getMaxMana(),
            		attributes.getRegenHP(),
            		attributes.getRegenMP(),
            		uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}