package isolette.Regulate

import isolette.Isolette_Data_Model.ValueStatus
import isolette.{InitialValues, Isolette_Data_Model}
import org.sireum._
import isolette.Regulate._

// This file will not be overwritten so is safe to edit
class Manage_Regulator_Mode_impl_thermostat_regulate_temperature_manage_regulator_mode_Test extends Manage_Regulator_Mode_impl_thermostat_regulate_temperature_manage_regulator_mode_ScalaTest {

  // helper function to check for expected concrete values for the interface
  def check_expected_mode_values(regulator_mode: Isolette_Data_Model.Regulator_Mode.Type): Unit = {
    check_concrete_output(rm => rm == regulator_mode)
  }

  //=============================================
  //  I n i t i a l i z e    EP   T e s t s
  //=============================================

  // REQ-MRI-1: The Regulator Mode shall initially be set to Init.
  test("InitEP & REQ-MRI-1: Regulator Mode is initialized to Init") {

    testInitialise()

    // --------- outputs ----------
    check_expected_mode_values(Isolette_Data_Model.Regulator_Mode.Init_Regulator_Mode)
  }

  //=============================================
  //  C o m p u t e    EP   T e s t s
  //=============================================

  // REQ-MRM-2: If the Regulator Mode is INIT and the Regulator Status is TRUE, the
  // Regulator Mode shall be set to Normal.
  // Regulator status: NOT(Regulator Interface Failure OR Regulator Internal Failure)
  // AND Current Temperature.Status = Valid
  test("REQ-MRM-2: Regulator Mode Init, Status True"){
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    val regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)
  }

  // REQ-MRM-3: If the Regulator Mode is NORMAL and the Regulator Status is FALSE, the
  // Regulator Mode shall be set to Failed.
  test("REQ-MRM-3: Temperature Invalid") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.Failure_Flag_impl(F),
      Isolette_Data_Model.Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Interface Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(T),
      Isolette_Data_Model.Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Internal Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(F),
      Isolette_Data_Model.Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Temperature Invalid, Interface Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.Failure_Flag_impl(T),
      Isolette_Data_Model.Failure_Flag_impl(F)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Temperature Invalid, Internal Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.Failure_Flag_impl(F),
      Isolette_Data_Model.Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Interface Failure, Internal Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(T),
      Isolette_Data_Model.Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  test("REQ-MRM-3: Temperature Invalid, Regulator Interface Failure, Regulator Internal Failure") {
    // SETUP for test
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.Failure_Flag_impl(false),
      Isolette_Data_Model.Failure_Flag_impl(false)
    )

    // execute compute entry point
    testCompute()

    // Ensures the Regulator Mode is Normal before proceeding with testing
    var regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Normal_Regulator_Mode)

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.Failure_Flag_impl(T),
      Isolette_Data_Model.Failure_Flag_impl(T)
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    regulator_mode = get_regulator_mode().get
    assert(regulator_mode == Isolette_Data_Model.Regulator_Mode.Failed_Regulator_Mode)
  }

  // REQ-MRM-4: If the Regulator Mode is INIT and the process times out,
  // Regulator Mode shall be set to Failed.
  // Timing function not yet implemented
}
