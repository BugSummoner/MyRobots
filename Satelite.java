package mjb;

import java.awt.Color;
import robocode.*;

public class Satelite extends robocode.AdvancedRobot {

	Utilitys util = null;
	int position = 0;

	public void run() {
		setColors(Color.magenta, Color.yellow, Color.red, Color.MAGENTA, Color.WHITE); // body,gun,radar,bullet,arc
		util = new Utilitys(this);

		// Robot main loop
		while (true) {
			execute();
			util.scanForEnemy();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		util.count = 0;
		out.println("Enemy Distand: " + e.getDistance());
		
		if (position >0) {
			pointToRadar();
			fire(2);
		}

		if (e.getDistance() > 200) {
			setAhead(e.getDistance() - 10);
			setTurnRight(e.getBearing());
			position = 0;
		} else {
			satelite(e);
		}
	}

	public void satelite(ScannedRobotEvent e) {
		out.println("Alpha: " + e.getBearing());
		out.println("Sat Bearing: " + getHeading());

		switch (position) {
		case 0:
//			goToStartPosition(e.getDistance());
			position = 1;
			break;
		case 1:
			setTurnLeft(10000);
			setMaxVelocity(5);
			ahead(1000);
			break;
		default:
			break;
		}
	}

	public void goToStartPosition(double distance) {
		double gegen = distance * Math.sin(getAngle());
		double anka = distance * Math.cos(getAngle());

		double zielWinkel = Math.tan(distance - gegen / anka) + getAngle();

		turnRight(zielWinkel);
		ahead(distance * Math.cos(getAngle()));
	}

	public double getAngle() {
		int angleLvl = new Double(getHeading() / 90).intValue();
		double driveAngle = getHeading() - (angleLvl * 90);

		return driveAngle;
	}

	public void pointToRadar() {
		turnGunLeft(getRadarHeading()-getGunHeading());
		turnRadarRight(getRadarHeading() - getGunHeading());
	}
}
