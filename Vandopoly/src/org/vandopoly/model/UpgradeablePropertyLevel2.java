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
 * UpgradeablePropertyLevel2 class implements the behavior associated with the 
 * upgradeable property space being upgraded to level 2.
 * ConcreteState class for the State pattern.
 * 
 * @author Allie Mazzia
 */

public class UpgradeablePropertyLevel2 extends UpgradeablePropertyState {
	
	private static UpgradeablePropertyLevel2 INSTANCE = null;
	
	protected UpgradeablePropertyLevel2() {
		// Exists to disable instantiation
	}
	
	public static UpgradeablePropertyState Instance() {
		if (INSTANCE == null) {
			INSTANCE = new UpgradeablePropertyLevel2();
		}
		
		return INSTANCE;
	}

	public void landOn(Player player, UpgradeablePropertySpace property) {
		property.getOwner().collectRent(property.getRentValues()[2], player);
	}
	
	protected boolean isUpgradeable() {
		return true;
	}
	protected boolean isDowngradeable() {
		return true;
	}
	
	protected String getNameAndStatus() {
		return " (Level 2)";
	}
}