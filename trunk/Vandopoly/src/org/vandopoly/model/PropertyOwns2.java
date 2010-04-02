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

/*
 * PropertyOwns2 class implements the behavior associated with the 
 * property space being owned by a player who also owns another property
 * space of the same kind.
 * ConcreteState class for the State pattern.
 * 
 * @author Allie Mazzia
 */
public class PropertyOwns2 extends PropertyState{

	private static PropertyOwns2 INSTANCE = null;
	
	protected PropertyOwns2() {
		// Exists to disable instantiation
	}
	
	public static PropertyState Instance() {
		if (INSTANCE == null) {
			INSTANCE = new PropertyOwns2();
		}
		
		return INSTANCE;
	}

	public void landOn(Player player, PropertySpace property) {
		//property.getOwner().collectRent(property.getRentValues()[1], player);
	}
}