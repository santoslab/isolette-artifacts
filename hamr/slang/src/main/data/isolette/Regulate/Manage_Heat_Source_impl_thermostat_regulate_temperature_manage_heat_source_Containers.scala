// #Sireum

package isolette.Regulate

import org.sireum._
import isolette._
import isolette.util.Container

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

// containers for the pre and post state values of ports and state variables

@sig trait Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PreState_Container extends Container {
  def api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl
  def api_lower_desired_temp: Isolette_Data_Model.Temp_impl
  def api_regulator_mode: Isolette_Data_Model.Regulator_Mode.Type
  def api_upper_desired_temp: Isolette_Data_Model.Temp_impl
}

// container for incoming ports
@datatype class Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PreState_Container_P (
  val api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
  val api_lower_desired_temp: Isolette_Data_Model.Temp_impl,
  val api_regulator_mode: Isolette_Data_Model.Regulator_Mode.Type,
  val api_upper_desired_temp: Isolette_Data_Model.Temp_impl) extends Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PreState_Container

// container for incoming ports and state variables
@datatype class Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PreState_Container_PS (
  val In_lastCmd: Isolette_Data_Model.On_Off.Type,
  val api_current_tempWstatus: Isolette_Data_Model.TempWstatus_impl,
  val api_lower_desired_temp: Isolette_Data_Model.Temp_impl,
  val api_regulator_mode: Isolette_Data_Model.Regulator_Mode.Type,
  val api_upper_desired_temp: Isolette_Data_Model.Temp_impl) extends Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PreState_Container

@sig trait Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PostState_Container extends Container {
  def api_heat_control: Isolette_Data_Model.On_Off.Type
}

// container for outgoing ports
@datatype class Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PostState_Container_P (
  val api_heat_control: Isolette_Data_Model.On_Off.Type) extends Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PostState_Container

// container for outgoing ports and state variables
@datatype class Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PostState_Container_PS (
  val lastCmd: Isolette_Data_Model.On_Off.Type,
  val api_heat_control: Isolette_Data_Model.On_Off.Type) extends Manage_Heat_Source_impl_thermostat_regulate_temperature_manage_heat_source_PostState_Container