package edu.up.cs301.Uno;

import java.util.ArrayList;

import edu.up.cs301.Uno.actionMsg.ColorAction;
import edu.up.cs301.Uno.actionMsg.PlaceCardAction;
import edu.up.cs301.Uno.actionMsg.SkipTurnAction;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by fredenbe20 on 4/15/2018.
 *
 * @author Chris Fishback
 * @author Stelios Popoutsakis
 * @author Alli Jacobs
 * @author Mason Fredenberg
 */

public class UnoSmartComputerPlayer extends GameComputerPlayer {
    private ArrayList<Card> playableCards;


    public UnoSmartComputerPlayer(String name) {
        super(name);
        playableCards = new ArrayList<Card>();
    }

    protected void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            UnoGameState state = (UnoGameState) info;
            if (state.getTurn() == this.playerNum) {
                this.sleep(1000);//waits 1 second before playing
                this.playableCards.clear();
                //the smart AI cycles through its hand and selects all cards that can be played in the turn
                for (int i = 0; i < state.getCurrentPlayerHand().size(); i++) {
                    if (state.getCurrentPlayerHand().get(i).getColor() == state.getCurrentColor()
                            || state.getCurrentPlayerHand().get(i).getType() == state.getDiscardPile().getTopCard().getType()
                            || state.getCurrentPlayerHand().get(i).getType() == Type.WILD
                            || state.getCurrentPlayerHand().get(i).getType() == Type.WILDDRAW4) {
                        playableCards.add(state.getCurrentPlayerHand().get(i));
                    } else {
                        playableCards.add(null);
                    }
                }

                /*
                plays a card based on the number of cards in the other players hand
                 */


               /*
               calculates the next player based on direction
                */
                int nextPlayer = state.getTurn();
                if (state.getGameDirection()) {
                    if (state.getTurn() + 1 == state.getNumPlayers()) nextPlayer = 0;
                    else nextPlayer++;
                } else {
                    if (state.getTurn() - 1 == -1) nextPlayer = state.getNumPlayers() - 1;
                    else nextPlayer--;
                }

                if (isPlayableCardsNull()) {
                    this.game.sendAction(new SkipTurnAction(this));
                    return;

                }

                //will play a wild draw 4 if it has one and the other player is below 3 cards
                else if (state.getPlayerHandSize(nextPlayer) < 3) {
                    for (int i = 0; i < playableCards.size(); i++) {
                        if (playableCards.get(i) == null) continue;
                        if (playableCards.get(i).getType() == Type.WILDDRAW4) {
                            this.game.sendAction(new PlaceCardAction(this, i));
                            this.game.sendAction(new ColorAction(this,mostOfColor(state.getCurrentPlayerHand())));
                            return;
                        }
                    }
                    //will play a skip, plus 2, or reverse if they have less than 5 cards
                } else if (state.getPlayerHandSize((nextPlayer)) < 5) {
                    for (int i = 0; i < playableCards.size(); i++) {
                        if (playableCards.get(i) == null) continue;
                        if (playableCards.get(i).getType() == Type.SKIP
                                || playableCards.get(i).getType() == Type.PLUS2
                                || playableCards.get(i).getType() == Type.REVERSE) {
                            this.game.sendAction(new PlaceCardAction(this, i));
                            return;
                        }
                    }
                    //will play the first available number card (if there is one) if the next player has 5 or more cards
                } else if (state.getPlayerHandSize(nextPlayer) >= 5) {
                    for (int i = 0; i < playableCards.size(); i++) {
                        if (playableCards.get(i) == null) continue;
                        if (playableCards.get(i).getType() != Type.PLUS2
                                && playableCards.get(i).getType() != Type.SKIP
                                && playableCards.get(i).getType() != Type.REVERSE
                                && playableCards.get(i).getType() != Type.WILD
                                && playableCards.get(i).getType() != Type.WILDDRAW4) {
                            this.game.sendAction(new PlaceCardAction(this, i));
                            return;
                        }
                    }
                }
                //if none of these conditions are met, it will play the first card that matches thats not a wild
                for (int i = 0; i < playableCards.size(); i++) {
                    if (this.playableCards.get(i) == null) continue;
                    if (playableCards.get(i).getType() != Type.WILD
                            && playableCards.get(i).getType() != Type.WILDDRAW4) {
                        this.game.sendAction(new PlaceCardAction(this, i));
                        return;

                    }
                }


                //will play a wild if its is the only card it can play
                for (int i = 0; i < playableCards.size(); i++) {
                    if (playableCards.get(i) == null) continue;
                    if (playableCards.get(i).getType() == Type.WILD
                            || playableCards.get(i).getType() == Type.WILDDRAW4) {
                        this.game.sendAction(new PlaceCardAction(this, i));
                        this.game.sendAction(new ColorAction(this, this.mostOfColor(state.getCurrentPlayerHand())));
                        return;
                    }
                }

            }

        }

    }


    /*
    Checks to see if the computer has any playable cards in their hand
     */
    public boolean isPlayableCardsNull() {
        int cardsPlayable = 0;
        for (int i = 0; i < playableCards.size(); i++) {
            if (playableCards.get(i) != null) {
                cardsPlayable++;
            }
        }
        if (cardsPlayable != 0) {
            return false;
        } else {
            return true;
        }

    }

    /*
    checks to see if the only playable card in the computers hand is a wild
     */
    public boolean onlyHasWild() {
        boolean hasWild = false;
        boolean hasWild4 = false;
        int cardsPlayable = 0;
        int wilds = 0;

        for (int i = 0; i < playableCards.size(); i++) {
            if (playableCards.get(i) == null) continue;
            if (playableCards.get(i).getType() == Type.WILD) {
                hasWild = true;
                wilds++;
            } else if (playableCards.get(i).getType() == Type.WILDDRAW4) {
                hasWild4 = true;
                wilds++;
            }
            if (playableCards.get(i) != null) {
                cardsPlayable++;
            }
        }
        if (cardsPlayable - wilds == 0 && hasWild || hasWild4) {
            return true;
        }
        return false;
    }

    public Color mostOfColor(ArrayList<Card> hand) {
        int red = 0;
        int green = 0;
        int blue = 0;
        int yellow = 0;

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor() == Color.RED) {
                red++;
            } else if (hand.get(i).getColor() == Color.GREEN) {
                green++;
            } else if (hand.get(i).getColor() == Color.BLUE) {
                blue++;
            } else if (hand.get(i).getColor() == Color.YELLOW) {
                yellow++;
            }
        }
        if (red >= green && red >= blue && red >= yellow) {
            return Color.RED;
        } else if (green > red && green >= blue && green >= yellow) {
            return Color.GREEN;
        } else if (blue > red && blue > green && blue >= yellow) {
            return Color.BLUE;
        } else if (yellow > red && yellow > green && yellow > blue) {
            return Color.YELLOW;
        }
        return null;
    }

    public int getPlayerID() {
        return this.playerNum;
    }
}
