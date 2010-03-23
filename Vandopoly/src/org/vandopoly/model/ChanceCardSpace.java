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
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import org.vandopoly.messaging.Notification;
import org.vandopoly.messaging.NotificationManager;
import org.vandopoly.ui.CardPanel;
import org.vandopoly.ui.PropertySelectionPanel;

/*
 * Model class that is a descendant of Space and represents a 
 * chance space on the board
 * 
 * @author Allie Mazzia
 */
public class ChanceCardSpace extends Space {
	
	Vector<Card> stack_;
	ListIterator itr;
	ArrayList<Player> players_;
	private static ChanceCardSpace INSTANCE = null;
	public static final int NUMBER = 5;

	protected ChanceCardSpace(ArrayList<Player> players) {
		stack_ = new Vector<Card>(NUMBER);
		
		stack_.add(new CardTypeOutOfJail());
		stack_.add(new CardTypeWinMoney("You win $40!", 40));
		stack_.add(new CardTypePayPlayers("Awarded Student Body President. " +
				"Pay players $20 each.", 20));
		stack_.add(new CardTypePayFund("It's a new semester! Pay $200 for books.", 200));
		stack_.add(new CardTypeMove("Caught cheating on a test. You are" +
				" immediately placed on Academic Probation", 10));
		
		players_ = players;
		itr = stack_.listIterator();
	}
	
	public static ChanceCardSpace Instance(ArrayList<Player> players) {
		if (INSTANCE == null) {
			INSTANCE = new ChanceCardSpace(players);
		}
		
		return INSTANCE;
	}
	
	public String toString() {
		return "Chance";
	}
	
	public void landOn(Player p) {
		Card c = drawCard();
		NotificationManager.getInstance().notifyObservers(Notification.SHOW_CARD, c);
		new CardPanel(c);
		
		if (c instanceof CardTypeMove) 
			p.setPosition(((CardTypeMove)c).getSpace());
		else if (c instanceof CardTypeOutOfJail)
			p.setGetOutOfJail(true);
		else if (c instanceof CardTypePayFund) {
			p.updateCash(-((CardTypePayFund)c).getAmount());
			NotificationManager.getInstance().notifyObservers(Notification.UPDATE_SCHOLARSHIP_FUND, 
					new Integer(((CardTypePayFund)c).getAmount()));
		}
		else if (c instanceof CardTypePayPlayers) {
			//Pay people
			ListIterator<Player> iter = players_.listIterator();
			while (iter.hasNext()) {
				if (iter.next() != p) {
					p.updateCash(-((CardTypePayFund)c).getAmount());
					iter.previous().updateCash(((CardTypePayFund)c).getAmount());
				}
			}
		
		}
		else if (c instanceof CardTypeWinMoney) 
			p.updateCash(((CardTypeWinMoney)c).getAmount());
		else
			System.out.print("Unknown Card type passed to landOn()");
		
	}
	
	public Card drawCard() {
		
		if (itr.hasNext())
			return (Card) itr.next();
		else {
			while(itr.hasPrevious()) {
				itr.previous();
			}
			return (Card) itr.next();
		}
	}
	
	public void shuffleCards() {
		Collections.shuffle(stack_);
	}
}
