import models.CargoHold;
import models.CargoItem;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roi Atalla
 */
public class CargoHoldTest {
    @Test
    public void testCargoHold() {
        final int capacity = 10;
        
        CargoHold cargoHold = new CargoHold(capacity);
        
        for(int a = 0; a < capacity; a++) {
            cargoHold.addItem(new DummyCargoItem("Dummy" + a));
        }

        Assert.assertEquals(capacity, cargoHold.getQuantity());
        
        for(CargoItem dummy : cargoHold.getCargoItems()) {
            Assert.assertEquals(dummy.getItemName(), 1, cargoHold.getQuantity(dummy));
            Assert.assertTrue("Removing " + dummy.getItemName(), cargoHold.removeItem(dummy));
        }
        
        Assert.assertEquals(0, cargoHold.getQuantity());
        
        CargoItem[] onePerInstance = new CargoItem[capacity / 2];
        
        for(int a = 0; a < capacity; a++) {
            CargoItem dummy;
            if(a % 2 == 0) {
                dummy = new DummyCargoItem("Dummy" + (a / 2));
                onePerInstance[a / 2] = dummy;
            } else {
                dummy = onePerInstance[a / 2];
            }

            cargoHold.addItem(dummy);
        }

        Assert.assertEquals(capacity, cargoHold.getQuantity());
        
        for(CargoItem dummy : onePerInstance) {
            Assert.assertEquals(dummy.getItemName(), cargoHold.getQuantity(dummy), 2);
            Assert.assertTrue("Removing " + dummy.getItemName(), cargoHold.removeItem(dummy));
        }
        
        Assert.assertEquals(capacity / 2, cargoHold.getQuantity());
    }
    
    private static class DummyCargoItem implements CargoItem {
        private String name;
        
        DummyCargoItem(String name) {
            this.name = name;
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof CargoItem) {
                return name.equals(((CargoItem)o).getItemName());
            }
            
            return false;
        }
        
        @Override
        public String getItemName() {
            return name;
        }
    }
}
