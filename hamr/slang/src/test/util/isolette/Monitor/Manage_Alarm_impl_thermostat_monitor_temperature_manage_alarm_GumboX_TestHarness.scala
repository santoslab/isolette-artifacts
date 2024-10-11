// #Sireum

package isolette.Monitor

import org.sireum._
import isolette._
import isolette.GumboXUtil.GumboXResult

// Do not edit this file as it will be overwritten if HAMR codegen is rerun
@msig trait Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX_TestHarness extends Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_TestApi {
  def verbose: B

  /** Contract-based test harness for the initialise entry point
    */
  def testInitialiseCB(
      ): GumboXResult.Type = {

    if (verbose) {
      println(st"""Pre State Values:
                  """.render)
    }

    // [InvokeEntryPoint]: invoke the entry point test method
    testInitialise()

    // [RetrieveOutState]: retrieve values of the output ports via get operations and GUMBO declared local state variable
    val api_alarm_control: Isolette_Data_Model.On_Off.Type = get_alarm_control().get
    val lastCmd: Isolette_Data_Model.On_Off.Type = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm.lastCmd

    if (verbose) {
      println(st"""Post State Values:
                  |  api_alarm_control = ${api_alarm_control.string}
                  |  lastCmd = ${lastCmd.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX.inititialize_IEP_Post(lastCmd, api_alarm_control)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }

  def testComputeCBJ(json: String): GumboXResult.Type = {
    isolette.JSON.toMonitorManage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_PreState_Container(json) match {
      case Either.Left(o) => return testComputeCBV(o)
      case Either.Right(msg) => halt(msg.string)
    }
  }

  def testComputeCBV(o: Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_PreState_Container): GumboXResult.Type = {
    return testComputeCB(o.api_current_tempWstatus, o.api_lower_alarm_temp, o.api_monitor_mode, o.api_upper_alarm_temp)
  }

  /** Contract-based test harness for the compute entry point
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    */
  def testComputeCB(
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_impl,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_impl): GumboXResult.Type = {

    // [SaveInLocal]: retrieve and save the current (input) values of GUMBO-declared local state variables as retrieved from the component state
    val In_lastCmd: Isolette_Data_Model.On_Off.Type = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm.lastCmd

    // [CheckPre]: check/filter based on pre-condition.
    val CEP_Pre_Result: B = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX.compute_CEP_Pre (In_lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp)
    if (!CEP_Pre_Result) {
      return GumboXResult.Pre_Condition_Unsat
    }

    // [PutInPorts]: put values on the input ports
    put_current_tempWstatus(api_current_tempWstatus)
    put_lower_alarm_temp(api_lower_alarm_temp)
    put_monitor_mode(api_monitor_mode)
    put_upper_alarm_temp(api_upper_alarm_temp)

    if (verbose) {
      println(st"""Pre State Values:
                  |  In_lastCmd = ${In_lastCmd.string}
                  |  api_current_tempWstatus = ${api_current_tempWstatus.string}
                  |  api_lower_alarm_temp = ${api_lower_alarm_temp.string}
                  |  api_monitor_mode = ${api_monitor_mode.string}
                  |  api_upper_alarm_temp = ${api_upper_alarm_temp.string}""".render)
    }

    // [InvokeEntryPoint]: invoke the entry point test method
    testCompute()

    // [RetrieveOutState]: retrieve values of the output ports via get operations and GUMBO declared local state variable
    val api_alarm_control: Isolette_Data_Model.On_Off.Type = get_alarm_control().get
    val lastCmd: Isolette_Data_Model.On_Off.Type = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm.lastCmd

    if (verbose) {
      println(st"""Post State Values:
                  |  api_alarm_control = ${api_alarm_control.string}
                  |  lastCmd = ${lastCmd.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX.compute_CEP_Post(In_lastCmd, lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }

  def testComputeCBwLJ(json: String): GumboXResult.Type = {
    isolette.JSON.toMonitorManage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_PreState_Container_PS(json) match {
      case Either.Left(o) => return testComputeCBwLV(o)
      case Either.Right(msg) => halt(msg.string)
    }
  }

  def testComputeCBwLV(o: Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_PreState_Container_PS): GumboXResult.Type = {
    return testComputeCBwL(o.In_lastCmd, o.api_current_tempWstatus, o.api_lower_alarm_temp, o.api_monitor_mode, o.api_upper_alarm_temp)
  }

  /** Contract-based test harness for the compute entry point
    * @param In_lastCmd pre-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    */
  def testComputeCBwL(
      In_lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_impl,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_impl): GumboXResult.Type = {

    // [CheckPre]: check/filter based on pre-condition.
    val CEP_Pre_Result: B = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX.compute_CEP_Pre (In_lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp)
    if (!CEP_Pre_Result) {
      return GumboXResult.Pre_Condition_Unsat
    }

    // [PutInPorts]: put values on the input ports
    put_current_tempWstatus(api_current_tempWstatus)
    put_lower_alarm_temp(api_lower_alarm_temp)
    put_monitor_mode(api_monitor_mode)
    put_upper_alarm_temp(api_upper_alarm_temp)

    // [SetInStateVars]: set the pre-state values of state variables
    isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm.lastCmd = In_lastCmd

    if (verbose) {
      println(st"""Pre State Values:
                  |  In_lastCmd = ${In_lastCmd.string}
                  |  api_current_tempWstatus = ${api_current_tempWstatus.string}
                  |  api_lower_alarm_temp = ${api_lower_alarm_temp.string}
                  |  api_monitor_mode = ${api_monitor_mode.string}
                  |  api_upper_alarm_temp = ${api_upper_alarm_temp.string}""".render)
    }

    // [InvokeEntryPoint]: invoke the entry point test method
    testCompute()

    // [RetrieveOutState]: retrieve values of the output ports via get operations and GUMBO declared local state variable
    val api_alarm_control: Isolette_Data_Model.On_Off.Type = get_alarm_control().get
    val lastCmd: Isolette_Data_Model.On_Off.Type = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm.lastCmd

    if (verbose) {
      println(st"""Post State Values:
                  |  api_alarm_control = ${api_alarm_control.string}
                  |  lastCmd = ${lastCmd.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Alarm_impl_thermostat_monitor_temperature_manage_alarm_GumboX.compute_CEP_Post(In_lastCmd, lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }
}
