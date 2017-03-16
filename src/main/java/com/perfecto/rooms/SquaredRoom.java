package com.perfecto.rooms;

import java.awt.*;

/**
 * Created by marji on 2017-03-13.
 */
public class SquaredRoom implements Room {

    Point startPosition;

    int length;

    public SquaredRoom(Point startPosition, int length) {

        // validate the dimensions and the start position
        if(length > 0 ){
            this.length = length;
            if(!contains(startPosition))
                throw new IllegalArgumentException("start position is out of boundary!");
            this.startPosition = startPosition;
        }else
            throw new IllegalArgumentException("Room length is invalid!");

    }

    public Point getStartPosition() {
        return startPosition;
    }

    public boolean contains(Point point) {

        // check if coordinates are valid and within the limits
        if(point.x >=0 && point.y >= 0)
            if(point.x < length && point.y < length)
                return true;

        return false;
    }


}
