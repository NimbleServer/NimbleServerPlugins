package de.nimble.server.enchantmentsystem.enchants.types.playereffect;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * DamageEnchantment class
 * @author master
 */
public class DamageEnchantment extends PlayerEffectEnchantment {
	
	public DamageEnchantment(String enchantmentName) {
		super(enchantmentName);
	}

	/**
	 * Overrides onUse method of base class "Enchantment"
	 * 
	 *  @param EntityDamageByEntityEvent
	 *  @return void
	 */
	@Override
	public void onUse(Event ev) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ev;
		// damage to config
		event.setDamage(event.getDamage() * (getMultiplier() + (getLevel() * 1.5) ));
		
	}

}