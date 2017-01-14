package CannonballSmelter;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToBank extends Task {

	public WalkToBank(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return CannonballSmelter.smeltHere.contains(api.myPlayer()) && !api.getInventory().contains("Steel bar");
	}

	@Override
	public void process() {
		api.getWalking().walk(CannonballSmelter.edgeBank.getRandomPosition());
		if(api.myPlayer().isMoving()) {
			new ConditionalSleep(15000, 1000) {
	            @Override
	            public boolean condition() throws InterruptedException {
	                return CannonballSmelter.edgeBank.contains(api.myPlayer());
	            }
	        }.sleep();
		}
	}

}