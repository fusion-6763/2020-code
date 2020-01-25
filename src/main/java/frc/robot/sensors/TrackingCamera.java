/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.Link;
import io.github.pseudoresonance.pixy2api.links.SPILink;

/**
 * This class defines the Pixy camera functionality.
 */
public class TrackingCamera {
  private final Link _link = new SPILink();
  private final Pixy2 _pixy = Pixy2.createInstance(_link);
  private Block _biggestBlock = null;

  public TrackingCamera() {
    _pixy.init();
    _biggestBlock = getBiggestBlock();
  }

  public int getXCoordinate() {
    return _biggestBlock.getX();
  }

  public int getYCoordinate() {
    return _biggestBlock.getY();
  }

  public int getAngle() {
    return _biggestBlock.getAngle();
  }

  public int getWidth() {
    return _biggestBlock.getWidth();
  }

  public int getHeight() {
    return _biggestBlock.getHeight();
  }

  /**
   * This finds the largest target in the current frame.
   * 
   * @return The biggest target in the current frame.
   */
  public Block getBiggestBlock() {
    final int blockCount = _pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);

    if (blockCount <= 0) {
      return null;
    }

    final ArrayList<Block> blocks = _pixy.getCCC().getBlocks();
    Block largestBlock = null;
    for (Block block : blocks) {
      if (largestBlock == null) {
        largestBlock = block;
      } else if (block.getWidth() > largestBlock.getWidth()) {
        largestBlock = block;
      }
    }
    return largestBlock;
  }
}
