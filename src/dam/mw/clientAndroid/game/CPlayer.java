package dam.mw.clientAndroid.game;

import java.util.ArrayList;


public class CPlayer {
    /**
     * ATTRIBUTES
     */
    private ArrayList<CSpell> spells = new ArrayList<CSpell>();
    /* ó
    private CSpell[] spells = new CSpell[6]; // sugiero el uso de arrays cuando ya sabemos que es limitado
    */
    private int classType; // call to static class for assign 0 or 1 (mage or warlock)
    private String playerName;
    private Double life;
    private Double energy;
    private Double energyRegen;
    private Double strength;
    private Double intelligence;
    private int currentPoints;
    private int wonGames;
    
    
    // Battle attributes
    private int action; // use the constants of the CConstant for compare to. 
    private String statusOnBattle;
    private boolean isAlive = true;

    /**
     * CONSTRUCTOR
     * @param playerName
     * @param classType
     * @param life
     * @param energy
     * @param energyRegen
     * @param strength
     * @param intelligence 
     */
    public CPlayer(String playerName, int classType, Double life, Double energy, Double energyRegen, Double strength, Double intelligence, int totalPoints, int wonGames, ArrayList<CSpell> spells ) {
        this.playerName = playerName;
        this.classType = classType;
        this.life = life;
        this.energy = energy;
        this.energyRegen = energyRegen;
        this.strength = strength;
        this.intelligence = intelligence;
        this.currentPoints = totalPoints;
        this.wonGames = wonGames;
        this.spells = spells;
    }

    /**
     * GETTERS AND SETTERS
     */
    public ArrayList<CSpell> getSpells() {
        return spells;
    }
    public void setSpells(ArrayList<CSpell> spells) {
        this.spells = spells;
    }
    
    public int getClassType() {
        return classType;
    }
    public void setClassType(int classType) {
        this.classType = classType;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Double getLife() {
        return life;
    }
    public void setLife(Double life) {
       this.life = life;
    }

    public Double getEnergy() {
        return energy;
    }
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getEnergyRegen() {
        return energyRegen;
    }
    public void setEnergyRegen(Double energyRegen) {
        this.energyRegen = energyRegen;
    }

    public Double getStrength() {
        return strength;
    }
    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(Double intelligence) {
        this.intelligence = intelligence;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }    

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getStatusOnBattle() {
        return statusOnBattle;
    }

    public void setStatusOnBattle(String statusOnBattle) {
        this.statusOnBattle = statusOnBattle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    
}
