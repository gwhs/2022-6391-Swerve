package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

import java.util.function.DoubleSupplier;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoCommand(DrivetrainSubsystem m_drivetrainSubsystem) {
        this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory examplePath = PathPlanner.loadPath("Straight2", 0.5, 0.5);
        addCommands(new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)))),
                    new PPSwerveControllerCommand(
                        examplePath,
                        m_drivetrainSubsystem::getPose,
                        m_drivetrainSubsystem.getKinematics(),
                        new PIDController(1, 0, 0),
                        new PIDController(1, 0, 0),
                        m_drivetrainSubsystem.getThetaController(),
                        m_drivetrainSubsystem::setStates,
                        m_drivetrainSubsystem)
                    );
    }

}

    

   