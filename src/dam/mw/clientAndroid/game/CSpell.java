package dam.mw.clientAndroid.game;


public class CSpell {
    private String name;
    private int probability;
    private int energyCost;
    private int type; // call static clas CTYPE (0/1 = offensive/defensive)

    /**
     * CONSTRUCTOR
     * @param name
     * @param probability
     * @param energyCost
     * @param type 
     */
    public CSpell(String name, int probability, int energyCost, int type) {
        this.name = name;
        this.probability = probability;
        this.energyCost = energyCost;
        this.type = type;
    }

    /**
     * GETTERS AND SETTERS
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getProbability() {
        return probability;
    }
    public void setProbability(int probability) {
        this.probability = probability;
    }

    public int getEnergyCost() {
        return energyCost;
    }
    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
    
    
    
}
