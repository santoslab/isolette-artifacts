package isolette.Monitor

import isolette.Isolette_Data_Model.{Failure_Flag_impl, Monitor_Mode, TempWstatus_impl, ValueStatus}
import isolette.{InitialValues, Isolette_Data_Model}
import org.sireum._

// This file will not be overwritten so is safe to edit
class Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_Test extends Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_ScalaTest {

  // helper function to check for expected concrete values for the system
  def check_expected_mode_values(monitor_mode: Isolette_Data_Model.Monitor_Mode.Type): Unit = {
    check_concrete_output(mm => mm == monitor_mode)
  }

  //=============================================
  //  I n i t i a l i z e    EP   T e s t s
  //=============================================

  // REQ-MMM-1: The Monitor Mode shall initially be set to Init.
  test("InitEP & REQ-MMM-1: Monitor Mode is initialized to Init"){

    testInitialise()

    // --------- outputs ----------
    check_expected_mode_values(Isolette_Data_Model.Monitor_Mode.Init_Monitor_Mode)
  }

  //=============================================
  //  C o m p u t e    EP   T e s t s
  //=============================================

  // REQ-MMM-2: If the Monitor Mode is Init and the Monitor Status is True, the
  // Monitor Mode shall be set to Normal.
  // Monitor Status: NOT (Monitor Interface Failure OR Monitor Internal Failure)
  // AND Current Temperature.Status = Valid
  test("REQ-MMM-2: Monitor Status True") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)
  }

  // Related to REQ-MMM-3; If the Monitor Mode is Normal and Monitor Status is Valid,
  // the Monitor Mode should stay Normal.
  test("REQ-MMM-3_Valid") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)
  }

  // REQ-MMM-3: If the Monitor Mode is Normal and the Monitor Status is Invalid, the
  // Monitor Mode shall be set to Failed
  test("REQ-MMM-3: Temp Status Invalid") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Interface Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(T),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Internal Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Temp Status Invalid, Interface Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Failure_Flag_impl(T),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Temp Status Invalid, Internal Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Interface Failure, Internal Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(T),
      Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  test("REQ-MMM-3: Temp Status Invalid, Interface Failure, Internal Failure") {

    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Failure_Flag_impl(F),
      Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Monitor Mode is Normal before proceeding with testing
    check_concrete_output(c => c == Monitor_Mode.Normal_Monitor_Mode)

    // Test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Failure_Flag_impl(T),
      Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Monitor_Mode.Failed_Monitor_Mode)
  }

  // REQ-MMM-4: If the Monitor Mode is INIT and the process times out,
  // Regulator Mode shall be set to Failed.
  // Timing function not yet implemented
}
