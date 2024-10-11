// #Sireum

package isolette.Regulate

import org.sireum._
import art._
import isolette.SystemTestSuiteSlang.runtimeMonitorStream
import isolette._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

object Manage_Regulator_Interface_impl_thermostat_regulate_temperature_manage_regulator_interface_SystemTestAPI {
  /** helper method to set the values of all incoming ports
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_desired_tempWstatus incoming data port
    * @param api_regulator_mode incoming data port
    * @param api_upper_desired_tempWstatus incoming data port
    */
  def put_concrete_inputs(api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
                          api_lower_desired_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
                          api_regulator_mode: Isolette_Data_Model.Regulator_Mode.Type,
                          api_upper_desired_tempWstatus: Isolette_Data_Model.TempWstatus_impl): Unit = {
    put_current_tempWstatus(api_current_tempWstatus)
    put_lower_desired_tempWstatus(api_lower_desired_tempWstatus)
    put_regulator_mode(api_regulator_mode)
    put_upper_desired_tempWstatus(api_upper_desired_tempWstatus)
  }

  // setter for incoming data port
  def put_current_tempWstatus(value: Isolette_Data_Model.TempWstatus_impl): Unit = {
    Art.insertInInfrastructurePort(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.operational_api.current_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  // setter for incoming data port
  def put_lower_desired_tempWstatus(value: Isolette_Data_Model.TempWstatus_impl): Unit = {
    Art.insertInInfrastructurePort(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.operational_api.lower_desired_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  // setter for incoming data port
  def put_regulator_mode(value: Isolette_Data_Model.Regulator_Mode.Type): Unit = {
    Art.insertInInfrastructurePort(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.operational_api.regulator_mode_Id, Isolette_Data_Model.Regulator_Mode_Payload(value))
  }

  // setter for incoming data port
  def put_upper_desired_tempWstatus(value: Isolette_Data_Model.TempWstatus_impl): Unit = {
    Art.insertInInfrastructurePort(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.operational_api.upper_desired_tempWstatus_Id, Isolette_Data_Model.TempWstatus_impl_Payload(value))
  }

  def fetchContainer(): isolette.Regulate.Manage_Regulator_Interface_impl_thermostat_regulate_temperature_manage_regulator_interface_PostState_Container_PS = {
    if (runtimeMonitorStream.contains(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.id)) {
      val (_, postContainer_) = runtimeMonitorStream.get(Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.id).get
      return postContainer_.asInstanceOf[isolette.Regulate.Manage_Regulator_Interface_impl_thermostat_regulate_temperature_manage_regulator_interface_PostState_Container_PS]
    }
    else {
      assert(F, s"No post state recorded for ${Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.name}")
      halt(s"No post state recorded for ${Arch.isolette_single_sensor_Instance_thermostat_regulate_temperature_manage_regulator_interface.name}")
    }
  }

  def check_concrete_outputs(api_displayed_temp: Isolette_Data_Model.Temp_impl,
                             api_interface_failure: Isolette_Data_Model.Failure_Flag_impl,
                             api_lower_desired_temp: Isolette_Data_Model.Temp_impl,
                             api_regulator_status: Isolette_Data_Model.Status.Type,
                             api_upper_desired_temp: Isolette_Data_Model.Temp_impl): Unit = {
    var failureReasons: ISZ[ST] = ISZ()

    val actual_displayed_temp = get_api_displayed_temp()
    if (api_displayed_temp != actual_displayed_temp) {
      failureReasons = failureReasons :+ st"'displayed_temp' did not match expected.  Expected: $api_displayed_temp, Actual: $actual_displayed_temp"
    }
    val actual_interface_failure = get_api_interface_failure()
    if (api_interface_failure != actual_interface_failure) {
      failureReasons = failureReasons :+ st"'interface_failure' did not match expected.  Expected: $api_interface_failure, Actual: $actual_interface_failure"
    }
    val actual_lower_desired_temp = get_api_lower_desired_temp()
    if (api_lower_desired_temp != actual_lower_desired_temp) {
      failureReasons = failureReasons :+ st"'lower_desired_temp' did not match expected.  Expected: $api_lower_desired_temp, Actual: $actual_lower_desired_temp"
    }
    val actual_regulator_status = get_api_regulator_status()
    if (api_regulator_status != actual_regulator_status) {
      failureReasons = failureReasons :+ st"'regulator_status' did not match expected.  Expected: $api_regulator_status, Actual: $actual_regulator_status"
    }
    val actual_upper_desired_temp = get_api_upper_desired_temp()
    if (api_upper_desired_temp != actual_upper_desired_temp) {
      failureReasons = failureReasons :+ st"'upper_desired_temp' did not match expected.  Expected: $api_upper_desired_temp, Actual: $actual_upper_desired_temp"
    }

    assert(failureReasons.isEmpty, st"${(failureReasons, "\n")}".render)
  }

  def get_api_displayed_temp(): Isolette_Data_Model.Temp_impl = {
    return fetchContainer().api_displayed_temp
  }

  def get_api_interface_failure(): Isolette_Data_Model.Failure_Flag_impl = {
    return fetchContainer().api_interface_failure
  }

  def get_api_lower_desired_temp(): Isolette_Data_Model.Temp_impl = {
    return fetchContainer().api_lower_desired_temp
  }

  def get_api_regulator_status(): Isolette_Data_Model.Status.Type = {
    return fetchContainer().api_regulator_status
  }

  def get_api_upper_desired_temp(): Isolette_Data_Model.Temp_impl = {
    return fetchContainer().api_upper_desired_temp
  }
}