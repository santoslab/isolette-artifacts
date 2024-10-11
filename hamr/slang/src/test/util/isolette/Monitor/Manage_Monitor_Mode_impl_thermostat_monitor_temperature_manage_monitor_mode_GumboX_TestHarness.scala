// #Sireum

package isolette.Monitor

import org.sireum._
import isolette._
import isolette.GumboXUtil.GumboXResult

// Do not edit this file as it will be overwritten if HAMR codegen is rerun
@msig trait Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_GumboX_TestHarness extends Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_TestApi {
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
    val api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type = get_monitor_mode().get
    val lastMonitorMode: Isolette_Data_Model.Monitor_Mode.Type = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode.lastMonitorMode

    if (verbose) {
      println(st"""Post State Values:
                  |  api_monitor_mode = ${api_monitor_mode.string}
                  |  lastMonitorMode = ${lastMonitorMode.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_GumboX.inititialize_IEP_Post(lastMonitorMode, api_monitor_mode)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }

  def testComputeCBJ(json: String): GumboXResult.Type = {
    isolette.JSON.toMonitorManage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_PreState_Container(json) match {
      case Either.Left(o) => return testComputeCBV(o)
      case Either.Right(msg) => halt(msg.string)
    }
  }

  def testComputeCBV(o: Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_PreState_Container): GumboXResult.Type = {
    return testComputeCB(o.api_current_tempWstatus, o.api_interface_failure, o.api_internal_failure)
  }

  /** Contract-based test harness for the compute entry point
    * @param api_current_tempWstatus incoming data port
    * @param api_interface_failure incoming data port
    * @param api_internal_failure incoming data port
    */
  def testComputeCB(
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
      api_interface_failure: Isolette_Data_Model.Failure_Flag_impl,
      api_internal_failure: Isolette_Data_Model.Failure_Flag_impl): GumboXResult.Type = {

    // [SaveInLocal]: retrieve and save the current (input) values of GUMBO-declared local state variables as retrieved from the component state
    val In_lastMonitorMode: Isolette_Data_Model.Monitor_Mode.Type = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode.lastMonitorMode

    // [CheckPre]: check/filter based on pre-condition.
    //   manage_monitor_mode's compute entry point does not have top level assume clauses

    // [PutInPorts]: put values on the input ports
    put_current_tempWstatus(api_current_tempWstatus)
    put_interface_failure(api_interface_failure)
    put_internal_failure(api_internal_failure)

    if (verbose) {
      println(st"""Pre State Values:
                  |  In_lastMonitorMode = ${In_lastMonitorMode.string}
                  |  api_current_tempWstatus = ${api_current_tempWstatus.string}
                  |  api_interface_failure = ${api_interface_failure.string}
                  |  api_internal_failure = ${api_internal_failure.string}""".render)
    }

    // [InvokeEntryPoint]: invoke the entry point test method
    testCompute()

    // [RetrieveOutState]: retrieve values of the output ports via get operations and GUMBO declared local state variable
    val api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type = get_monitor_mode().get
    val lastMonitorMode: Isolette_Data_Model.Monitor_Mode.Type = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode.lastMonitorMode

    if (verbose) {
      println(st"""Post State Values:
                  |  api_monitor_mode = ${api_monitor_mode.string}
                  |  lastMonitorMode = ${lastMonitorMode.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_GumboX.compute_CEP_Post(In_lastMonitorMode, lastMonitorMode, api_current_tempWstatus, api_interface_failure, api_internal_failure, api_monitor_mode)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }

  def testComputeCBwLJ(json: String): GumboXResult.Type = {
    isolette.JSON.toMonitorManage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_PreState_Container_PS(json) match {
      case Either.Left(o) => return testComputeCBwLV(o)
      case Either.Right(msg) => halt(msg.string)
    }
  }

  def testComputeCBwLV(o: Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_PreState_Container_PS): GumboXResult.Type = {
    return testComputeCBwL(o.In_lastMonitorMode, o.api_current_tempWstatus, o.api_interface_failure, o.api_internal_failure)
  }

  /** Contract-based test harness for the compute entry point
    * @param In_lastMonitorMode pre-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_interface_failure incoming data port
    * @param api_internal_failure incoming data port
    */
  def testComputeCBwL(
      In_lastMonitorMode: Isolette_Data_Model.Monitor_Mode.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
      api_interface_failure: Isolette_Data_Model.Failure_Flag_impl,
      api_internal_failure: Isolette_Data_Model.Failure_Flag_impl): GumboXResult.Type = {

    // [CheckPre]: check/filter based on pre-condition.
    //   manage_monitor_mode's compute entry point does not have top level assume clauses

    // [PutInPorts]: put values on the input ports
    put_current_tempWstatus(api_current_tempWstatus)
    put_interface_failure(api_interface_failure)
    put_internal_failure(api_internal_failure)

    // [SetInStateVars]: set the pre-state values of state variables
    isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode.lastMonitorMode = In_lastMonitorMode

    if (verbose) {
      println(st"""Pre State Values:
                  |  In_lastMonitorMode = ${In_lastMonitorMode.string}
                  |  api_current_tempWstatus = ${api_current_tempWstatus.string}
                  |  api_interface_failure = ${api_interface_failure.string}
                  |  api_internal_failure = ${api_internal_failure.string}""".render)
    }

    // [InvokeEntryPoint]: invoke the entry point test method
    testCompute()

    // [RetrieveOutState]: retrieve values of the output ports via get operations and GUMBO declared local state variable
    val api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type = get_monitor_mode().get
    val lastMonitorMode: Isolette_Data_Model.Monitor_Mode.Type = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode.lastMonitorMode

    if (verbose) {
      println(st"""Post State Values:
                  |  api_monitor_mode = ${api_monitor_mode.string}
                  |  lastMonitorMode = ${lastMonitorMode.string}""".render)
    }

    // [CheckPost]: invoke the oracle function
    val postResult = isolette.Monitor.Manage_Monitor_Mode_impl_thermostat_monitor_temperature_manage_monitor_mode_GumboX.compute_CEP_Post(In_lastMonitorMode, lastMonitorMode, api_current_tempWstatus, api_interface_failure, api_internal_failure, api_monitor_mode)
    val result: GumboXResult.Type =
      if (!postResult) GumboXResult.Post_Condition_Fail
      else GumboXResult.Post_Condition_Pass

    return result
  }
}