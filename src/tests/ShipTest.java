package tests;

import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing of the ship model.
 *
 * @author Taylor
 */
public class ShipTest {

    private Ship[] ships;

    /**
     * Creates array of every type of ship for testing.
     */
    @Before
    public void createShips() {
        ships = new Ship[5];
        ships[0] = new Ship(Ship.Type.Flea, new Pirate("pirate"));
        ships[1] = new Ship(Ship.Type.Gnat, new Pirate("pirate"));
        ships[2] = new Ship(Ship.Type.Firefly, new Pirate("pirate"));
        ships[3] = new Ship(Ship.Type.Mosquito, new Pirate("pirate"));
        ships[4] = new Ship(Ship.Type.BumbleBee, new Pirate("pirate"));
    }

    /**
     * Tests initialization, adding, and functioning of equipment.
     */
    @Test
    public void addEquipmentTest() {
        for (Ship s : ships) {
            assertEquals(s.getWeapons().size(), 0);
            assertEquals(s.getShields().size(), 0);
            assertEquals(s.getGadgets().size(), 0);
            for (int i = 0; i < s.getType().weaponSlots + 5; i++) {
                s.addWeapon(new Weapon(Weapon.Type.Pulse));
            }
            for (int i = 0; i < s.getType().shieldSlots + 5; i++) {
                s.addShield(new Shield(Shield.Type.Alien));
            }
            for (int i = 0; i < s.getType().gadgetSlots + 5; i++) {
                s.addGadget(new Gadget(Gadget.Type.AUTOREPAIR));
            }
            int damage = 0;
            for (Weapon w : s.getWeapons()) {
                damage += w.getDamage();
            }
            assertEquals("Attack miscalculation", s.calculateAttack(), damage);
            assertEquals("Weapons overfilled",
                    s.getType().weaponSlots, s.getWeapons().size());
            assertEquals("Shields overfilled",
                    s.getType().shieldSlots, s.getShields().size());
            assertEquals("Gadgets overfilled",
                    s.getType().gadgetSlots, s.getGadgets().size());
        }
    }

    /**
     * Tests fuel consumption and initialization.
     */
    @Test
    public void fuelTest() {
        for (Ship s : ships) {
            assertEquals((int) s.getFuelAmount(), s.getType().fuelCapacity);
            assertEquals(s.getFuelCapacity(), s.getType().fuelCapacity);

            s.expendFuel(s.getFuelAmount());
            assertEquals((int) s.getFuelAmount(), 0);
            s.refuel();
            assertEquals((int) s.getFuelAmount(), s.getFuelCapacity());
            s.expendFuel(s.getFuelAmount());
            assertEquals((int) s.getFuelAmount(), 0);
        }
    }

    /**
     * Tests ship's illegal cargo reporting.
     */
    @Test
    public void illegalCargoTest() {
        for (Ship s : ships) {
            assertFalse(s.hasIllegalGoods());
            s.getCargoHold().addItem(TradeGood.FIREARMS);
            assertTrue(s.hasIllegalGoods());
            s.getCargoHold().removeItem(TradeGood.FIREARMS);
            assertFalse(s.hasIllegalGoods());
            s.getCargoHold().addItem(TradeGood.NARCOTICS);
            assertTrue(s.hasIllegalGoods());
            s.getCargoHold().removeItem(TradeGood.NARCOTICS);
            assertFalse(s.hasIllegalGoods());
        }
    }

    /**
     * Tests initialization and damaging of ship's
     * hull strength and death upon depletion of hull
     * strength.
     */
    @Test
    public void damageTest() {
        for (Ship s : ships) {
            assertEquals(s.getHullStrength(), s.getMaxHullStrength());
            assertFalse(s.isDead());
            s.takeDamage(s.getMaxHullStrength() / 2);
            assertEquals(s.getHullStrength(), s.getMaxHullStrength()
                    - s.getMaxHullStrength() / 2);
            assertFalse(s.isDead());
            s.takeDamage(s.getHullStrength());
            assertEquals(s.getHullStrength(), 0);
            assertTrue(s.isDead());
            s.takeDamage(1);
            assertTrue(s.isDead());
            s.takeDamage(s.getMaxHullStrength());
            assertTrue(s.isDead());
        }
    }

    /**
     * Tests if ship reports correct owner.
     */
    @Test
    public void ownerTest() {
        for (Ship s : ships) {
            assertTrue("Incorrect Owner", s.getOwner() instanceof Pirate);
        }
    }


}
