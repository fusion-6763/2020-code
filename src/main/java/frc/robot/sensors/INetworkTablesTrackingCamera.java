package frc.robot.sensors;

public interface INetworkTablesTrackingCamera {
  /**
   * Gets X
   * 
   * @return
   */
  double getX();

  /**
   * Gets Y
   * 
   * @return
   */
  double getY();

  /**
   * Finds out if the block is valid
   * 
   * @return
   */
  boolean isValid();

  /**
   * Enables/disables driver mode
   * 
   * @param driverMode True for driver mode, false for tracking mode
   */
  void setDriverMode(boolean driverMode);

  /**
   * Switches the current pipeline
   * 
   * @param pipeline The pipeline to switch to.
   */
  void setPipeline(int pipeline);
}