package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DifferentialDriveConstants;
import frc.robot.subsystems.DifferentialDriveSubsystem;

public class DifferentialDriveCommand extends Command {
    private final DifferentialDriveSubsystem drive;
    private final DoubleSupplier vx, omega;

    public DifferentialDriveCommand(DifferentialDriveSubsystem drive, DoubleSupplier vx, DoubleSupplier omega) {
        this.drive = drive;
        this.vx = vx;
        this.omega = omega;
        this.addRequirements(drive);
    }

    @Override
    public void execute() {
        ChassisSpeeds speed = new ChassisSpeeds(vx.getAsDouble() * DifferentialDriveConstants.MAX_SPEED, 0,
                omega.getAsDouble() * DifferentialDriveConstants.MAX_OMEGA);

        SmartDashboard.putNumber("vx", vx.getAsDouble() * DifferentialDriveConstants.MAX_SPEED);
        SmartDashboard.putNumber("w", omega.getAsDouble() * DifferentialDriveConstants.MAX_OMEGA);

        drive.drive(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}