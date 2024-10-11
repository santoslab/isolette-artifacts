package isolette.Regulate

import isolette.Isolette_Data_Model.{Failure_Flag_impl, Regulator_Mode, ValueStatus}
import isolette.{InitialValues, Isolette_Data_Model}
import org.sireum._

// This file will not be overwritten so is safe to edit
class Manage_Regulator_Interface_impl_thermostat_regulate_temperature_manage_regulator_interface_Test extends Manage_Regulator_Interface_impl_thermostat_regulate_temperature_manage_regulator_interface_ScalaTest {

  // helper function to check for expected concrete values for the interface
  def check_expected_interface_values(upper_desired_temp: Isolette_Data_Model.Temp_impl,
                                      lower_desired_temp: Isolette_Data_Model.Temp_impl,
                                      displayed_temp: Isolette_Data_Model.Temp_impl,
                                      regulator_status: Isolette_Data_Model.Status.Type,
                                      interface_failure: Isolette_Data_Model.Failure_Flag_impl): Unit = {
    check_concrete_output(ut => ut == upper_desired_temp, lt => lt == lower_desired_temp,
      dt => dt == displayed_temp, rs => rs == regulator_status, ifail => ifail == interface_failure)
  }

  //=============================================
  //  I n i t i a l i z e    EP   T e s t s
  //=============================================

  // Related to Req-MRI-1
  // REQ-MRI-1: If the Regulator Mode is INIT, the Regulator Status shall be set to Init.
  test("InitEP: Regulator Interface is initialized to Init"){

    testInitialise()

    // --------- outputs ----------
    check_expected_interface_values(Isolette_Data_Model.Temp_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE),
      Isolette_Data_Model.Temp_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE),
      Isolette_Data_Model.Temp_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE),
      Isolette_Data_Model.Status.Init_Status,
      Isolette_Data_Model.Failure_Flag_impl(F))
  }

  //=============================================
  //  C o m p u t e    EP   T e s t s
  //=============================================

  // REQ-MRI-1: If the Regulator Mode is INIT, the Regulator Status shall be set to Init.
  test("REQ-MRI-1: Init mode"){
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      // Relevant to requirement
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    val regulator_status = get_regulator_status().get
    assert(regulator_status == Isolette_Data_Model.Status.Init_Status)
  }

  // REQ-MRI-2: If the Regulator Mode is NORMAL, the Regulator Status shall be set to On.
  test("REQ-MRI-2: Normal mode") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      // Relevant to requirement
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    val regulator_status = get_regulator_status().get
    assert(regulator_status == Isolette_Data_Model.Status.On_Status)
  }

  // REQ-MRI-3: If the Regulator Mode is FAILED, the Regulator Status shall be set to Failed.
  test("REQ-MRI-3: Failed mode") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      // Relevant to requirement
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    val regulator_status = get_regulator_status().get
    assert(regulator_status == Isolette_Data_Model.Status.Failed_Status)
  }

  // REQ-MRI-4: If the Regulator Mode is NORMAL, the Display Temperature shall be set
  // to the value of the Current Temperature rounded to the nearest integer.
  test("REQ-MRI-4: Normal, Display Temp") {

    // Part 1: Checks for rounding down

    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(98.4F, ValueStatus.Valid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    var displayed_temp = Math.round(get_displayed_temp().get.value.value)
    assert(displayed_temp == 98)


    // Part 2: Checks for rounding up

    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(98.6F, ValueStatus.Valid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    displayed_temp = Math.round(get_displayed_temp().get.value.value)
    assert(displayed_temp == 99)
  }

  // REQ-MRI-5: If the Regulator Mode is not NORMAL, the value of the Display
  // Temperature is UNSPECIFIED.

  // REQ-MRI-6: If the Status attribute of the Lower Desired Temperature or the Upper
  // Desired Temperature is Invalid, the Regulator Interface Failure shall be set to True.
  test("REQ-MRI-6: Desired Temperature(s) Invalid") {

    // ------------------------------
    // Section 1 - Init Regulator Mode
    // ------------------------------

    // Part 1: Upper Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    var interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 2: Lower Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 3: Both Bounds Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // ---------------------------------
    // Section 2 - Normal Regulator Mode
    // ---------------------------------

    // Part 1: Upper Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 2: Lower Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 3: Both Bounds Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // ---------------------------------
    // Section 3 - Failed Regulator Mode
    // ---------------------------------

    // Part 1: Upper Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 2: Lower Bound Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))


    // Part 3: Both Bounds Invalid

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Invalid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(T))
  }

  // REQ-MRI-7: If the Status attribute of the Lower Desired Temperature and the Upper
  // Desired Temperature is Valid, the Regulator Interface Failure shall be set to False.
  test("REQ-MRI-7: Desired Temperatures Valid") {

    // Part 1: Init Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    var interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(F))


    // Part 2: Normal Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(F))


    // Part 3: Failed Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    interface_failure = get_interface_failure().get
    assert(interface_failure == Failure_Flag_impl(F))
  }

  // REQ-MRI-8: If the Regulator Interface Failure is False, the Desired Range shall be set to
  // the Desired Temperature Range.
  test("REQ-MRI-8: No Regulator Interface Failure") {

    // Part 1: Init Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(100F, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(98F, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Init_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    var upper_temp = get_upper_desired_temp().get
    var lower_temp = get_lower_desired_temp().get
    assert(upper_temp.value.value == 100)
    assert(lower_temp.value.value == 98)


    // Part 2: Normal Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(100F, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(98F, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Normal_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    upper_temp = get_upper_desired_temp().get
    lower_temp = get_lower_desired_temp().get
    assert(upper_temp.value.value == 100)
    assert(lower_temp.value.value == 98)


    // Part 3: Failed Regulator Mode

    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      Isolette_Data_Model.TempWstatus_impl(100F, ValueStatus.Valid),
      Isolette_Data_Model.TempWstatus_impl(98F, ValueStatus.Valid),
      // Independent of requirement
      Isolette_Data_Model.TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Invalid),
      Regulator_Mode.Failed_Regulator_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    upper_temp = get_upper_desired_temp().get
    lower_temp = get_lower_desired_temp().get
    assert(upper_temp.value.value == 100)
    assert(lower_temp.value.value == 98)
  }

  // REQ-MRI-9: If the Regulator Interface Failure is True, the Desired Range is
  // UNSPECIFIED.
}
