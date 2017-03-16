package com.perfecto.infrastructure.commands;

/**
 * Created by marji on 2017-03-14.
 */
public class SwedishCommands implements BaseCommands{
    public char turnRight() {
        return 'h';
    }

    public char turnLeft() {
        return 'v';
    }

    public char goForward() {
        return 'g';
    }

}
