package com.perfecto.infrastructure;

import com.perfecto.infrastructure.commands.BaseCommands;

import java.util.ArrayList;

/**
 * Created by marji on 2017-03-14.
 */
public class RegexHelper {

    public static String ENGLISH_LANGUAGE_COMMANDS = "[rlf]+";
    public static String SWEDISH_LANGUAGE_COMMANDS = "[hvg]+";

    public static ArrayList<String> circularCommandsWithoutChangingPosition = new ArrayList<String>();

    public static void createCircularCommands(BaseCommands usedCommands) {

        char right = usedCommands.turnRight();
        char left = usedCommands.turnLeft();

        // full circle commands of only right and left
        circularCommandsWithoutChangingPosition.add(String.valueOf(right) + String.valueOf(right) + String.valueOf(right) + String.valueOf(right)); // hhhh
        circularCommandsWithoutChangingPosition.add(String.valueOf(left) + String.valueOf(left) + String.valueOf(left) + String.valueOf(left)); // vvvv

        // partially circular commands of X number of right commands followed by X  left commands and vice versa
        // e.g hhhvvv or vvhh etc..
        for (int i = 3; i > 0; i--) {
            String rightCommands = "";
            String leftCommands = "";

            for (int j = 0; j < i; j++) {
                rightCommands = rightCommands + String.valueOf(right);
                leftCommands = leftCommands + String.valueOf(left);
            }
            circularCommandsWithoutChangingPosition.add(rightCommands + leftCommands);
            circularCommandsWithoutChangingPosition.add(leftCommands + rightCommands);
        }

    }


}
