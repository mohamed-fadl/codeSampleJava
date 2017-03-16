package com.perfecto.application;

import com.perfecto.infrastructure.enums.RobotLanguage;
import com.perfecto.robots.Robot;
import com.perfecto.rooms.Room;
import com.perfecto.rooms.CircularRoom;
import com.perfecto.infrastructure.exceptions.InstructionsException;
import com.perfecto.rooms.SquaredRoom;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InstructionsException {

        Room room;

        // first example
        room = new SquaredRoom(new Point(1,2),5);

        Robot robot = new Robot(room,"hghgghghg");
        robot.move();

        System.out.println(robot.getFinalState());

        //second example
        room  = new CircularRoom(new Point(0,0),10);

        robot = new Robot(room,"RRFLFFLRF");
        robot.setLanguage(RobotLanguage.ENGLISH);
        robot.move();

        System.out.println(robot.getFinalState());


    }
}
