/*
*************************************************************************************
* Copyright 2011 Normation SAS
*************************************************************************************
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* In accordance with the terms of section 7 (7. Additional Terms.) of
* the GNU Affero GPL v3, the copyright holders add the following
* Additional permissions:
* Notwithstanding to the terms of section 5 (5. Conveying Modified Source
* Versions) and 6 (6. Conveying Non-Source Forms.) of the GNU Affero GPL v3
* licence, when you create a Related Module, this Related Module is
* not considered as a part of the work and may be distributed under the
* license agreement of your choice.
* A "Related Module" means a set of sources files including their
* documentation that, without modification of the Source Code, enables
* supplementary functions or services in addition to those offered by
* the Software.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/agpl.html>.
*
*************************************************************************************
*/

package com.normation.rudder.domain.eventlog


import com.normation.eventlog._
import scala.xml._
import com.normation.rudder.domain.policies._
import org.joda.time.DateTime
import net.liftweb.common._
import com.normation.utils.HashcodeCaching


sealed trait NodeEventLog extends EventLog { override final val eventLogCategory = NodeLogCategory }

final case class ModifyNodeHeartbeat(
    override val eventDetails : EventLogDetails
) extends NodeEventLog with HashcodeCaching {
  override val eventType = ModifyNodeHeartbeat.eventType
}

object ModifyNodeHeartbeat extends EventLogFilter {
  override val eventType = ModifyHeartbeatNodeEventType

  override def apply(x : (EventLogType, EventLogDetails)) : ModifyNodeHeartbeat = ModifyNodeHeartbeat(x._2)
}


final case class ModifyNodeAgentRun(
    override val eventDetails : EventLogDetails
) extends NodeEventLog with HashcodeCaching {
  override val eventType = ModifyNodeAgentRun.eventType
}

object ModifyNodeAgentRun extends EventLogFilter {
  override val eventType = ModifyAgentRunIntervalNodeEventType

  override def apply(x : (EventLogType, EventLogDetails)) : ModifyNodeAgentRun = ModifyNodeAgentRun(x._2)
}


final case class ModifyNodeProperties(
    override val eventDetails : EventLogDetails
) extends NodeEventLog with HashcodeCaching {
  override val eventType = ModifyNodeProperties.eventType
}

object ModifyNodeProperties extends EventLogFilter {
  override val eventType = ModifyPropertiesNodeEventType

  override def apply(x : (EventLogType, EventLogDetails)) : ModifyNodeProperties = ModifyNodeProperties(x._2)
}

object NodeEventLogsFilter {
  final val eventList : List[EventLogFilter] = List(
      ModifyNodeHeartbeat
    , ModifyNodeAgentRun
    , ModifyNodeProperties
    )
}
