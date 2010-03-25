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

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Vector;

import org.vandopoly.messaging.Notification;
import org.vandopoly.messaging.NotificationManager;
import org.vandopoly.ui.CardPanel;

/*
 * Model class that is a descendant of Space and represents a Community Chest
 * card space on the board
 * 
 * @author Allie Mazzia
 */
public class CommCardSpace extends Space {
	
	ArrayList<Player> players_;
	Vector<Card> stack_;
	ListIterator<Card> itr_;
	private static CommCardSpace INSTANCE = null;
	public static final int NUMBER = 2;

	protected CommCardSpace(ArrayList<Player> players) {
		stack_ = new Vector<Card>(NUMBER);
		
		stack_.add(new CardTypeOutOfJail());
		stack_.add(new CardTypeMove("Out late partying. Go directly to the Vandy"
				+ " Van Normal Route", 25));
		stack_.add(new CardTypePayFund("Buy a Rites of Spring ticket. Pay $30", 30));
		stack_.add(new CardTypeWinMoney("You've earned yourself a scholarship! " +
				"Collect $200", 200));
		stack_.add(new CardTypePayFund("ER visit! Pay VUMC $100", 100));
		
		players_ = players;
		itr_ = stack_.listIterator();
	}
	
	public static CommCardSpace Instance(ArrayList<Player> players) {
		if (INSTANCE == null) {
			INSTANCE = new CommCardSpace(players);
		}
		
		return INSTANCE;
	}
	
	public String toString() {
		return "Community Chest";
	}
	
	public void landOn(Player p) {
		Card card = drawCard();
		NotificationManager.getInstance().notifyObservers(Notification.SHOW_CARD, card);
		new CardPanel(card, p, players_, "Community Chest");
		
	}
	
	public Card drawCard() {
		
		if (itr_.hasNext()) {
			return itr_.next();
		}
		else {
			while(itr_.hasPrevious()) {
				itr_.previous();
			}
			return itr_.next();
		}
	}
	
	public void shuffleCards() {
		Collections.shuffle(stack_);
	}
	
/*	Debugging Code:
	public static void main(String[] args)  {
		CommCardSpace space = new CommCardSpace();
		space.shuffleCards();
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
		System.out.println(space.drawCard().getMessage());
	}
*/
}
