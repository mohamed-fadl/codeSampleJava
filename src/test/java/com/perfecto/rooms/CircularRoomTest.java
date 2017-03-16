package com.perfecto.rooms;

import com.perfecto.Helpers.RoomHelper;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by marji on 2017-03-14.
 */
public class CircularRoomTest extends RoomTest {

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithMinusLengthShouldThroughIllegalArgumentException() throws IllegalArgumentException {

        room = new CircularRoom(RoomHelper.getRandomPoint(),RoomHelper.getRoomInvalidDimension());

    }

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithInvalidStartPointShouldThroughIllegalArgumentException() throws IllegalArgumentException {

        room = new CircularRoom(new Point(6,6),RoomHelper.getRoomValidDimension());

    }

    @Test
    public void createRoomWithValidArgumentsShouldCreateRoom()  {

        CircularRoom room = new CircularRoom(RoomHelper.getRandomPoint(), RoomHelper.getRoomValidDimension());

        assertEquals(RoomHelper.getRoomValidDimension(), room.radius);
        assertEquals(RoomHelper.getRandomPoint(), room.getStartPosition());

    }

  }