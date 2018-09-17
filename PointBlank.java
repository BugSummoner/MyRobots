package mjb;

import java.awt.Color;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * PointBlank - a robot by (your name here)
 */
public class PointBlank extends AdvancedRobot {
	/**
	 * run: PointBlank's default behavior
	 */
	int count = 0;

	public void run() {
		// Initialization of the robot should be put here

		setColors(Color.red, Color.black, Color.gray,Color.MAGENTA,Color.WHITE); // body,gun,radar
		turnRadarRight(270);
		double radarTurn = 180;

		// Robot main loop
		while (true) {
			// Replace the next 4 lines with any behavior you would like

			turnRadarRight(radarTurn);
			radarTurn = radarTurn * -1;
			count++;
			execute();
			
			if (count>1) {
				turnRadarLeft(-radarTurn);
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like

		setTurnRight(e.getBearing());
		setAhead(e.getDistance());
		count = 0;
	}


	public void onHitRobot(HitRobotEvent e) {
		// Replace the next line with any behavior you would like
		if (e.isMyFault()) {
			fire(Rules.MAX_BULLET_POWER);
		}
	}

	
	public void onBulletMissed(BulletMissedEvent e) {
		back(20);
	}

	
	public void onHitByBullet(HitByBulletEvent e) {
		fire(e.getPower());
	}


//	/**
//	 * onHitWall: What to do when you hit a wall
//	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}
}
