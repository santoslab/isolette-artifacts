package isolette.Monitor

import isolette.Isolette_Data_Model.{Monitor_Mode, Status, TempWstatus_impl, ValueStatus}
import isolette.{InitialValues, Isolette_Data_Model}
import org.sireum._

// This file will not be overwritten so is safe to edit
class Manage_Monitor_Interface_impl_thermostat_monitor_temperature_manage_monitor_interface_Test extends Manage_Monitor_Interface_impl_thermostat_monitor_temperature_manage_monitor_interface_ScalaTest {
  // helper function to check for expected concrete values for the interface
  def check_expected_interface_values(upper_alarm_temp: Isolette_Data_Model.Temp_impl,
                                      lower_alarm_temp: Isolette_Data_Model.Temp_impl,
                                      monitor_status: Isolette_Data_Model.Status.Type,
                                      interface_failure: Isolette_Data_Model.Failure_Flag_impl): Unit = {
    check_concrete_output(ut => ut == upper_alarm_temp, lt => lt == lower_alarm_temp,
      ms => ms == monitor_status, ifail => ifail == interface_failure)
  }

  //=============================================
  //  I n i t i a l i z e    EP   T e s t s
  //=============================================

  // Related to REQ-MMI-1
  // REQ-MMI-1: If the Manage Monitor Interface mode is INIT, the Monitor Status shall be
  // set to Init
  test("InitEP: Monitor Interface is initialized to Init"){

    testInitialise()

    // --------- outputs ----------
    check_expected_interface_values(
      Isolette_Data_Model.Temp_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE),
      Isolette_Data_Model.Temp_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE),
      Isolette_Data_Model.Status.Init_Status,
      Isolette_Data_Model.Failure_Flag_impl(F)
    )
  }

  //=============================================
  //  C o m p u t e    EP   T e s t s
  //=============================================

  // REQ-MMI-1: If the Manage Monitor Interface mode is INIT, the Monitor Status shall be
  // set to Init

  test("REQ-MMI-1: Init mode") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      // Relevant to requirement
      Monitor_Mode.Init_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      monitor_status = (s => s == Status.Init_Status),
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T,
      interface_failure = s => T
    )
  }

  // REQ-MMI-2: If the Manage Monitor Interface mode is NORMAL, the Monitor Status
  // shall be set to On.

  test("REQ-MMI-2: Normal Mode") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      // Relevant to requirement
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      monitor_status = (s => s == Status.On_Status),
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T,
      interface_failure = s => T)
  }

  // REQ-MMI-3: If the Manage Monitor Interface mode is FAILED, the Monitor Status
  // shall be set to Failed.

  test("REQ-MMI-3: Failed Mode") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      // Relevant to requirement
      Monitor_Mode.Failed_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      monitor_status = (s => s == Status.Failed_Status),
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T,
      interface_failure = s => T)
  }

  // REQ-MMI-4: If the Status attribute of the Lower Alarm Temperature or the Upper
  // Alarm Temperature is Invalid, the Monitor Interface Failure shall be set to True.

  test("REQ-MMI-4: Lower Temp Invalid") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      interface_failure = (f => f.value == T),
      monitor_status = s => T,
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T)
  }

  test("REQ-MMI-4: Upper Temp Invalid") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Invalid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      interface_failure = (f => f.value == T),
      monitor_status = s => T,
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T)
  }

  test("REQ-MMI-4: Both Temps Invalid") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Invalid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      interface_failure = (f => f.value == T),
      monitor_status = s => T,
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T)
  }

  // REQ-MMI-5: If the Status attribute of the Lower Alarm Temperature and the Upper
  // Alarm Temperature is Valid, the Monitor Interface Failure shall be set to False.

  test("REQ-MMI-5") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      interface_failure = (f => f.value == F),
      monitor_status = s => T,
      lower_alarm_temp = s => T,
      upper_alarm_temp = s => T)
  }

  // REQ-MMI-6: If the Monitor Interface Failure is False, the Alarm Range variable shall
  // be set to the Desired Temperature Range.
  // Interface Failure: (upper_alarm_status == Isolette_Data_Model.ValueStatus.Valid)
  // && (lower_alarm_status == Isolette_Data_Model.ValueStatus.Valid)

  test("REQ-MMI-6: No Monitor Interface Failure") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE, ValueStatus.Valid),
      TempWstatus_impl(InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(
      upper_alarm_temp = (u => u.value == (InitialValues.DEFAULT_UPPER_ALARM_TEMPERATURE)),
      lower_alarm_temp = (l => l.value == (InitialValues.DEFAULT_LOWER_ALARM_TEMPERATURE)),
      monitor_status = s => T,
      interface_failure = s => T)
  }

  // REQ-MMI-7: If the Monitor Interface Failure is True, the Alarm Range variable is
  // UNSPECIFIED.
}
