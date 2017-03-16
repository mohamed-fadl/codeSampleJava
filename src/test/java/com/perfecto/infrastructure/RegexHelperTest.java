package com.perfecto.infrastructure;

import com.perfecto.Helpers.RobotHelper;
import com.perfecto.infrastructure.commands.SwedishCommands;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by marji on 2017-03-16.
 */

public class RegexHelperTest {

    @Test
    public void languageRegextShouldMatchValidInstructions() {

        assertTrue(RobotHelper.getValidSwedishInstructions().toLowerCase().matches(RegexHelper.SWEDISH_LANGUAGE_COMMANDS));
    }

    @Test
    public void circularCommandsListShouldContainValidCircularCommands() {

        RegexHelper.createCircularCommands(new SwedishCommands());

        ArrayList<String> circularCommandsSamples = RobotHelper.getCircularCommandSamples();

        for(int i=0;i<circularCommandsSamples.size();i++){
            assertTrue(RegexHelper.circularCommandsWithoutChangingPosition.contains(circularCommandsSamples.get(i)));
        }
    }

}