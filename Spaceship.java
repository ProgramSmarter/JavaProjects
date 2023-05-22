//Ishan Singh, Spaceship Find the Middle Project AP CSA, 5/12/2023

import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class Spaceship extends BasicSpaceship {
    private Point center;

    public static void main(String[] args) {
        // "10.40.30.77" is typically the IP address of Mr. Stutler's projector computer
        // "FindTheMiddleShip" is the name of the current class
        TextClient.run("192.168.4.23", new Spaceship());
    }

    @Override
    public RegistrationData registerShip(int numImages, int worldWidth, int worldHeight) {
        center = new Point(worldWidth / 2.0, worldHeight / 2.0);
        // parameters are ship name, color of ship text (RGB), and index of image
        return new RegistrationData("spaceship", new Color(100, 200, 150), 0);
    }

    //method that gives commands for the ship to make
    @Override
    public ShipCommand getNextCommand(BasicEnvironment env) {

        //getting angle to center
        Point shipPos = env.getShipStatus().getPosition();
        int orientation = env.getShipStatus().getOrientation();
        int angleToCenter = shipPos.getAngleTo(center);
        angleToCenter = angleToCenter - orientation;

        //making it so angle is smallest it should be to turn to a booster
        angleToCenter = angleToCenter % 90;
        if (angleToCenter > 45) {
            angleToCenter -= 90;
        }

        //turning ship if it's not at correct angle yet
        if (angleToCenter != 0) {

            return new RotateCommand(angleToCenter);
        }

        //setting direction based on which booster needs to be used
        char direction;
        int fullAngle = shipPos.getAngleTo(center) - orientation;

        if (fullAngle == 0) {

            direction = 'B';
        }
        else if (fullAngle == 180) {

            direction = 'F';
        }
        else if (fullAngle == 90) {

            direction = 'R';
        }
        else {

            direction = 'L';
        }

        //ship brakes at a distance based on what speed it's at, if it's outside a radius of 220, it speeds up
        if (shipPos.getDistanceTo(center) > 220) {

            return new ThrustCommand(direction, 1.0, 1.0);
        }
        else if (shipPos.getDistanceTo(center) < 220 && env.getShipStatus().getSpeed() > 49) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 200 && env.getShipStatus().getSpeed() > 47) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 180 && env.getShipStatus().getSpeed() > 44) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 150 && env.getShipStatus().getSpeed() > 40) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 120 && env.getShipStatus().getSpeed() > 35) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 90 && env.getShipStatus().getSpeed() > 30) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 70 && env.getShipStatus().getSpeed() > 27) {

            return new BrakeCommand(0.0);
        }
        else if (shipPos.getDistanceTo(center) < 60 && env.getShipStatus().getSpeed() > 25) {

            return new BrakeCommand(0.0);
        }

        return new IdleCommand(0.1);
    }
}