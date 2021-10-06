// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.mach.LightDrive.*;
import java.awt.Color;
import java.util.Map;



// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Lights extends SubsystemBase {

    LightDriveCAN lightController;
    public NetworkTableEntry custom_red;
    public NetworkTableEntry custom_green;
    public NetworkTableEntry custom_blue;
    public NetworkTableEntry custom_toggle;
    public NetworkTableEntry alliance_input;
    private SendableChooser alliance_chooser;
    public NetworkTableEntry is_red_alliance;

    public Lights() {
        lightController = new LightDriveCAN();
        custom_red = Shuffleboard.getTab("Lights").add("Custom Color Red", 1f).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0f, "max", 1f)).getEntry();
        custom_green = Shuffleboard.getTab("Lights").add("Custom Color Green", 1f).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0f, "max", 1f)).getEntry();
        custom_blue = Shuffleboard.getTab("Lights").add("Custom Color Blue", 1f).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0f, "max", 1f)).getEntry();
        custom_toggle = Shuffleboard.getTab("Lights").add("Use Custom Color?", true).withWidget(BuiltInWidgets.kToggleSwitch).getEntry();
        is_red_alliance = NetworkTableInstance.getDefault().getTable("FMSInfo").getEntry("IsRedAlliance");

        alliance_chooser = new SendableChooser<String>();
        alliance_chooser.setDefaultOption("Red", "red");
        alliance_chooser.addOption("Blue", "blue");

        Shuffleboard.getTab("Lights").add("Alliance Color",alliance_chooser);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    

    public void setColors(Color color, int bank, float brightness){
        try{if(bank > 4 || bank < 1){
            throw new Exception("ERROR: invalid bank");
            }
        }catch(Exception e){
            return;
        }
        lightController.SetColor(bank, color, brightness);
        lightController.Update();
        
    }

    public Color getSelectedColor(){
        if(custom_toggle.getBoolean(false)){
            return new Color((float)custom_red.getDouble(1),(float)custom_green.getDouble(1),(float)custom_blue.getDouble(1));
        }
        if(is_red_alliance.getBoolean(alliance_chooser.getSelected() == "red")){
            System.out.println("displaying red!");
        }
        return Constants.allianceBlueColor;
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}

