package com.perfecto.robots;

import com.perfecto.Helpers.RobotHelper;
import com.perfecto.Helpers.RoomHelper;
import com.perfecto.infrastructure.RegexHelper;
import com.perfecto.infrastructure.commands.SwedishCommands;
import com.perfecto.infrastructure.enums.RobotDirection;
import com.perfecto.infrastructure.enums.RobotLanguage;
import com.perfecto.infrastructure.exceptions.InstructionsException;
import com.perfecto.rooms.Room;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by marji on 2017-03-13.
 */
public class RobotTest {

    Robot robot;

    private void initializeRobot(){
        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()),RobotHelper.getValidSwedishInstructions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRobotWithNullRoomShouldThrowException()  {

        robot = new Robot(null, RobotHelper.getValidSwedishInstructions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRobotWithNullInstructionsShouldThrowException()  {

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRobotWithEmptyInstructionsShouldThrowException()  {

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), new String());
    }

    // in a robot with many available languages we should use parametrized tests rather than this simple form of a test
    @Test(expected = InstructionsException.class)
    public void setInvalidSwedishInstructionsShouldThrowException() throws InstructionsException {

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), RobotHelper.getInvalidSwedishInstructions());
        robot.move();
    }

    @Test(expected = InstructionsException.class)
    public void setInvalidEnglishInstructionsShouldThrowException() throws InstructionsException {

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), RobotHelper.getInvalidEnglishInstructions());
        robot.setLanguage(RobotLanguage.ENGLISH);
        robot.move();
    }

    @Test
    public void provideValidArgumentsValuesShouldInitiateRobotCorrectly() throws InstructionsException {

        Room room = RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint());
        String instructions = RobotHelper.getValidEnglishInstructions();

        robot = new Robot(room,instructions);

        assertEquals(room,robot.room);
        assertEquals(instructions,robot.instructions);

    }

    @Test
    public void setTheLanguageShouldChangeTheCurrentLanguage() throws InstructionsException {

        initializeRobot();

        RobotLanguage englishLanguage = RobotHelper.getEnglishLanguage();
        RobotLanguage swedishLanguage = RobotHelper.getSwedishLanguage();

        assertEquals(swedishLanguage,robot.language);

        robot.setLanguage(englishLanguage);

        assertEquals(englishLanguage,robot.language);
    }

    /*
       in an application with many different types of rooms, we should use parametrized
       tests to test multiple cases rather than using this simple form
    */
    @Test
    public void moveRobotInSquareRoomWithSwedishInstructionsShouldGiveTheExpectedResults() throws InstructionsException {

        moveRobotWithValidSwedishInstructions();

        assertEquals(RobotHelper.getSwedishTestCaseFinalPosition(),robot.currentPosition);
    }

    private void moveRobotWithValidSwedishInstructions() throws InstructionsException {
        robot = new Robot(RoomHelper.getSquaredRoom(RobotHelper.getSwedishTestCaseStartPosition()), RobotHelper.getValidSwedishInstructions());
        robot.move();
    }

    @Test
    public void moveRobotInCircularRoomWithEnglishInstructionsShouldGiveTheExpectedResults() throws InstructionsException {


        robot = new Robot(RoomHelper.getCircularRoom(RobotHelper.getEnglishTestCaseStartPosition()), RobotHelper.getValidEnglishInstructions());
        robot.setLanguage(RobotLanguage.ENGLISH);
        robot.move();

        assertEquals(RobotHelper.getEnglishTestCaseFinalPosition(),robot.currentPosition);
    }

    @Test
    public void moveRobotInCircularRoomBeyondEdgesShouldStopByTheEdgesOfTheRoom() throws InstructionsException {

        robot = new Robot(RoomHelper.getCircularRoom(RoomHelper.getRandomPoint()), RobotHelper.getEnglishInstructionsBeyondLimits());
        robot.setLanguage(RobotLanguage.ENGLISH);
        robot.move();

        assertEquals(RobotHelper.getEnglishTestBeyondLimitsFinalPosition(),robot.currentPosition);
    }

    @Test
    public void robotFinalStateShouldBeInCorrectFormat() throws InstructionsException {
        moveRobotWithValidSwedishInstructions();

        assertEquals(RobotHelper.getValidFinalState(), robot.getFinalState());
    }

    @Test
    public void removeCircularCommandsShouldRemoveFirstMultipleSequencesOfCircularCommands()  {

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), RobotHelper.getCircularCommands());
        RegexHelper.createCircularCommands(new SwedishCommands());

        robot.removeCircularCommand();

        assertEquals(RobotHelper.getCircularCommandsRemovedResult(),robot.instructions);
    }

    @Test
    public void moveRobotWithoutRemovingCircularCommandsShouldEndInSameStateAsRobotWhichRemovedCircularCommands() throws InstructionsException {

        /*
         final state of a robot which removed circular commands and a robot which didn't remove circular commands
         should be the same
        */

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), RobotHelper.getCircularCommands());

        Robot robot2 = spy(Robot.class);
        robot2.instructions = RobotHelper.getCircularCommands();
        robot2.room = RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint());

        doNothing().when(robot2).removeCircularCommand();

        robot.move();
        robot2.move();

        assertEquals(robot2.getFinalState(),robot.getFinalState());
    }

    @Test
    public void moveRobotWithEmptyInstructionsAfterRemovingCircularCommandsShouldResultInStartPositionAndDirection() throws InstructionsException {

        // final state of robot which has instructions contain only circular commands should
        // result in final state that has start position as final position and
        // direction North as final direction

        robot = new Robot(RoomHelper.getSquaredRoom(RoomHelper.getRandomPoint()), RobotHelper.getFullyCircularCommands());

        robot.move();

        assertEquals(RobotDirection.NORTH,robot.direction);
        assertEquals(RoomHelper.getRandomPoint(),robot.currentPosition);

    }

    @Test(expected = InstructionsException.class)
    public void changingRobotLanguageAfterSettingInstructionsShouldThrowException() throws InstructionsException {

        initializeRobot();

        robot.setLanguage(RobotLanguage.ENGLISH);
        robot.move();
    }
}