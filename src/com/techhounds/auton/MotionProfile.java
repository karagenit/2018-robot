package com.techhounds.auton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.techhounds.Constants;

/**
 * This class represents a motion profile to be loaded into
 * a MotionProfileExecutor command.
 * 
 * FIXME right now, it loads all of the profiles into Lists of TrajectoryPoint
 * objects - this will use lots of memory, but will likely make it very fast
 * to push points to the buffer. If necessary, getPoints() can instead read
 * the files on-the-fly (i.e. when the method is actually called) and construct
 * the points then - maybe we could even implement Iterator?
 * 
 * Also, it won't necessarily load all the data right when the program loads,
 * but rather when the class is 'Initialized' sometime later
 * 
 * Alternatives: Construct arrayList of objs only when getPoints is called for that one
 * (but still cache the ArrayList), preload double array but don't construct yet; serialize either double array
 * or arraylist to file and read that into memory; preload double array but let command handle
 * constructing the objects
 * 
 * TODO we need a spec for the CSV file format - current setup:
 * column number - data
 * 0 - position (rotations)
 * 1 - velocity (rot/s)
 * 2 - time interval (ms)
 * 
 * Of course, we'll need to at least duplicate 0 & 1 so we can have both right & left
 * side in a single file.
 */
public enum MotionProfile {
			
	Test("Test.csv");
	
	public final String filename;
	MotionProfile(String filename) {
		this.filename = filename;
	}
	
	public TrajectoryPointSequence getPoints() {
		TrajectoryPointSequence points = new TrajectoryPointSequence();

		try {

			BufferedReader file = new BufferedReader(new FileReader(new File(Constants.PROFILE_PATH + filename)));
			
			String line;
			while ((line = file.readLine()) != null) {
				String[] fields = line.split(",");
				
				if (fields.length != 4) continue;
				
				TrajectoryPoint right = makeTrajectoryPoint(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]));
				TrajectoryPoint left = makeTrajectoryPoint(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
				
				points.addPointPair(left, right);
			}		
			
			file.close();
		} catch (IOException e) {
			System.err.println("Failed to Load Profile at: " + filename);
		}
		
		if (!points.rightPoints.isEmpty()) {
			points.rightPoints.get(0).zeroPos = true;
			points.rightPoints.get(0).isLastPoint = true;
		}
		
		if (!points.leftPoints.isEmpty()) {
			points.leftPoints.get(0).zeroPos = true;
			points.leftPoints.get(0).isLastPoint = true;
		}
		
		return points;
	}
	
	public static TrajectoryPoint makeTrajectoryPoint(double position, double velocity) {
		TrajectoryPoint point = new TrajectoryPoint();
				
		//I think the 'recorder' saves them in talon units (encoder counts per 100 ms) so no conversion necessary
		point.position = position;
		point.velocity = velocity;
		point.profileSlotSelect0 = 0;
		point.profileSlotSelect1 = 0;
		point.timeDur = TrajectoryDuration.Trajectory_Duration_5ms;
		point.zeroPos = false;
		point.isLastPoint = false;
		
		return point;
	}
}
