package CannonballSmelter;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class SmeltTask extends Task {

	public SmeltTask(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return api.getInventory().isFull() && CannonballSmelter.smeltHere.contains(api.myPlayer()) && api.getInventory().contains("Ammo mould") && !api.inventory.isItemSelected();
	}

	@Override
	public void process() {
		api.inventory.getItem("Steel bar").interact("Use");
		if(api.inventory.isItemSelected())
			api.getObjects().closest("Furnace").interact("Use");
		api.mouse.moveRandomly();
		new ConditionalSleep(400, 200) {
			@Override
            public boolean condition() throws InterruptedException {
                return api.getObjects().closest("Furnace").interact("Use");
            }
		}.sleep();
		new ConditionalSleep(6000, 2000) {
            @Override
            public boolean condition() throws InterruptedException {
            	return api.widgets.getWidgetContainingText("Steel bar") != null;
            }
        }.sleep();
        api.widgets.interact(309, 6, "Make All");
        api.mouse.moveOutsideScreen();
		new ConditionalSleep(170000, 1000) {
            @Override
            public boolean condition() throws InterruptedException {
                return !api.inventory.contains("Steel bar");
            }
        }.sleep();
	}		
}


