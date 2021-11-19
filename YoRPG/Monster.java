public class Monster {

  private int health;
  private int damage;
  private String attackState = "";
  private String monsterName = "";
  private int level;


  public Monster() {
    this.health = 100;
    this.damage = 15;
    this.level = 0;
    this.attackState = "normalize";
  }
  public Monster(String name) {
    this();
    this.monsterName = name;
  }
  public String getName() {
    return this.monsterName;
  }

  public String toString() {
    return "Name: " + getName() + "\nLevel: " + this.level + "\nHealth: " + this.health + "\nAttack State" + this.attackState;
  }

  public boolean isAlive() {
    return health >= 0;
  }
  public void takeDamage(int damage) {
    this.health -= damage;
  }
  public int attack(Protagonist name) {
    if (attackState.equals("specialize")) {
      //System.out.println("Swinging Hammer...");
      int amplifier = (int) (Math.random() * 10);
      if (amplifier > 5) {
        damage += amplifier;
        System.out.println(this.health);
      } else {
        System.out.println("Youg swung too hard, and bashed thy skull");
        takeDamage(5);
        System.out.println(this.health);
      }
    } else {
      damage = 15;
      System.out.println(this.health);
    }
    name.takeDamage(damage);


    return damage;
  }

}