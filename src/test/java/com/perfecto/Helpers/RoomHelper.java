package com.perfecto.Helpers;

import com.perfecto.rooms.CircularRoom;
import com.perfecto.rooms.SquaredRoom;

import java.awt.*;

/**
 * Created by marji on 2017-03-16.
 */
public class RoomHelper {

    private static final int ROOM_VALID_DIMENSION = 5;
    private static final int ROOM_INVALID_DIMENSION = -5;

    private static final Point RANDOM_POINT = new Point(0, 0);

    public static Point getRandomPoint() {
        return RANDOM_POINT;
    }

    public static SquaredRoom getSquaredRoom(Point point) {
        return new SquaredRoom(point, ROOM_VALID_DIMENSION);
    }

    public static CircularRoom getCircularRoom(Point point) {
        return new CircularRoom(point, ROOM_VALID_DIMENSION);
    }

    public static int getRoomValidDimension() {
        return ROOM_VALID_DIMENSION;
    }

    public static int getRoomInvalidDimension() {
        return ROOM_INVALID_DIMENSION;
    }

}
