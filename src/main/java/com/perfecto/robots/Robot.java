package com.perfecto.robots;

import com.perfecto.infrastructure.RegexHelper;
import com.perfecto.infrastructure.commands.BaseCommands;
import com.perfecto.infrastructure.commands.EnglishCommands;
import com.perfecto.infrastructure.commands.SwedishCommands;
import com.perfecto.infrastructure.enums.RobotDirection;
import com.perfecto.infrastructure.enums.RobotLanguage;
import com.perfecto.infrastructure.exceptions.InstructionsException;
import com.perfecto.rooms.Room;
import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * Created by marji on 2017-03-13.
 */
public class Robot {

    //default direction
    RobotDirection direction = RobotDirection.NORTH;
    Room room;

    String instructions;
    Point currentPosition;

    //default language
    RobotLanguage language = RobotLanguage.SWEDISH;

    // current configured commands
    BaseCommands usedCommands = new SwedishCommands();

    public Robot() {
    }

    public Robot(@NotNull Room room, String instructions)  {

        if(room == null)
            throw new IllegalArgumentException("room has not been initialized");

        if(instructions == null || instructions.isEmpty())
            throw new IllegalArgumentException("instructions can't be empty");

        this.room = room;
        this.instructions = instructions;
    }

    public void setLanguage(RobotLanguage language){

        this.language = language;

        // update the current configured commands
        if(language == RobotLanguage.ENGLISH)
            usedCommands = new EnglishCommands();
        else
            usedCommands = new SwedishCommands();
    }

    private void checkInstructionsValidity(String instructions) throws InstructionsException {

        // check if given instructions matches the valid set of commands
        if(language == RobotLanguage.SWEDISH){
            if(!instructions.toLowerCase().matches(RegexHelper.SWEDISH_LANGUAGE_COMMANDS) )
                throw new InstructionsException("invalid instructions");
        }else{
            if(!instructions.toLowerCase().matches(RegexHelper.ENGLISH_LANGUAGE_COMMANDS))
                throw new InstructionsException("invalid instructions");
        }

    }

    public void move() throws InstructionsException {

        instructions = instructions.toLowerCase();

        // check if the instructions are valid before apllying them
        checkInstructionsValidity(instructions);

        currentPosition = room.getStartPosition();

        // create all possible circular commands using current configured language
        RegexHelper.createCircularCommands(usedCommands);


        // apply the instructions one by one
        while(instructions.length()>0){

            // remove circular commands to save execution power
            removeCircularCommand();

            // if all instructions were circular commands, return the current position and direction of the robot
            if(instructions.isEmpty())
                return;

            char command = instructions.charAt(0);

            if(command != usedCommands.goForward())
                direction = changeDirection(command);
            else
                changePosition();
            instructions = instructions.substring(1);
        }
    }

    void removeCircularCommand() {

        String firstFourCommands="";
        String firstSixCommands="";
        String firstTwoCommands="";

        int length = instructions.length();


        if(instructions.length()>5)
                firstSixCommands = instructions.substring(0,6);
        if(instructions.length()>3)
            firstFourCommands = instructions.substring(0,4);
        if(instructions.length()>1)
            firstTwoCommands = instructions.substring(0,2);

        if(!firstSixCommands.isEmpty() &&RegexHelper.circularCommandsWithoutChangingPosition.contains(firstSixCommands))
            instructions = instructions.substring(6);
        else if(!firstFourCommands.isEmpty() &&RegexHelper.circularCommandsWithoutChangingPosition.contains(firstFourCommands))
            instructions = instructions.substring(4);
        else if(!firstTwoCommands.isEmpty() && RegexHelper.circularCommandsWithoutChangingPosition.contains(firstTwoCommands))
            instructions = instructions.substring(2);

        // if we removed some commands, check again if the next sequence of commands is circular or not
        if(length>instructions.length())
            removeCircularCommand();
     }

    private RobotDirection changeDirection(char command){

        // check the current direction and command and change the direction according to that

        if(direction == RobotDirection.NORTH && command == usedCommands.turnRight())
            return RobotDirection.EAST;
        if(direction == RobotDirection.NORTH && command == usedCommands.turnLeft())
            return RobotDirection.WEST;

        if(direction == RobotDirection.EAST && command == usedCommands.turnRight())
            return RobotDirection.SOUTH;
        if(direction == RobotDirection.EAST && command == usedCommands.turnLeft())
            return RobotDirection.NORTH;

        if(direction == RobotDirection.SOUTH && command == usedCommands.turnRight())
            return RobotDirection.WEST;
        if(direction == RobotDirection.SOUTH && command == usedCommands.turnLeft())
            return RobotDirection.EAST;

        if(direction == RobotDirection.WEST && command == usedCommands.turnRight())
            return RobotDirection.NORTH;
        if(direction == RobotDirection.WEST && command == usedCommands.turnLeft())
            return RobotDirection.SOUTH;

        return direction;
    }

    private void changePosition(){

        // set temporary position to check it the move is valid or not
        Point tempPosition = new Point(currentPosition);

        switch (direction){
            case NORTH:
                tempPosition.y--;
                break;
            case EAST:
                tempPosition.x++;
                break;
            case SOUTH:
                tempPosition.y++;
                break;
            case WEST:
                tempPosition.x--;
                break;

        }

        // if the move is valid, update robot's current position
        if(room.contains(tempPosition))
            currentPosition=tempPosition;
    }

    public String getFinalState(){
        return currentPosition.x + " " + currentPosition.y + " " +direction.toString().charAt(0);
    }
}
