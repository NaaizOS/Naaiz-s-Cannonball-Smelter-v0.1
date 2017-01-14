package CannonballSmelter;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class BankTask extends Task {
	
	public BankTask(MethodProvider api) {
		super(api);
	}
	
	@Override
	public boolean canProcess() {
		return !api.inventory.isFull() && CannonballSmelter.edgeBank.contains(api.myPlayer());
	}

	@Override
	public void process() throws InterruptedException  {
		api.getWalking().walk(CannonballSmelter.edgeBank.getRandomPosition());
		api.getBank().open();
		if(api.getBank().open()){
			new ConditionalSleep(1000, 100) {
	            @Override
	            public boolean condition() throws InterruptedException {
	                return api.bank.contains("Steel bar");
	            }
	        }.sleep();
		}
		if(api.getInventory().contains("Cannonball") && api.getBank().isOpen()) {
			api.getBank().depositAll("Cannonball");
			new ConditionalSleep(5000, 1000) { 
				@Override
				public boolean condition() throws InterruptedException {
					return !api.getInventory().contains("Cannonball");
				}
			}.sleep();
		}
		api.getBank().withdrawAll("Steel bar");
		new ConditionalSleep(5000, 1750) { 
			@Override
			public boolean condition() throws InterruptedException {
				return api.getInventory().contains("Steel bar");
			}
		}.sleep();
	}
}