package CannonballSmelter;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class PathingFix extends Task {

	public PathingFix(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return !CannonballSmelter.edgeBank.contains(api.myPlayer()) && !CannonballSmelter.smeltHere.contains(api.myPlayer()) && !api.myPlayer().isMoving();
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