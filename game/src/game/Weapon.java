package game;
import java.io.*;
import java.util.*;


public class Weapon {

	
	private String weaponType;
	private String weaponName;
	private int[] stats = new int[7];
	private int baseDamage;
	private int baseDurability;
	private int modCount;
	private String breakString;
	private boolean foundWeapon;
	
	
	public Weapon(String type, String name) throws FileNotFoundException {
		weaponName = name;
		weaponType = type;
		Scanner findWeapon = new Scanner(new File("WeaponList.txt"));
		while (foundWeapon == false && findWeapon.hasNextLine()) {
			String thisLine = findWeapon.nextLine();
			if (type.equals(thisLine)) {
				foundWeapon = true;
				Scanner weaponStats = new Scanner(findWeapon.nextLine());
				for (int i = 0; i <= 6; i++) {
					stats[i] = weaponStats.nextInt();
				}
				baseDamage = stats[1];
				
				baseDurability = stats[2];
				breakString = findWeapon.nextLine();
				weaponStats.close();
			}
		}
		findWeapon.close();
	}
	
	
	public String examine() {
		return "Name: \t " + weaponName + "\t " + weaponType + "\n Damage: " + stats[1];
	}
	
	
	public int[] use() {
		modCount--;
		stats[1]--;
		if (modCount == 0) {
			stats[1] = baseDamage;
			stats[5] = 0;
		}
		return stats;	
	}
	
	
	public String getBreakString() {
		return breakString;
	}
	
	public String toString() {
		return weaponName;
	}
	
	public String modify(Item modifier) {
		if (modifier.weaponMod == true) {
			if (modifier.modifierType.equals("fire")) {
				stats[5] = 1;
				stats[0] += 3;
				stats[5] = 50;
				return "You've applied fire to your " + weaponName + ".";
			}
			if (modifier.equals("sharpen")) {
				if (stats[0] >= 5) {
					return "You can't sharpen your " + weaponName + ".";
				} else {
					stats[2] = baseDurability;
					return "You've sharpened your " + weaponName + ".";
				}
			}
			return "";
		} else {
			return "You can't apply this item to a weapon.";
		}
	}
	
	
	
}
