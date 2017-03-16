package com.perfecto.Helpers;

import com.perfecto.infrastructure.enums.RobotLanguage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marji on 2017-03-14.
 */
public class RobotHelper {


    private static final String VALID_ENGLISH_INSTRUCTIONS = "rrflfflrf";
    private static final String INVALID_ENGLISH_INSTRUCTIONS = "rrfflfldccd";

    private static final String VALID_SWEDISH_INSTRUCTIONS = "hghgghghg";
    private static final String INVALID_SWEDISH_INSTRUCTIONS = "hgvvligkd";

    private static final String CIRCULAR_COMMANDS = "hhhhvvvhhhhvhvhhhvvhhvvvh";
    private static final String FULLY_CIRCULAR_COMMANDS = "hhhhvvvvhhhvvvhvhvhvhv";
    private static final String CIRCULAR_COMMANDS_REMOVED_RESULT = "hhhvvhhvvvh";

    private static final String ENGLISH_INSTRUCTIONS_BEYOND_LIMITS = "rfffffffll";

    private static final RobotLanguage SWEDISH_LANGUAGE= RobotLanguage.SWEDISH;
    private static final RobotLanguage ENGLISH_LANGUAGE= RobotLanguage.ENGLISH;

    private static final Point SWEDISH_TEST_CASE_START_POSITION = new Point(1,2);
    private static final Point SWEDISH_TEST_CASE_FINAL_POSITION = new Point(1,3);
    private static final Point ENGLISH_TEST_CASE_START_POSITION = new Point(0,0);
    private static final Point ENGLISH_TEST_CASE_FINAL_POSITION = new Point(3,1);

    private static final String CIRCULAR_COMMAND_SAMPLE_1 = "hhhh";
    private static final String CIRCULAR_COMMAND_SAMPLE_2= "hhhvvv";
    private static final String CIRCULAR_COMMAND_SAMPLE_3= "hv";
    private static final String CIRCULAR_COMMAND_SAMPLE_4= "hhvv";


    public static ArrayList<String> getCircularCommandSamples() {
        ArrayList<String> circularCommandsSamples = new ArrayList<String>();

        CIRCULAR_COMMAND_SAMPLES.add(CIRCULAR_COMMAND_SAMPLE_1);
        CIRCULAR_COMMAND_SAMPLES.add(CIRCULAR_COMMAND_SAMPLE_2);
        CIRCULAR_COMMAND_SAMPLES.add(CIRCULAR_COMMAND_SAMPLE_3);
        CIRCULAR_COMMAND_SAMPLES.add(CIRCULAR_COMMAND_SAMPLE_4);

        return circularCommandsSamples;
    }

    private static final ArrayList<String> CIRCULAR_COMMAND_SAMPLES = new ArrayList<String>();
    private static final String VALID_FINAL_STATE = "1 3 N";

    private static final Point ENGLISH_TEST_BEYOND_LIMITS_FINAL_POSITION = new Point(5,0);

    public static Point getSwedishTestCaseStartPosition() {
        return SWEDISH_TEST_CASE_START_POSITION;
    }

    public static Point getSwedishTestCaseFinalPosition() {
        return SWEDISH_TEST_CASE_FINAL_POSITION;
    }

    public static Point getEnglishTestCaseStartPosition() {
        return ENGLISH_TEST_CASE_START_POSITION;
    }

    public static Point getEnglishTestCaseFinalPosition() {
        return ENGLISH_TEST_CASE_FINAL_POSITION;
    }

    public static String getEnglishInstructionsBeyondLimits() {
        return ENGLISH_INSTRUCTIONS_BEYOND_LIMITS;
    }

    public static Point getEnglishTestBeyondLimitsFinalPosition() {
        return ENGLISH_TEST_BEYOND_LIMITS_FINAL_POSITION;
    }


    public static RobotLanguage getSwedishLanguage() {
        return SWEDISH_LANGUAGE;
    }

    public static RobotLanguage getEnglishLanguage() {
        return ENGLISH_LANGUAGE;
    }


    public static String getValidEnglishInstructions(){
        return VALID_ENGLISH_INSTRUCTIONS;
    }

    public static String getInvalidEnglishInstructions() {
        return INVALID_ENGLISH_INSTRUCTIONS;
    }

    public static String getValidSwedishInstructions() {
        return VALID_SWEDISH_INSTRUCTIONS;
    }

    public static String getInvalidSwedishInstructions() {
        return INVALID_SWEDISH_INSTRUCTIONS;
    }

    public static String getValidFinalState() {
        return VALID_FINAL_STATE;
    }

    public static String getCircularCommands() {
        return CIRCULAR_COMMANDS;
    }

    public static String getCircularCommandsRemovedResult() {
        return CIRCULAR_COMMANDS_REMOVED_RESULT;
    }

    public static String getFullyCircularCommands() {
        return FULLY_CIRCULAR_COMMANDS;
    }
}
