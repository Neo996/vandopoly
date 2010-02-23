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

import org.vandopoly.ui.Display;

/*
 * Player class is a model class that represents a game player.
 * Context class for the State pattern
 * 
 * @author Allie Mazzia
 */
public class Player {
	
	private PlayerState state_;
	private String name_;
	private int cash_, positionOnBoard_;
	private boolean getOutOfJail_;
	
	public Player() {
		state_ = PlayerFree.Instance();
		name_ = "";
		cash_ = 0;
		positionOnBoard_ = 0;
		getOutOfJail_ = false;
	}
	
	void changeState(PlayerState newState) {
		state_ = newState;
	}
	
	public void movePiece(int numOfSpaces) {
		state_.movePiece(this, numOfSpaces);
	}
	
	public void collectRent(int space, Player payer) {
		state_.collectRent(this, space, payer);
	}
	
	public void goToJail() {
		state_.goToJail(this);
	}
	
	public void getOutOfJail() {
		state_.getOutOfJail(this);
	}
	
	public void updatePosition(int numOfSpaces) {
		positionOnBoard_ = numOfSpaces;
	}
	
	public void updateCash(int value) {
		cash_ = cash_ + value;
	}
	
	public void gainGetOutOfJail() {
		getOutOfJail_ = true;
	}
	
	public String getName() {
		return name_;
	}
	
	public int getPosition() {
		return positionOnBoard_;
	}
	
	public int getCash() {
		return cash_;
	}
	
	public PlayerState getState() {
		return state_;
	}
	
	public boolean hasGetOutOfJail() {
		return getOutOfJail_;
	}
	
}
