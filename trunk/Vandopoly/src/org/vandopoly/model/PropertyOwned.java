/*****************************************************************************
 *   Copyright 2010 Vandopoly Team                                           *
 *   Licensed under the Apache License, Version 2.0 (the "License");         *
 *   you may not use this file except in compliance with the License.        *
 *   You may obtain a copy of the License at                                 *
 *                                                                           *
 *   http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                           *
 *   Unless required by applicable law or agreed to in writing, software     *
 *   distributed under the License is distributed on an "AS IS" BASIS,       *
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.*
 *   See the License for the specific language governing permissions and     *
 *   limitations under the License.                                          *
 ****************************************************************************/


package org.vandopoly.model;

import org.vandopoly.messaging.Notification;
import org.vandopoly.messaging.NotificationManager;

/*
 * PropertyOwned class implements the behavior associated with the 
 * property space being owned.
 * ConcreteState class for the State pattern.
 * 
 * @author Allie Mazzia
 */

public class PropertyOwned extends PropertySpaceState {
	
	private static PropertyOwned INSTANCE = null;
	
	protected PropertyOwned() {
		// Exists to disable instantiation
	}
	
	public static PropertyOwned Instance() {
		if (INSTANCE == null) {
			INSTANCE = new PropertyOwned();
		}
		
		return INSTANCE;
	}
	
	public void landOn(Player player, UpgradablePropertySpace property) {
		// TODO: Pay the man!
		System.out.println("Player "+player.getName()+" should pay "+property.getOwner().getName()
				+" rent from property "+property.getName());
	}

}