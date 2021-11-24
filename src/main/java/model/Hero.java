package model;


public class Hero {
    private Integer id;

    private String name;
    private String faction;
    private String power;
    private float hp;


    public Hero( String name, String faction, String power, float hp) {
        this.name = name;
        this.faction = faction;
        this.power = power;
        this.hp = hp;
    }

    public static String hero_to_JSON(Hero hero){

        String jsonString = "{\n" +
                "    \"id\": \""+ hero.getId() + "\",\n" +
                "    \"name\": \""+ hero.getName()+"\",\n" +
                "    \"faction\": \""+hero.getfaction()+"\",\n" +
                "    \"power\": \" "+hero.getPower()+"\",\n" +
                "    \"hp\": "+ hero.getHp() + "\n" +
                "}";
        return jsonString;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hero(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfaction() {
        return faction;
    }

    public void setfaction(String faction) {
        this.faction = faction;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }




}
