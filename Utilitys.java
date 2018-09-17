package mjb;

import robocode.*;

public class Utilitys {

	String enemy = null;
	double radarTurn = 180;
	public int count = 0;
	AdvancedRobot rob = null;
	Boolean firstScan = true;

	public Utilitys(AdvancedRobot rob) {
		this.rob = rob;
		
	}

	public void scanForEnemy() {

		if (firstScan) {
			rob.turnRadarRight(270);
			firstScan = false;
		}
		
		rob.turnRadarRight(radarTurn);
		radarTurn = radarTurn * -1;
		count++;
		if (count > 1) {
			rob.turnRadarLeft(-radarTurn);
		}
	}
}
