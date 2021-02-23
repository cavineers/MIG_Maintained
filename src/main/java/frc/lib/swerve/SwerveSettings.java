package frc.lib.swerve;

import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class SwerveSettings {
    private int m_rotationMotorID;
    private int m_driveMotorID;
    private int m_encoderID;
    private Rotation2d m_offset;
    private boolean m_inverted = false;
    private String m_commonName = "";

    public SwerveSettings() {}

    public SwerveSettings setRotationMotorID(int id) {
        this.m_rotationMotorID = id;
        return this;
    }

    public SwerveSettings setDriveMotorID(int id) {
        this.m_driveMotorID = id;
        return this;
    }

    public SwerveSettings setRotationEncoderID(int id) {
        this.m_encoderID = id;
        return this;
    }

    public SwerveSettings setRotationOffset(Rotation2d offset) {
        this.m_offset = offset;
        return this;
    }

    public SwerveSettings setInverted(boolean inverted) {
        this.m_inverted = inverted;
        return this;
    }

    public SwerveSettings setCommonName(String name) {
        this.m_commonName = name;
        return this;
    }

    public int getRotationMotorID() {
        return this.m_rotationMotorID;
    }

    public int getDriveMotorID() {
        return this.m_driveMotorID;
    }

    public int getEncoderID() {
        return this.m_encoderID;
    }

    public Rotation2d getRotationOffset() {
        return this.m_offset;
    }

    public boolean isInverted() {
        return this.m_inverted;
    }

    public String commonName() {
        return this.m_commonName;
    }
}