package isolette.Monitor

import isolette.Isolette_Data_Model.{Monitor_Mode, TempWstatus_impl, Temp_impl, ValueStatus}
import isolette.{InitialValues, Isolette_Data_Model}

// This file will not be overwritten so is safe to edit
class Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_Test extends Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_ScalaTest {

  //=============================================
  //  I n i t i a l i z e    EP   T e s t s
  //=============================================

  // Related to REQ-MA-1: If the Monitor Mode is INIT, the Alarm Control shall be set to Off.
  test("InitEP: Alarm Control is initialized to Off"){

    testInitialise()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Off)
  }

  // REQ-MA-1: If the Monitor Mode is INIT, the Alarm Control shall be set to Off.
  test("Req-MA-1") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Temp_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE),
      Temp_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE),
      // Relevant to requirement
      Monitor_Mode.Init_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Off)
  }

  //  REQ-MA-2: If the Monitor Mode is NORMAL and the Current Temperature is less than
  //  the Lower Alarm Temperature or greater than the Upper Alarm Temperature, the Alarm
  //  Control shall be set to On.
  test("Req-MA-2_lower") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(96f, ValueStatus.Valid),
      Temp_impl(97f),
      // Independent of requirement
      Temp_impl(101f),
      // Relevant to requirement
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)
  }

  test("Req-MA-2_greater") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(105f, ValueStatus.Valid),
      // Independent of requirement
      Temp_impl(97f),
      // Relevant to requirement
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)
  }

  //  REQ-MA-3: If the Monitor Mode is NORMAL and the Current Temperature is greater
  //    than or equal to the Lower Alarm Temperature and less than the Lower Alarm
  //    Temperature +0.5°, or the Current Temperature is greater than the Upper Alarm
  //    Temperature -0.5° and less than or equal to the Upper Alarm Temperature, the value of
  //  the Alarm Control shall not be changed.
  test("Req-MA-3_under") {

    // Turn alarm to Onn due to undershot lower bound
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(96f, ValueStatus.Valid),
      Temp_impl(97f),
      // Independent of requirement
      Temp_impl(101f),
      // Relevant to requirement
      Monitor_Mode.Normal_Monitor_Mode
    )
    testCompute()
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)

    // Alarm should remain on even though temp is now within valid range
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(97.2f, ValueStatus.Valid),
      Temp_impl(97f),
      // Independent of requirement
      Temp_impl(101f),
      // Relevant to requirement
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)
  }

  test("Req-MA-3_over") {

    // Turn alarm to Onn due to exceeding upper bound
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(104f, ValueStatus.Valid),
      // Independent of requirement
      Temp_impl(97f),
      // Relevant to requirement
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )
    testCompute()
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)

    // Alarm should remain on even though temp is now within valid range
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(100.7f, ValueStatus.Valid),
      // Independent of requirement
      Temp_impl(97f),
      // Relevant to requirement
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)
  }

  //  REQ-MA-4: If the Monitor Mode is NORMAL and the value of the Current
  //    Temperature is greater than or equal to the Lower Alarm Temperature +0.5° and less than
  //  or equal to the Upper Alarm Temperature -0.5°, the Alarm Control shall be set to Off.
  test("Req-MA-4") {

    // Part 1: Too cold, alarm on -> off

    // Turn alarm to Onn due to undershot lower bound
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(96f, ValueStatus.Valid),
      Temp_impl(97f),
      // Independent of requirement
      Temp_impl(101f),
      // Relevant to requirement
      Monitor_Mode.Normal_Monitor_Mode
    )
    testCompute()
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)

    // Alarm should turn off because the temperature is solidly within range
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(98f, ValueStatus.Valid),
      Temp_impl(97f),
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Off)


    // Part 2: Too hot, alarm on -> off

    // Turn alarm to Onn due to exceeding upper bound
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(104f, ValueStatus.Valid),
      // Independent of requirement
      Temp_impl(97f),
      // Relevant to requirement
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )
    testCompute()
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)

    // Alarm should turn off because the temperature is solidly within range
    // --------- inputs ----------
    put_concrete_inputs(
      // Relevant to requirement
      TempWstatus_impl(100f, ValueStatus.Valid),
      Temp_impl(97f),
      Temp_impl(101f),
      Monitor_Mode.Normal_Monitor_Mode
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Off)
  }

  //  REQ-MA-5: If the Monitor Mode is FAILED, the Alarm Control shall be set to On.
  //  Rationale: A failed monitor cannot monitor the Current Temperature of the Isolette and
  //    the Alarm should be turned on.
  test("Req-MA-5") {
    // --------- inputs ----------
    put_concrete_inputs(
      // Independent of requirement
      TempWstatus_impl(InitialValues.DEFAULT_CURRENT_TEMPERATURE, ValueStatus.Valid),
      Temp_impl(InitialValues.DEFAULT_LOWER_DESIRED_TEMPERATURE),
      Temp_impl(InitialValues.DEFAULT_UPPER_DESIRED_TEMPERATURE),
      // Relevant to requirement
      Monitor_Mode.Failed_Monitor_Mode,
    )

    // execute compute entry point
    testCompute()

    // --------- outputs ----------
    check_concrete_output(c => c == Isolette_Data_Model.On_Off.Onn)
  }
}
