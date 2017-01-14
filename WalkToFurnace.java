package CannonballSmelter;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToFurnace extends Task {

	public WalkToFurnace(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return api.getInventory().isFull() && CannonballSmelter.edgeBank.contains(api.myPlayer());
	}

	@Override
	public void process() {
		api.getWalking().walk(CannonballSmelter.smeltHere.getRandomPosition());
		if(api.myPlayer().isMoving()) {
			new ConditionalSleep(15000, 1000) {
	            @Override
	            public boolean condition() throws InterruptedException {
	                return CannonballSmelter.smeltHere.contains(api.myPlayer());
	            }
	        }.sleep();
		}
	}
}